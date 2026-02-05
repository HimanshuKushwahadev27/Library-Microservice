package com.emi.Catalog_Service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emi.Catalog_Service.Entity.Book;
import com.emi.Catalog_Service.Entity.Book_Content;
import com.emi.Catalog_Service.Repository.BookContentRepo;
import com.emi.Catalog_Service.Repository.BookRepository;
import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;
import com.emi.Catalog_Service.Services.BookContentService;
import com.emi.Catalog_Service.enums.BookStatus;
import com.emi.Catalog_Service.exception.BookDeletedException;
import com.emi.Catalog_Service.exception.BookNotFoundException;
import com.emi.Catalog_Service.exception.ContentDeletedException;
import com.emi.Catalog_Service.exception.ContentNotFoundException;
import com.emi.Catalog_Service.mapper.ContentMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookContentImpl implements BookContentService {

	private final ContentMapper contentMapper;
	private final BookContentRepo contentRepo;
	private final BookRepository bookRepo;
	
	@Override
	public ResponseContentDto createBookContent(RequestCreateContentDto createContentDto) {
		
		Book book = bookRepo.findById(createContentDto.bookId())
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + createContentDto.bookId()));
		
		if(contentRepo
				.existsByBookIdAndChapterNumber(createContentDto.bookId(), createContentDto.chapterNumber())
				) {
			throw new IllegalArgumentException(
					"Chapter number " + createContentDto.chapterNumber() + " already exists for book with id: " + createContentDto.bookId());
		}
		
		
		if(book.getStatus()!=BookStatus.PUBLIC) {
			throw new BookDeletedException("Book for the bookID " + createContentDto.bookId() + " is not PUBLIC.");

		}
		
		Book_Content content =  contentMapper.toEntity(createContentDto);
		content.setDeleted(false);
		book.setTotalChapters(book.getTotalChapters()+1);
		bookRepo.save(book);
		contentRepo.save(content);
		
		return contentMapper.toDto(content);
	}

	@Override
	public List<ResponseContentDto> createMultipleBookContents(List<RequestCreateContentDto> createContentDto) {
		List<ResponseContentDto> createdContents = createContentDto
				.stream()
				.map(this::createBookContent)
				.toList();
		return createdContents;
	}

	@Override
	public ResponseContentDto getBookContentByContentId(UUID contentId) {
		Book_Content content = contentRepo.findById(contentId)
				.orElseThrow(() -> new ContentNotFoundException("Content not found with id: " + contentId));
		
		if(bookRepo.existsById(content.getBookId())==false) {
			throw new BookNotFoundException("Book not found for content with id: " + contentId);
		}
		
		Book book = bookRepo.findById(content.getBookId()).orElseThrow(() -> 
			new BookNotFoundException("Book not found for content with id: " + contentId));
		
		if(book.getStatus()!=BookStatus.PUBLIC) {
			throw new BookDeletedException("Book for the contentID " + contentId + " is not PUBLIC.");
		}
		
		if(content.isDeleted()) {
			throw new ContentDeletedException("Content not found with id: " + contentId);
		}
		
		return contentMapper.toDto(content);
	}

	@Override
	public List<ResponseContentDto> getBookContentsByContentIds(List<UUID> contentIds) {
		List<ResponseContentDto> contents = contentIds.stream()
				.map(this::getBookContentByContentId)
				.toList();
		return contents;
	}

	@Override
	public List<ResponseContentDto> getBookContentByBookId(UUID bookId) {
		
		if(!bookRepo.existsById(bookId)) {
			throw new BookNotFoundException("Book not found with id: " + bookId);
		}
		
		List<Book_Content> contents = contentRepo.findByBookIdAndIsDeletedFalse(bookId);
		
		if(contents.isEmpty()) {
			throw new ContentNotFoundException("No contents found for book with id: " + bookId);
		}
		
		return contents.stream()
				.map(contentMapper::toDto)
				.toList();
	}

	@Override
	public ResponseEntity<?> deleteBookContentByContentId(UUID contentId) {
		Book_Content content = contentRepo.findById(contentId).orElseThrow(
				() -> new ContentNotFoundException("Content not found with id: " + contentId));
		
		if(content.isDeleted()) {
			throw new ContentDeletedException("Content is already deleted with id: " + contentId);
		}
		
		content.setDeleted(true);
		content.setStatus(com.emi.Catalog_Service.enums.BookChapter_Status.DELETED);
		contentRepo.save(content);
		return ResponseEntity.ok("Content deleted successfully with id: " + contentId);
	}

	@Override
	public ResponseEntity<?> deleteBookContentsByContentIds(List<UUID> contentIds) {
		contentIds.forEach(this::deleteBookContentByContentId);
		return ResponseEntity.ok("Contents deleted successfully for provided ids.");
	}

	@Override
	public ResponseEntity<?> deleteBookContentByBookId(UUID bookId) {
		List<Book_Content> contents = contentRepo.findByBookIdAndIsDeletedFalse(bookId);
		
		if(contents.isEmpty()) {
			throw new ContentNotFoundException("No contents found for book with id: " + bookId);
		}
		
		contents.forEach(content -> {
			content.setDeleted(true);
			content.setStatus(com.emi.Catalog_Service.enums.BookChapter_Status.DELETED);
			contentRepo.save(content);
		});
		
		return ResponseEntity.ok("All contents deleted successfully for book with id: " + bookId);
	}

}

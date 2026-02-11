package com.emi.Catalog_Service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.Catalog_Service.Entity.Book;
import com.emi.Catalog_Service.Entity.Book_Content;
import com.emi.Catalog_Service.Repository.BookContentRepo;
import com.emi.Catalog_Service.Repository.BookRepository;
import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;
import com.emi.Catalog_Service.Services.BookContentService;
import com.emi.Catalog_Service.enums.BookVisibilityStatus;
import com.emi.Catalog_Service.exception.BookDeletedException;
import com.emi.Catalog_Service.exception.BookNotFoundException;
import com.emi.Catalog_Service.exception.ContentDeletedException;
import com.emi.Catalog_Service.exception.ContentNotFoundException;
import com.emi.Catalog_Service.exception.NotAuthorizedException;
import com.emi.Catalog_Service.mapper.ContentMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookContentImpl implements BookContentService {

	private final ContentMapper contentMapper;
	private final BookContentRepo contentRepo;
	private final BookRepository bookRepo;
	
	@Transactional
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
		
		
		if(!book.getStatusVisible().equals(BookVisibilityStatus.PUBLIC)) {
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
		
		if(!book.getStatusVisible().equals(BookVisibilityStatus.PUBLIC)) {
			throw new BookDeletedException("Book for the contentID " + contentId + " is not PUBLIC.");
		}
		
		if(content.isDeleted()) {
			throw new ContentDeletedException("Content not found with id: " + contentId);
		}
		
		return contentMapper.toDto(content);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ResponseContentDto> getBookContentsByContentIds(List<UUID> contentIds) {
		List<ResponseContentDto> contents = contentIds.stream()
				.map(this::getBookContentByContentId)
				.toList();
		return contents;
	}

	@Transactional(readOnly = true)
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
	public String deleteBookContentByContentId(UUID contentId, UUID authorId) {
		Book_Content content = contentRepo.findById(contentId).orElseThrow(
				() -> new ContentNotFoundException("Content not found with id: " + contentId));
		
		if(content.isDeleted()) {
			throw new ContentDeletedException("Content is already deleted with id: " + contentId);
		}
		
		Book book= bookRepo.findById(content.getBookId())
				.orElseThrow(
						() -> new BookNotFoundException("Book not found with id: " + content.getBookId()
						));
		
		if(!book.getAuthorSnapshots().stream().anyMatch(snapshot -> snapshot.getId().equals(authorId))){
			throw new NotAuthorizedException("You are not authorized to make any changes to content with book Id " + content.getBookId());
		}
		
		content.setDeleted(true);
		content.setStatus(com.emi.Catalog_Service.enums.BookChapter_Status.DELETED);
		contentRepo.save(content);
		return "Content deleted successfully with id: " + contentId;
	}

	@Override
	public String deleteBookContentsByContentIds(List<UUID> contentIds, UUID authorId) {
		contentIds.stream().forEach(t -> this.deleteBookContentByContentId(t, authorId));
		return "Contents deleted successfully for provided ids.";
	}

	@Transactional
	@Override
	public String deleteBookContentByBookId(UUID bookId, UUID authorId) {
		List<Book_Content> contents = contentRepo.findByBookIdAndIsDeletedFalse(bookId);
		
		if(contents.isEmpty()) {
			throw new ContentNotFoundException("No contents found for book with id: " + bookId);
		}
		
		Book book= bookRepo.findById(bookId)
				.orElseThrow(
						() -> new BookNotFoundException("Book not found with id: " + bookId
						));
		
		if(!book.getAuthorSnapshots().stream().anyMatch(snapshot -> snapshot.getId().equals(authorId))){
			throw new NotAuthorizedException("You are not authorized to make any changes to content with book Id " + bookId);
		}
		
		
		contents.forEach(content -> {
			content.setDeleted(true);
			content.setStatus(com.emi.Catalog_Service.enums.BookChapter_Status.DELETED);
			contentRepo.save(content);
		});
		
		return "All contents deleted successfully for book with id: " + bookId;
	}

}

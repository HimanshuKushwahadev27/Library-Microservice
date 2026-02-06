package com.emi.Catalog_Service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.Catalog_Service.Entity.Book;
import com.emi.Catalog_Service.Repository.BookContentRepo;
import com.emi.Catalog_Service.Repository.BookRepository;
import com.emi.Catalog_Service.RequestDtos.RequestBookCreationDto;
import com.emi.Catalog_Service.RequestDtos.RequsestBookUpdateDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseBookDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseFullBookDto;
import com.emi.Catalog_Service.Services.BookContentService;
import com.emi.Catalog_Service.Services.BookService;
import com.emi.Catalog_Service.enums.BookStatus;
import com.emi.Catalog_Service.exception.BookDeletedException;
import com.emi.Catalog_Service.exception.BookNotFoundException;
import com.emi.Catalog_Service.exception.NotAuthorizedException;
import com.emi.Catalog_Service.mapper.AuthorSnapshotMapper;
import com.emi.Catalog_Service.mapper.BookMapper;
import com.emi.Catalog_Service.mapper.GenreSnapshotMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	
	private final BookContentService contentService;
	private final BookContentRepo contentRepo;
	private final BookRepository bookRepo;
	private final BookMapper bookMapper;
	private final AuthorSnapshotMapper authorMapper;
	private final GenreSnapshotMapper genreMapper;
	
	@Override
	public ResponseBookDto createBook(RequestBookCreationDto requestDto) {
		Book book = bookMapper.toEntity(requestDto);
		
		authorMapper.setAuthorSnapshot(book, requestDto.authorInfo());
		genreMapper.setGenreSnapshot(book, requestDto.genreInfo());
		Book savedBook = bookRepo.save(book);
		return bookMapper.toDto(savedBook);
		
	}

	@Override
	public ResponseFullBookDto getBookById(UUID bookId) {
		Book book = bookRepo
				.findById(bookId)
				.orElseThrow(
						() -> new BookNotFoundException("Book not found with id: " + bookId)
						);
		if(book.isDeleted()) {
			throw new BookDeletedException("Book with id: " + bookId + " has been deleted.");
		}
		
		List<UUID> chapterIds=contentRepo
								.findAllByBookId(bookId)
								.stream()
								.map(content -> content.getId())
								.toList();
		
	    return bookMapper.toFullBookDto(book , chapterIds);
	}

	@Override
	public List<ResponseFullBookDto> getBookByIds(List<UUID> bookIds) {
	  		List<ResponseFullBookDto> books = bookIds
				.stream()
				.map(this::getBookById)
				.toList();
		return books;
	}

	@Override
	public ResponseBookDto updateBook(RequsestBookUpdateDto requestDto, UUID authorId) {
		
		Book book = bookRepo.findById(requestDto.bookId())
				.orElseThrow(
						() -> new BookNotFoundException("Book not found with id: " + requestDto.bookId())
						);
		
		if(book.isDeleted()) {
			throw new BookDeletedException("Book with id: " + requestDto.bookId() + " has been already deleted.");
		}
		
		if(!book.getAuthorSnapshots().stream().anyMatch(snapshot -> snapshot.getId().equals(authorId))){
			throw new NotAuthorizedException("You are not authorized to make any changes to book with Id " + book.getId());
		}
		
		book=bookMapper.updateBookEntity(requestDto, book);
		genreMapper.updateGenreSnapshot(book, requestDto.genreInfo());
		
		bookRepo.save(book);
		
		return bookMapper.returnUpdatedBook(requestDto, book.getTotalChapters());
		
	}

	@Override
	public String deleteBook(UUID bookId, UUID authorId) {
		Book book = bookRepo.findById(bookId)
				.orElseThrow(
						() -> new BookNotFoundException("Book not found with id: " + bookId)
						);
		if(book.isDeleted()) {
			throw new BookDeletedException("Book with id: " + bookId + " has been already deleted.");
		}

		
		if(!book.getAuthorSnapshots().stream().anyMatch(snapshot -> snapshot.getId().equals(authorId))){
			throw new NotAuthorizedException("You are not authorized to make any changes to book with Id " + bookId);
		}
		
		book.setDeleted(true);
		book.setStatus(BookStatus.DELETED);
		
		if(book.getTotalChapters()>0) {
		String info=contentService.deleteBookContentByBookId(bookId, authorId);
		log.info(info);
		}
		
		bookRepo.save(book);
		
		return "Book deleted successfully with id: " + bookId;
	}

}

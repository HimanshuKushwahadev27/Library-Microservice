package com.emi.Catalog_Service.mapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.emi.Catalog_Service.Entity.Book;
import com.emi.Catalog_Service.RequestDtos.RequestBookCreationDto;
import com.emi.Catalog_Service.RequestDtos.RequsestBookUpdateDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseBookDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseFullBookDto;

@Component
public class BookMapper {

	public Book toEntity(RequestBookCreationDto requestDto) {
		Book book = new Book();
		book.setTitle(requestDto.title());
		book.setDescription(requestDto.description());
		book.setISBN(requestDto.ISBN());
		book.setPrice(requestDto.price());
		book.setCreatedAt(Instant.now());
		book.setUpdatedAt(Instant.now());
		book.setTotalChapters(0);
		book.setDeleted(false);
		book.setFreePreview(requestDto.freePreviewAvailable());
		book.setStatusLifecycle(requestDto.lifeCycleStatus());
		book.setStatusVisible(requestDto.visibilityStatus());
		return book;
	}

	public ResponseBookDto toDto(Book savedBook) {
		return new ResponseBookDto(
				savedBook.getId(),
				savedBook.getStatusLifecycle(),
				savedBook.getStatusVisible(),
				savedBook.getTotalChapters(),
				"Book Saved in Author's publsh section Successfully (Catalog service)"
				);
			
	}
	
	public ResponseFullBookDto toFullBookDto(Book book, List<UUID> chapterIds) {
		return new ResponseFullBookDto(
				
				book.getTitle(),
				book.getDescription(),
				book.getISBN(),
				book.getPrice(),
				book.getAuthorSnapshots().stream().collect(Collectors.toList()),
				book.getGenreIds().stream().collect(Collectors.toList()),
				book.getStatusLifecycle(),
				book.getStatusVisible(),
				book.getTotalChapters(),
				chapterIds,
				book.getFreePreview()
				);
	}
	
	public Book updateBookEntity(RequsestBookUpdateDto request, Book book) {
		book.setPrice(request.price());
		book.setDescription(request.description());
		book.setFreePreview(request.freePreviewAvailable());
		book.setUpdatedAt(Instant.now());
		return book;
	}
	
	public ResponseBookDto returnUpdatedBook(RequsestBookUpdateDto request, Integer totalChapters) {
		return new ResponseBookDto(
				request.bookId(),
				request.lifeCycleStatus(),
				request.visibilityStatus(),
				totalChapters,
				"Book Saved in Author's publsh section Successfully (Catalog service)"				
				);
	}
	
	

}

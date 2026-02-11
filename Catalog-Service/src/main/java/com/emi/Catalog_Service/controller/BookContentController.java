package com.emi.Catalog_Service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;
import com.emi.Catalog_Service.Services.BookContentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books/contents")
public class BookContentController {

	private final BookContentService bookContentService;

	
	
	@PostMapping(value="/create")
	public ResponseEntity<ResponseContentDto> createBookContent(
			@RequestBody @Valid RequestCreateContentDto request){
		return ResponseEntity.ok(bookContentService.createBookContent(request));
	}
	
	@PostMapping(value="/createMultiple")
	public ResponseEntity<List<ResponseContentDto>> createMultipleBookContents(
			@RequestBody @Valid List<RequestCreateContentDto> request){
		return ResponseEntity.ok(bookContentService.createMultipleBookContents(request));
	}
	
	@GetMapping(value="/ContentIds")
	public ResponseEntity<List<ResponseContentDto>> getBookContentsByContentIds(
			@RequestParam List<UUID> contentIds){
		return ResponseEntity.ok(bookContentService.getBookContentsByContentIds(contentIds));
	}
	
	@GetMapping(value="/bookId/{bookId}")
	public ResponseEntity<List<ResponseContentDto>> getBookContentByBookId(
			@PathVariable UUID bookId){
		return ResponseEntity.ok(bookContentService.getBookContentByBookId(bookId));
	}
	
	@DeleteMapping(value="/contentIds/{authorId}")
	public ResponseEntity<String> deleteBookContentByContentId(
			@RequestParam List<UUID> contentId, @PathVariable UUID authorId){
		return ResponseEntity.ok(bookContentService.deleteBookContentsByContentIds(contentId, authorId));
	}
	
	@DeleteMapping(value="/bookId/{bookId}/{authorId}")
	public ResponseEntity<String> deleteBookContentByBookId(
			@PathVariable UUID bookId,@PathVariable UUID authorId){
		return ResponseEntity.ok(bookContentService.deleteBookContentByBookId(bookId, authorId));
	}
	
	
}

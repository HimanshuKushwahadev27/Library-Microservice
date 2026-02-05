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
	
	@PostMapping(value="/create/")
	public ResponseEntity<List<ResponseContentDto>> createMultipleBookContents(
			@RequestBody @Valid List<RequestCreateContentDto> request){
		return ResponseEntity.ok(bookContentService.createMultipleBookContents(request));
	}
	
	@GetMapping(value="/{contentId}")
	public ResponseEntity<ResponseContentDto> getBookContentByContentId(
			@PathVariable UUID contentId){
		return ResponseEntity.ok(bookContentService.getBookContentByContentId(contentId));
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseContentDto>> getBookContentsByContentIds(
			@RequestParam List<UUID> contentIds){
		return ResponseEntity.ok(bookContentService.getBookContentsByContentIds(contentIds));
	}
	
	@GetMapping(value="/{bookId}")
	public ResponseEntity<List<ResponseContentDto>> getBookContentByBookId(
			@PathVariable UUID bookId){
		return ResponseEntity.ok(bookContentService.getBookContentByBookId(bookId));
	}
	
	@DeleteMapping(value="/{contentId}")
	public ResponseEntity<String> deleteBookContentByContentId(
			@PathVariable UUID contentId){
		return ResponseEntity.ok(bookContentService.deleteBookContentByContentId(contentId));
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteBookContentsByContentIds(
			@RequestParam List<UUID> contentIds){
		return ResponseEntity.ok(bookContentService.deleteBookContentsByContentIds(contentIds));
	}
	
	@DeleteMapping(value="/{bookId}")
	public ResponseEntity<String> deleteBookContentByBookId(
			@PathVariable UUID bookId){
		return ResponseEntity.ok(bookContentService.deleteBookContentByBookId(bookId));
	}
	
	
}

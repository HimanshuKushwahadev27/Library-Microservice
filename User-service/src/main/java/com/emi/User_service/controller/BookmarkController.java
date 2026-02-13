package com.emi.User_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.User_service.requestDto.RequestBookmarkDto;

import com.emi.User_service.responseDto.ResponseBookmarkDto;
import com.emi.User_service.service.BookMarkService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/bookmark")
public class BookmarkController {

	private final BookMarkService bookmarkService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseBookmarkDto> create(@RequestBody @Valid RequestBookmarkDto request, @AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(bookmarkService.create(request, UUID.fromString(jwt.getSubject())));
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ResponseBookmarkDto>> getAll(@AuthenticationPrincipal Jwt jwt){
		return ResponseEntity.ok(bookmarkService.getAll(UUID.fromString(jwt.getSubject())));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id,  @AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(bookmarkService.delete(id, UUID.fromString(jwt.getSubject())));
	}
}

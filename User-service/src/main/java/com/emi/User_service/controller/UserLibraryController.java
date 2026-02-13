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

import com.emi.User_service.requestDto.RequestUserLibraryDto;
import com.emi.User_service.responseDto.ResponseUserLibraryDto;
import com.emi.User_service.service.UserLibraryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/userslibrary")
public class UserLibraryController {

	private final UserLibraryService userLibraryService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseUserLibraryDto> create(
			  @AuthenticationPrincipal Jwt jwt,
			@RequestBody @Valid RequestUserLibraryDto request) {
		return ResponseEntity.ok(userLibraryService.create(request, UUID.fromString(jwt.getSubject())));
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ResponseUserLibraryDto>> getAll(@AuthenticationPrincipal Jwt jwt){
		return ResponseEntity.ok(userLibraryService.getAll(UUID.fromString(jwt.getSubject())));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(userLibraryService.delete(id, UUID.fromString(jwt.getSubject())));
	}
}

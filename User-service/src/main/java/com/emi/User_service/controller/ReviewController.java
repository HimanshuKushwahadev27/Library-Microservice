package com.emi.User_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.User_service.requestDto.RequestReviewDto;
import com.emi.User_service.responseDto.ResponseReviewDto;
import com.emi.User_service.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/review")
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/create")
	public ResponseEntity<ResponseReviewDto> create(@RequestBody @Valid RequestReviewDto request, @AuthenticationPrincipal Jwt jwt)

	{
		return ResponseEntity.ok(reviewService.create(request, UUID.fromString(jwt.getSubject())));
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<List<ResponseReviewDto>> get(UUID bookId){
		return ResponseEntity.ok(reviewService.get(bookId));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(UUID id) {
		return ResponseEntity.ok(reviewService.delete(id));
	}
}

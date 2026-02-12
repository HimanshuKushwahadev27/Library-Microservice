package com.emi.Author_Service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.Author_Service.requestDto.RequestAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDtoForAdmin;
import com.emi.Author_Service.service.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

	private final AuthorService authorService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseAuthorDto> register(@RequestBody @Valid RequestAuthorDto request) {
		return ResponseEntity.ok(authorService.register(request, null));
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseAuthorDto> update(@RequestBody @Valid RequestAuthorDto request, @PathVariable  UUID authorID) {
		return ResponseEntity.ok(authorService.update(request, authorID, authorID));
	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@PathVariable UUID authorId) {
		return ResponseEntity.ok(authorService.delete(authorId, authorId));
	}
	
	
	@GetMapping("/get/{authorId}")
	public ResponseEntity<ResponseAuthorDto> get(@PathVariable UUID authorId) {
		return ResponseEntity.ok(authorService.get(authorId, authorId));
	}
	
	@GetMapping("/gets")
	public ResponseEntity<List<ResponseAuthorDtoForAdmin>> getAuthors(){
		return ResponseEntity.ok(authorService.getAuthors());

	}
}

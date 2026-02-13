package com.emi.User_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.User_service.requestDto.UserRequestDto;
import com.emi.User_service.requestDto.UserUpdateRequestDto;
import com.emi.User_service.responseDto.UserResponseDto;
import com.emi.User_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto request, @AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(userService.create(request, UUID.fromString(jwt.getSubject())));
	}
	
	@PatchMapping("/update")
	public ResponseEntity<UserResponseDto>  update(@RequestBody @Valid UserUpdateRequestDto request, @AuthenticationPrincipal Jwt jwt){
		return ResponseEntity.ok(userService.update(request, UUID.fromString(jwt.getSubject())));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserResponseDto>  get(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt){
		return ResponseEntity.ok(userService.get(id, UUID.fromString(jwt.getSubject())));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>  delete(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt){
		return ResponseEntity.ok(userService.delete(id, UUID.fromString(jwt.getSubject())));

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResponseDto>> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
}

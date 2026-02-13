package com.emi.User_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.User_service.requestDto.UserRequestDto;
import com.emi.User_service.requestDto.UserUpdateRequestDto;
import com.emi.User_service.responseDto.UserResponseDto;

public interface UserService {

	public UserResponseDto create(UserRequestDto request, UUID keycloakId);
	
	public UserResponseDto update(UserUpdateRequestDto request, UUID keycloakId);
	
	public UserResponseDto get(UUID id, UUID keycloakId);
	
	public String delete(UUID id, UUID keycloakId);
	
	public List<UserResponseDto> getAll();
}

package com.emi.User_service.mapper;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emi.User_service.entity.User;
import com.emi.User_service.requestDto.UserRequestDto;
import com.emi.User_service.requestDto.UserUpdateRequestDto;
import com.emi.User_service.responseDto.UserResponseDto;

@Component
public class UserServiceMapper {

	public User toEntity(UserRequestDto request, UUID keycloakId) {
		User user = new User();
		user.setBio(request.bio());
		user.setCreatedAt(Instant.now());
		user.setDisplayName(request.displayName());
		user.setKeycloakId(keycloakId);
		user.setProfileImageUrl(request.profileImgUrl());
		
		return user;
	}

	public UserResponseDto toDto(User user) {
		return new UserResponseDto(	
				user.getId(),
				user.getDisplayName(),
				user.getProfileImageUrl(),
				user.getBio(),
				user.getCreatedAt())
		
				;
	}

	public void toUpdate(UserUpdateRequestDto request, User user) {
		user.setDisplayName(request.displayName());
		user.setProfileImageUrl(request.imageUrl());
		user.setBio(request.bio());
		
	}

}

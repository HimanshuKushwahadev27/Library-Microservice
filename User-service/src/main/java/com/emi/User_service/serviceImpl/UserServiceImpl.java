package com.emi.User_service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.User_service.entity.User;
import com.emi.User_service.exception.DeletedException;
import com.emi.User_service.exception.UserExistsException;
import com.emi.User_service.mapper.UserServiceMapper;
import com.emi.User_service.repository.UserRepo;
import com.emi.User_service.requestDto.UserRequestDto;
import com.emi.User_service.requestDto.UserUpdateRequestDto;
import com.emi.User_service.responseDto.UserResponseDto;
import com.emi.User_service.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final UserServiceMapper userServiceMapper;
	
	
	@Override
	public UserResponseDto create(UserRequestDto request, UUID keycloakId) {
		
		if(userRepo.existsByKeycloakId(keycloakId)) {
			throw new UserExistsException("You are already registered as an user");
		}
		
		User user = userServiceMapper.toEntity(request, keycloakId);
		userRepo.save(user);
		return userServiceMapper.toDto(user);
	}

	@Override
	public UserResponseDto update(UserUpdateRequestDto request, UUID keycloakId) {
		
		User user = userRepo
				.findById(request.id())
				.orElseThrow(
						() -> new  UserExistsException("You are not registered as an user")
						);
		
		if(!user.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt match");
		}
		
		if(user.getIsDeleted()) {
			throw new DeletedException("user is deleted of id " +user.getId());
		}
		
		userServiceMapper.toUpdate(request, user);
		userRepo.save(user);
		
		return userServiceMapper.toDto(user);
	}

	@Override
	public UserResponseDto get(UUID id, UUID keycloakId) {
		User user = userRepo
				.findById(id)
				.orElseThrow(
						() -> new  UserExistsException("You are not registered as an user")
						);
		
		if(user.getIsDeleted()) {
			throw new DeletedException("user is deleted of id " +id);
		}
		
		if(!user.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt match");
		}
		
		return userServiceMapper.toDto(user);
	}

	@Override
	public String delete(UUID id, UUID keycloakId) {
		User user = userRepo
				.findById(id)
				.orElseThrow(
						() -> new  UserExistsException("You are not registered as an user")
						);
		
		if(!user.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt match");
		}
		
		if(user.getIsDeleted()) {
			throw new DeletedException("user is deleted of id " +id);
		}
		
		user.setIsDeleted(true);
		return "User with id " +id+ " is removed";
	}

	@Override
	public List<UserResponseDto> getAll() {
	  List<User> users = userRepo.findAll();
	  
	  return users.stream().map(userServiceMapper::toDto).toList()	;
	}

}

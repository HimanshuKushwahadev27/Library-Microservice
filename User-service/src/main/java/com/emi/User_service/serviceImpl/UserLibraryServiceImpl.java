package com.emi.User_service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.User_service.entity.UserLibrary;
import com.emi.User_service.exception.BookExistsInLibraryException;
import com.emi.User_service.mapper.UserLibraryMapper;
import com.emi.User_service.repository.UserLibraryRepo;
import com.emi.User_service.requestDto.RequestUserLibraryDto;
import com.emi.User_service.responseDto.ResponseUserLibraryDto;
import com.emi.User_service.service.UserLibraryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLibraryServiceImpl implements UserLibraryService {

	private final UserLibraryRepo userLibraryRepo;
	private final UserLibraryMapper userLibraryMapper;

	@Override
	public ResponseUserLibraryDto create(RequestUserLibraryDto request, UUID keycloakId) {

		if (userLibraryRepo.existsByBookIdAndKeycloakId(request.bookId(), keycloakId)) {
			throw new BookExistsInLibraryException(
					"book with id " + request.bookId() + " already exists in user's library");
		}

		UserLibrary userLibrary = userLibraryMapper.toEntity(request, keycloakId);
		userLibraryRepo.save(userLibrary);
		return userLibraryMapper.toDto(userLibrary);
	}

	@Override
	public List<ResponseUserLibraryDto> getAll(UUID keycloakId) {

		if (!userLibraryRepo.existsByKeycloakId(keycloakId)) {
			throw new BookExistsInLibraryException("you havent purchased any book yet");
		}

		List<UserLibrary> libs = userLibraryRepo.findByKeycloakId(keycloakId);

		return libs.stream().map(userLibraryMapper::toDto).toList();
	}

	@Override
	public String delete(UUID id, UUID keycloakId) {
		
		if (userLibraryRepo.existsByIdAndKeycloakId(id, keycloakId)) {
			throw new BookExistsInLibraryException(
					"book with id " + id + "is not found for the given user");
		}
		
		userLibraryRepo.deleteById(id);
		
		return "Book int the user's library with the id " +id+ " is removed";
		
	}

}


package com.emi.User_service.mapper;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emi.User_service.entity.UserLibrary;
import com.emi.User_service.requestDto.RequestUserLibraryDto;
import com.emi.User_service.responseDto.ResponseUserLibraryDto;

@Component
public class UserLibraryMapper {

	public UserLibrary toEntity(RequestUserLibraryDto request, UUID keycloakId) {
		UserLibrary lib = new UserLibrary();

		lib.setBookId(request.bookId());
		lib.setKeycloakId(keycloakId);
		lib.setPurchasedAt(Instant.now());

		return lib;
	}

	public ResponseUserLibraryDto toDto(UserLibrary userLibrary) {
		return new ResponseUserLibraryDto(userLibrary.getId(), userLibrary.getBookId(), userLibrary.getPurchasedAt());

	}

}

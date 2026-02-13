package com.emi.User_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.User_service.requestDto.RequestUserLibraryDto;
import com.emi.User_service.responseDto.ResponseUserLibraryDto;


public interface UserLibraryService {

	public ResponseUserLibraryDto create(RequestUserLibraryDto request, UUID keycloakId);
	public List<ResponseUserLibraryDto> getAll(UUID keycloakId);
	public String delete(UUID id, UUID keycloakId);
	
}

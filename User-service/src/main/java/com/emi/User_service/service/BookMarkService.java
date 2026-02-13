package com.emi.User_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.User_service.requestDto.RequestBookmarkDto;
import com.emi.User_service.responseDto.ResponseBookmarkDto;


public interface BookMarkService {

	public ResponseBookmarkDto create(RequestBookmarkDto request, UUID keycloakId);
	
	public List<ResponseBookmarkDto> getAll(UUID keycloakId);
	
	public String delete(UUID id, UUID keycloakId);
}

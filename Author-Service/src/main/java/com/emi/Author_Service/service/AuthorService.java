package com.emi.Author_Service.service;

import java.util.List;
import java.util.UUID;

import com.emi.Author_Service.requestDto.RequestAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDtoForAdmin;

public interface AuthorService {

	public ResponseAuthorDto register(RequestAuthorDto request, UUID keycloakID);
	
	public ResponseAuthorDto update(RequestAuthorDto request, UUID keycloakId,  UUID authorID);
	
	public String delete(UUID authorId, UUID keycloakId);
	
	public ResponseAuthorDto get(UUID authorId, UUID keycloakId);
	
	public List<ResponseAuthorDtoForAdmin> getAuthors();
}

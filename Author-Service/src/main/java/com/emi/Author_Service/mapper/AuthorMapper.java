package com.emi.Author_Service.mapper;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emi.Author_Service.entity.Author;
import com.emi.Author_Service.requestDto.RequestAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDtoForAdmin;

@Component
public class AuthorMapper {

	public Author toEntity(RequestAuthorDto request, UUID keycloakId) {
		Author author = new Author();
		
		author.setBio(request.bio());
		author.setCreatedAt(Instant.now());
		author.setKeycloakId(keycloakId);
		author.setPenName(request.penName());
		author.setProfileImageUrl(request.profileImageUrl());
		author.setUpdatedAt(Instant.now());
		author.setVerified(false);
		author.setDeleted(false);
		
		return author;
	}

	public ResponseAuthorDto toDto(Author author) {
		return new ResponseAuthorDto(
				author.getId(),
				author.getPenName(),
				author.getBio(),
				author.getProfileImageUrl(),
				author.getVerified(),
				author.getCreatedAt()
				);
	}
	
	public ResponseAuthorDtoForAdmin toDtoAdmin(Author author) {
		return new ResponseAuthorDtoForAdmin(
				author.getId(),
				author.getPenName(),
				author.getBio(),
				author.getProfileImageUrl(),
				author.getVerified(),
				author.getCreatedAt(),
				author.isDeleted(),
				author.getUpdatedAt()
				
				);
	}

	public void toUpdate(RequestAuthorDto request,  Author author) {
		author.setPenName(request.penName());
		author.setBio(request.bio());
		author.setProfileImageUrl(request.profileImageUrl());
	}

}

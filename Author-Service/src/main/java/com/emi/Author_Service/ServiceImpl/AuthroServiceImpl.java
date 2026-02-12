package com.emi.Author_Service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.Author_Service.Repository.AuthorRepo;
import com.emi.Author_Service.entity.Author;
import com.emi.Author_Service.exception.DeletedException;
import com.emi.Author_Service.exception.RegisteredAuthorException;
import com.emi.Author_Service.mapper.AuthorMapper;
import com.emi.Author_Service.requestDto.RequestAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDto;
import com.emi.Author_Service.responseDto.ResponseAuthorDtoForAdmin;
import com.emi.Author_Service.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthroServiceImpl implements AuthorService {

	private AuthorRepo authorRepo;
	private AuthorMapper authorMapper;
	
	@Override
	public ResponseAuthorDto register(RequestAuthorDto request, UUID keycloakId) {
		
		if(authorRepo.existsByKeycloakId(keycloakId)) {
			throw new RegisteredAuthorException("You are already registered as an author");
		}
		
		Author author = authorMapper.toEntity(request, keycloakId);
		
		authorRepo.save(author);
		
		return authorMapper.toDto(author);
		
	}

	@Override
	public ResponseAuthorDto update(RequestAuthorDto request, UUID keycloakId, UUID authorId) {
		
		if(!authorRepo.existsByKeycloakId(keycloakId)) {
			throw new RegisteredAuthorException("You are not registered as an author please register first");
		}
		
		Author author = authorRepo
				.findById(authorId)
				.orElseThrow(
						() -> new RegisteredAuthorException("Authot not found for the id "+ authorId)
						);
		
		if(!author.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt matches");
		}
		
		if(author.isDeleted()) {
			throw new DeletedException("authro is deleted");
		}
		authorMapper.toUpdate(request, author);
		authorRepo.save(author);
		
		return authorMapper.toDto(author);
	}

	@Override
	public String delete(UUID authorId, UUID keycloakId) {
		
		if(!authorRepo.existsByKeycloakId(keycloakId)) {
			throw new RegisteredAuthorException("You are not registered as an author please register first");
		}
		
		Author author = authorRepo
				.findById(authorId)
				.orElseThrow(
						() -> new RegisteredAuthorException("Authot not found for the id "+ authorId)
						);
		
		if(!author.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt matches");
		}
		
		if(author.isDeleted()) {
			throw new DeletedException("authro is deleted");
		}
		
		author.setDeleted(true);
		authorRepo.save(author);
		 
		return "Author with the id " + authorId + "is Deleted";
		
	}

	@Override
	public List<ResponseAuthorDtoForAdmin> getAuthors() {
		List<Author> authors = authorRepo.findAll();
		
		return authors.stream().map(authorMapper::toDtoAdmin).toList();
	}

	@Override
	public ResponseAuthorDto get(UUID authorId, UUID keycloakId) {
		
		if(!authorRepo.existsByKeycloakId(keycloakId)) {
			throw new RegisteredAuthorException("You are not registered as an author please register first");
		}
		
		Author author = authorRepo
				.findById(authorId)
				.orElseThrow(
						() -> new RegisteredAuthorException("Authot not found for the id "+ authorId)
						);
		
		if(!author.getKeycloakId().equals(keycloakId)) {
			throw new IllegalArgumentException("Id doesnt matches");
		}
		
		if(author.isDeleted()) {
			throw new DeletedException("authro is deleted");
		}
		
		return authorMapper.toDto(author);
	}

}

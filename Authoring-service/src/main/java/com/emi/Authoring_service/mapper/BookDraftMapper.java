package com.emi.Authoring_service.mapper;

import java.time.Instant;



import org.springframework.stereotype.Component;

import com.emi.Authoring_service.RequestDtos.PublishDraftBookRequest;
import com.emi.Authoring_service.RequestDtos.RequestBookCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestBookCreationDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftBookDto;
import com.emi.Authoring_service.RequestDtos.RequsestBookUpdateDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftBookDto;
import com.emi.Authoring_service.entity.AuthorDraftBook;

import jakarta.validation.Valid;


@Component
public class BookDraftMapper {

	public AuthorDraftBook toEntity(RequestBookCreateDto request) {
		
		AuthorDraftBook draftBook= new AuthorDraftBook();
		draftBook.setAuthorId(request.authorId());
		draftBook.setDescription(request.description());
		draftBook.setFreePreview(request.freePreview());
		draftBook.setIsbn(request.isbn());
		draftBook.setStatusLifecycle(request.lifeCycleStatus());
		draftBook.setStatusVisible(request.visibilityStatus());
		draftBook.setPrice(request.price());
		draftBook.setTitle(request.title());
		draftBook.setUpdatedAt(Instant.now());
		draftBook.setCreatedAt(Instant.now());
		
		return draftBook;
	}

	public ResponseDraftBookDto toDto(AuthorDraftBook draftBook) {
		return new ResponseDraftBookDto(
				draftBook.getId(),
				draftBook.getAuthorId(),
				draftBook.getTitle(),
				draftBook.getDescription(),
				draftBook.getIsbn(),
				draftBook.getPrice(),
				draftBook.getStatusLifecycle(),
				draftBook.getStatusVisible(),
				draftBook.getFreePreview(),
				draftBook.getCreatedAt(),
				draftBook.getUpdatedAt()
				);
							     
	}

	public AuthorDraftBook updateDraft(RequestUpdateDraftBookDto request, AuthorDraftBook draftBook ) {
		
		
		draftBook.setDescription(request.description());
		draftBook.setFreePreview(request.freePreview());
		draftBook.setStatusLifecycle(request.lifeCycleStatus());
		draftBook.setStatusVisible(request.visibilityStatus());
		draftBook.setPrice(request.price());
		draftBook.setTitle(request.title());
		draftBook.setUpdatedAt(Instant.now());
		
		return draftBook;
		
	}

	public @Valid RequestBookCreationDto toPublish(AuthorDraftBook bookDraft, PublishDraftBookRequest request) {
		return new RequestBookCreationDto(
				bookDraft.getTitle(),
				bookDraft.getIsbn(),
				bookDraft.getPrice(),
				request.authorInfo(),
				request.genreInfo(),
				bookDraft.getFreePreview(),
				bookDraft.getDescription(),
				bookDraft.getStatusLifecycle(),
				bookDraft.getStatusVisible()
				);
	}

	public @Valid RequsestBookUpdateDto toUpdatePublished(RequestUpdateDraftBookDto request) {
		return new RequsestBookUpdateDto(
				request.catalogId(),
				request.price(),
				request.description(),
				request.freePreview(),
				request.lifeCycleStatus(),
				request.visibilityStatus()
				);
	}

}

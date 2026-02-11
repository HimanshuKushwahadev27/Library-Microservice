package com.emi.Authoring_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.Authoring_service.RequestDtos.PublishDraftBookRequest;
import com.emi.Authoring_service.RequestDtos.RequestBookCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftBookDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftBookDto;

public interface DraftBookService {

	public ResponseDraftBookDto   createBookDraft(RequestBookCreateDto request);
	public ResponseDraftBookDto updateBookDraft( RequestUpdateDraftBookDto  request);
	public List<ResponseDraftBookDto>  getMyDraftBooks(UUID authorId);
	public ResponseDraftBookDto  getMyDraftBooksById(UUID authorId, UUID draftBookId);
	
	public String deleteDraftBookById(UUID bookId, UUID authorId);
	
	public UUID publishDraftedBook( PublishDraftBookRequest request, UUID authorId);
	

	public void updatePublishedBook( RequestUpdateDraftBookDto request);

}

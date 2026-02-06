package com.emi.Authoring_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.Authoring_service.RequestDtos.RequestChapterCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftChapterDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftChapterDto;

public interface DraftChapterService {

	public ResponseDraftChapterDto   createChapterDraft(RequestChapterCreateDto request);
	public ResponseDraftChapterDto  updateChapterDraft( RequestUpdateDraftChapterDto  request);
	public List<ResponseDraftChapterDto >  getMyDraftChapters(UUID authorId, UUID bookId);
	public ResponseDraftChapterDto   getMyDraftChapterById(UUID authorId, UUID draftChapterId);
	
	public String deleteDraftChapterById(UUID chapterId, UUID authorId);
	
	public ResponseDraftChapterDto  publishDraftedChapters( UUID draftChapterId, UUID authorId);
	

}

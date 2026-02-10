package com.emi.Authoring_service.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.emi.Authoring_service.RequestDtos.RequestChapterCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftChapterDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftChapterDto;

public interface DraftChapterService {

	public ResponseDraftChapterDto   createChapterDraft(RequestChapterCreateDto request);
	public ResponseDraftChapterDto  updateChapterDraft( RequestUpdateDraftChapterDto  request, UUID authorId);
	public List<ResponseDraftChapterDto >  getMyDraftChaptersByBookId(UUID authorId, UUID bookId);
	public ResponseDraftChapterDto   getMyDraftChapterById(UUID authorId, UUID draftChapterId);
	
	public String deleteDraftChaptersByIds(List<UUID> chapterId, UUID authorId);
	
	public ResponseDraftChapterDto  publishDraftedChapters( UUID draftChapterId, UUID authorId);
	
	public List<ResponseDraftChapterDto> getMyDraftChaptersByChapterIds(UUID authorId, Set<UUID> chaterIds);

}

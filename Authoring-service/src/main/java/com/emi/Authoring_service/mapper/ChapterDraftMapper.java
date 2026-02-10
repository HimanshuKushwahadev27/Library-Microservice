package com.emi.Authoring_service.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.emi.Authoring_service.RequestDtos.RequestChapterCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftChapterDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftChapterDto;
import com.emi.Authoring_service.entity.AuthorDraftBook;
import com.emi.Authoring_service.entity.AuthorDraftChapter;
import com.emi.Authoring_service.enums.ChapterStatus;

@Component
public class ChapterDraftMapper {

	public AuthorDraftChapter toEntity(RequestChapterCreateDto request) {
		AuthorDraftChapter draftChapter = new AuthorDraftChapter();
		
		draftChapter.setChapterOrder(request.chapterOrder());
		draftChapter.setDraftBookId(request.draftBookId());
		draftChapter.setTitle(request.title());
		draftChapter.setContent(request.content());
		draftChapter.setFreePreview(request.freePreview());
		draftChapter.setCreatedAt(Instant.now());
		draftChapter.setUpdatedAt(Instant.now());
		draftChapter.setIsDeleted(false);
		draftChapter.setStatus(ChapterStatus.DRAFTED);
		draftChapter.setPrice(request.price());
		
		return draftChapter;
	}

	public ResponseDraftChapterDto toDto(AuthorDraftChapter draftChapter) {
		return new ResponseDraftChapterDto(
				draftChapter.getId(),
				draftChapter.getDraftBookId(),
				draftChapter.getTitle(),
				draftChapter.getChapterOrder(),
				draftChapter.getPrice(),
				draftChapter.getContent(),
				draftChapter.getFreePreview(),
				draftChapter.getStatus()	
				);
	}

	public void toUpdate(RequestUpdateDraftChapterDto request, AuthorDraftChapter draftChapter ) {
		
		draftChapter.setChapterOrder(request.chapterOrder());
		draftChapter.setTitle(request.title());
		draftChapter.setContent(request.content());
		draftChapter.setFreePreview(request.freePreview());
		draftChapter.setUpdatedAt(Instant.now());
		draftChapter.setStatus(request.status());
		draftChapter.setPrice(request.price());
		
	}

}

package com.emi.User_service.mapper;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emi.User_service.entity.Bookmark;
import com.emi.User_service.requestDto.RequestBookmarkDto;
import com.emi.User_service.responseDto.ResponseBookmarkDto;

@Component
public class BookmarkMapper {

	public Bookmark toEntity(RequestBookmarkDto request, UUID keycloakId) {
		Bookmark mark = new Bookmark();
		
		mark.setBookId(request.bookId());
		mark.setChapterId(request.chapterId());
		mark.setKeycloakId(keycloakId);
		mark.setCreatedAt(Instant.now());
		
		return mark;
	}

	public ResponseBookmarkDto toDto(Bookmark mark) {
		return new ResponseBookmarkDto(
				mark.getId(),
				mark.getBookId(),
				mark.getChapterId(),
				mark.getCreatedAt()
				);
	}

}

package com.emi.User_service.responseDto;

import java.time.Instant;
import java.util.UUID;

public record ResponseBookmarkDto(
		UUID id ,
		UUID bookId,
		UUID chapterID,
		Instant createdAt
		) {

}

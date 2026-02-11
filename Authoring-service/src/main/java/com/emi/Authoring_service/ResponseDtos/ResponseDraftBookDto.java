package com.emi.Authoring_service.ResponseDtos;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.emi.Authoring_service.enums.BookLifeCycleStatus;

import com.emi.Authoring_service.enums.BookVisibilityStatus;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description="Response DTO for book draft")
public record ResponseDraftBookDto(
		
		UUID draftBookId,
		UUID authorId,
		String title,
		String description,
		String isbn,
		BigDecimal price,
		BookLifeCycleStatus lifeCycleStatus,	
		BookVisibilityStatus visibilityStatus,
		Boolean freePreview,
		Instant createdAt,
		Instant updatedAt
		
		) {

}

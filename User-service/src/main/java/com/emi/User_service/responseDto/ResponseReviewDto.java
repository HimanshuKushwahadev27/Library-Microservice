package com.emi.User_service.responseDto;

import java.time.Instant;
import java.util.UUID;

public record ResponseReviewDto(
		
		UUID id,
		UUID bookId,
		UUID userId,
		Integer rating,
		String comment,
		Instant createdAt
		) {
}

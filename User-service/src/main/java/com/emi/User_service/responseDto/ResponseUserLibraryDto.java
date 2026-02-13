package com.emi.User_service.responseDto;

import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Response DTO for purchased book in the user's library")
public record ResponseUserLibraryDto(
		UUID id ,
		UUID bookId,
		Instant purchasedAt
		) {

}

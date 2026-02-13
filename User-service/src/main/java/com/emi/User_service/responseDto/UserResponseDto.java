package com.emi.User_service.responseDto;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(
		UUID id,
		String displayName,
		String imageurl,
		String bio,
		Instant createdAt
		) {

}

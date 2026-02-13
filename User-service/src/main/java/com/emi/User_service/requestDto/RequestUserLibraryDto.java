package com.emi.User_service.requestDto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RequestUserLibraryDto(
	   
	    @NotNull(message = "Book id to create the library for user is required")
	    @Schema(
	        description = "Internal book profile ID in catalog-service",
	        example = "e3a424d2-a524-4f65-bb94-e229f73e30fb"
	    )
		UUID bookId
		) {

}

package com.emi.Catalog_Service.RequestDtos;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import com.emi.Catalog_Service.enums.BookLifeCycleStatus;
import com.emi.Catalog_Service.enums.BookVisibilityStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO for creating a new book")
public record RequestBookCreationDto(
		
	    @NotBlank
	    @Schema(
	        description = "Title of the book",
	        example = "Spring Boot Internals"
	    )
		String title,
		
	    @NotBlank
	    @Pattern(
	        regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
	        message = "Invalid ISBN format"
	    )
	    @Schema(
	        description = "ISBN number of the book",
	        example = "9780132350884"
	    )
		String ISBN,
		
	    @NotNull
	    @Positive
	    @Schema(
	        description = "Price of the book",
	        example = "499.99"
	    )
		BigDecimal price,
		
	    @NotNull
	    @Schema(
	        description = "Map of author IDs and their names associated with the book",
	        example = "{\"550e8400-e29b-41d4-a716-446655440000\": \"John Doe\"}"
	    )
		Map<UUID ,String> authorInfo,
		
	    @NotNull
	    @Schema(
	        description = "Map of genre IDs and their names associated with the book",
	        example = "{\"550e8400-e29b-41d4-a716-446655440000\": \"John Doe\"}"
	    )
		Map<UUID, String> genreInfo,
		
	    @NotNull
	    @Schema(
	        description = "Whether free preview is enabled for the book",
	        example = "true"
	    )
		Boolean freePreviewAvailable,
		
	    @NotBlank
	    @Size(max = 1000)
	    @Schema(
	        description = "Detailed description of the book",
	        example = "A deep dive into Spring Boot internals and architecture"
	    )
		String description,
		
		@NotNull
	    @Schema(
	        description = "Publication status of the book",
	        example = "ONGOING, DRAFT, COMPLETED"
	    )
		BookLifeCycleStatus lifeCycleStatus,
		
		@NotNull
	    @Schema(
	        description = "Publication status of the book",
	        example = "PRIVATE, PUBLIC"
	    )
		BookVisibilityStatus visibilityStatus
		) {
}

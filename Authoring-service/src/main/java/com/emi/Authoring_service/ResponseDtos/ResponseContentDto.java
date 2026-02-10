package com.emi.Authoring_service.ResponseDtos;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Published book chapter returned from Catalog")
public record ResponseContentDto(
		

	    @NotBlank
	    @Schema(
	        description = "Unique identifier of the chapter",
	        example = "a12b34cd-5678-90ef-ab12-34567890abcd"
	    )
	    UUID id,

	    @NotBlank
	    @Schema(
	        description = "Identifier of the book this chapter belongs to",
	        example = "f4826cb6-3123-45de-90f0-bfe1a2612164"
	    )
	    UUID bookId,

	    @NotBlank
	    @Schema(
	        description = "Title of the chapter",
	        example = "Introduction to Spring Boot"
	    )
	    String title,

	    @NotNull
	    @Min(1)
	    @Schema(
	        description = "Sequential chapter number",
	        example = "1"
	    )
	    Integer chapterNumber,

	    @NotNull
	    @PositiveOrZero
	    @Schema(
	        description = "Price of the chapter (0 means free)",
	        example = "49.99"
	    )
	    BigDecimal price,

	    @NotBlank
	    @Schema(
	        description = "Full textual content of the chapter",
	        example = "Spring Boot simplifies Java application development by..."
	    )
	    String content,

	    @NotNull
	    @Schema(
	        description = "Whether this chapter is available as a free preview",
	        example = "true"
	    )
	    Boolean freePreview,

	    @NotNull
	    @Schema(
	        description = "Timestamp when the chapter was created",
	        example = "2024-06-01T12:00:00Z"
	    )
	    Instant createdAt,
	    
	    @NotNull
	    @Schema(
	        description = "Timestamp when the chapter was last updated",
	        example = "2024-06-10T15:30:00Z"
	    )
	    Instant updatedAt,

	    @NotBlank
	    @Schema(
	        description = "Publication status of the chapter",
	        example = "PUBLISHED",
	        allowableValues = {"PUBLISHED", "HIDDEN", "DELETED"}
	    )
	    String status
	    
	    
	) {

}
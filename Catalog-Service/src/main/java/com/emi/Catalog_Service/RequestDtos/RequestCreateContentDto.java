package com.emi.Catalog_Service.RequestDtos;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request to publish a chapter into the Catalog")
public record RequestCreateContentDto(
		

	    @NotNull
	    @Schema(
	        description = "Book ID to which this chapter belongs",
	        example = "f4826cb6-3123-45de-90f0-bfe1a2612164"
	    )
	    UUID bookId,

	    @NotBlank
	    @Schema(
	        description = "Title of the chapter",
	        example = "Getting Started with Spring Boot"
	    )
	    String title,

	    @NotNull
	    @Min(1)
	    @Schema(
	        description = "Sequential chapter number within the book",
	        example = "2"
	    )
	    Integer chapterNumber,

	    @NotNull
	    @PositiveOrZero
	    @Schema(
	        description = "Price of the chapter (0 means free)",
	        example = "29.99"
	    )
	    BigDecimal price,

	    @NotBlank
	    @Schema(
	        description = "Final published content of the chapter",
	        example = "In this chapter, we explore the basics of Spring Boot..."
	    )
	    String content,

	    @NotNull
	    @Schema(
	        description = "Whether a free preview is allowed for this chapter",
	        example = "true"
	    )
	    Boolean freePreview
	) {}
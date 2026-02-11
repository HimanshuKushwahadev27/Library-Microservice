package com.emi.Authoring_service.RequestDtos;

import java.math.BigDecimal;
 import java.util.UUID;

import com.emi.Authoring_service.enums.BookLifeCycleStatus;
import com.emi.Authoring_service.enums.BookVisibilityStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Update book request DTO")
public record RequsestBookUpdateDto (
		
	    @NotBlank
	    @Schema(
	        description = "Id of the book in catalog",
	        example = "550e8400-e29b-41d4-a716-446655440000"
	        		)
		UUID bookId,
		
	    @NotNull
	    @Positive
	    @Schema(
	        description = "Price of the book",
	        example = "499.99"
	    )
		BigDecimal price,
		

	    @NotBlank
	    @Size(max = 1000)
	    @Schema(
	        description = "Detailed description of the book",
	        example = "A deep dive into Spring Boot internals and architecture"
	    )
		String description,
		
	    @NotNull
	    @Schema(
	        description = "Whether free preview is enabled for the book",
	        example = "true"
	    )
		Boolean freePreviewAvailable,
		


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
		
		
		)
{

}

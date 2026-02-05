package com.emi.Catalog_Service.RequestDtos;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Schema(description = "Update book request DTO")
public record RequsestBookUpdateDto (
		
	    @NotBlank
	    @Schema(
	        description = "Id of the book",
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
	    @Schema(
	        description = "Title of the book",
	        example = "Spring Boot Internals"
	    )
		String title,
		
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
	        description = "Map of genre IDs and their names associated with the book",
	        example = "{\"550e8400-e29b-41d4-a716-446655440000\": \"John Doe\"}"
	    )
		Map<UUID, String> genreInfo
		)
{

}

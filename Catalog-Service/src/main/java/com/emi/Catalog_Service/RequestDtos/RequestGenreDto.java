package com.emi.Catalog_Service.RequestDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "Genre information requested by the Catalog service")
public record RequestGenreDto(
		
	    @NotBlank
	    @Schema(
	        description = "Name of the genre",
	        example = "Programming"
	    )
	    String name,

	    @NotBlank
	    @Schema(
	        description = "Detailed description of the genre",
	        example = "Books related to software development and programming concepts"
	    )
	    String description
		) {

}

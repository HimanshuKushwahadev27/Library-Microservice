package com.emi.Catalog_Service.ResponseDtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Genre information returned by the Catalog service")
public record ResponseGenreDto(

    @NotNull
    @Schema(
        description = "Unique identifier of the genre",
        example = "660e8400-e29b-41d4-a716-446655440111"
    )
    UUID genreId,

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

) {}
package com.emi.Authoring_service.RequestDtos;

import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


@Schema(description = "Request payload used by an author to publish a draft book")
public record PublishDraftBookRequest(

    @NotNull
    @Schema(
        description = "Identifier of the draft book that is being published",
        example = "a34e8c19-1234-4f8b-9a01-8f12c8d9e111"
    )
    UUID draftBookId,

    @NotNull
    @Schema(
        description = "Map of genre IDs and their names associated with the book",
        example = "{\"550e8400-e29b-41d4-a716-446655440000\": \"John Doe\"}"
    )
    Map<UUID, String> genreInfo,
    
    @NotNull
    @Schema(
        description = "Map of author IDs and their names associated with the book",
        example = "{\"550e8400-e29b-41d4-a716-446655440000\": \"John Doe\"}"
    )
	Map<UUID ,String> authorInfo

) {}
package com.emi.User_service.requestDto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload to create or update a bookmark for a specific chapter")
public record RequestBookmarkDto(

    @NotNull(message = "Book ID is required")
    @Schema(
        description = "Identifier of the published book in Catalog service",
        example = "e3a424d2-a524-4f65-bb94-e229f73e30fb"
    )
    UUID bookId,

    @NotNull(message = "Chapter ID is required")
    @Schema(
        description = "Identifier of the chapter within the book",
        example = "b8f9c4a1-3c2d-4e89-91d3-0a1234567890"
    )
    UUID chapterId

) {}

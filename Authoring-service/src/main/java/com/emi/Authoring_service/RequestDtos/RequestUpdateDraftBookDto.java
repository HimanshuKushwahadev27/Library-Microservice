package com.emi.Authoring_service.RequestDtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.emi.Authoring_service.enums.BookStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Schema(description = "Request payload to update an existing draft book in the Authoring service")
public record RequestUpdateDraftBookDto(

    @NotNull
    @Schema(
        description = "Unique identifier of the draft book to be updated",
        example = "a34e8c19-1234-4f8b-9a01-8f12c8d9e111"
    )
    UUID id,

    @NotNull
    @Schema(
        description = "Identifier of the author who owns this draft book",
        example = "f12e9c44-5678-4b2c-9012-abcdefabcdef"
    )
    UUID authorId,

    @Schema(
        description = "Updated title of the draft book (optional)",
        example = "Advanced Spring Boot Internals"
    )
    String title,

    @Size(max = 2000)
    @Schema(
        description = "Updated description of the draft book (optional)",
        example = "An in-depth exploration of Spring Boot internals and advanced architecture."
    )
    String description,

    @PositiveOrZero
    @Schema(
        description = "Updated price of the book (optional)",
        example = "699.99"
    )
    BigDecimal price,

    @Schema(
        description = "Updated workflow status of the draft book",
        example = "DRAFT",
        allowableValues = {"DRAFT", "READY"}
    )
    BookStatus status,

    @Schema(
        description = "Whether free preview is enabled for the book (optional)",
        example = "true"
    )
    Boolean freePreview

) {}
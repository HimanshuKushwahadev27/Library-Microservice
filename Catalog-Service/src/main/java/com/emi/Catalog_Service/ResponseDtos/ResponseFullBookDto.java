package com.emi.Catalog_Service.ResponseDtos;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.emi.Catalog_Service.Snapshots.AuthorSnapshots;
import com.emi.Catalog_Service.Snapshots.GenreSnapshot;
import com.emi.Catalog_Service.enums.BookLifeCycleStatus;
import com.emi.Catalog_Service.enums.BookVisibilityStatus;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Full book representation used in Catalog responses")
public record ResponseFullBookDto(

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

    @NotEmpty
    @Schema(
        description = "List of author IDs associated with the book",
        example = "[\"550e8400-e29b-41d4-a716-446655440000\"], JOHN DOE"
    )
    List<AuthorSnapshots> authorIds,

    @NotEmpty
    @Schema(
        description = "List of genre IDs associated with the book",
        example = "[\"660e8400-e29b-41d4-a716-446655440111\"], BACKEND DEVELPOMENT"
    )
    List<GenreSnapshot> genreIds,

    @NotBlank
    @Schema(
        description = "Publication status of the book",
        example = "ONGOING, DRAFT, COMPLETED"
    )
	BookLifeCycleStatus lifeCycleStatus,
	
    @NotBlank
    @Schema(
        description = "Publication status of the book",
        example = "PRIVATE, PUBLIC"
    )
	BookVisibilityStatus visibilityStatus,

    @NotNull
    @Min(0)
    @Schema(
        description = "Total number of published chapters",
        example = "12"
    )
    Integer totalChapters,

    @Schema(
        description = "List of published chapter IDs",
        example = "[\"770e8400-e29b-41d4-a716-446655440222\"]"
    )
    List<UUID> chapterIds,

    @NotNull
    @Schema(
        description = "Whether free preview is enabled for the book",
        example = "true"
    )
    Boolean freePreview

) {}

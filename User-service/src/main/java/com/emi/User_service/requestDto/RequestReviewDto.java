package com.emi.User_service.requestDto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload to create or update a book review")
public record RequestReviewDto(

    @NotNull(message = "Book ID is required")
    @Schema(
        description = "Identifier of the published book in Catalog service",
        example = "e3a424d2-a524-4f65-bb94-e229f73e30fb"
    )
    UUID bookId,

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Schema(
        description = "Rating given to the book (1 to 5)",
        example = "5",
        minimum = "1",
        maximum = "5"
    )
    Integer rating,

    @Size(max = 2000, message = "Comment must not exceed 2000 characters")
    @Schema(
        description = "Optional review comment about the book",
        example = "Excellent explanation of distributed systems with real-world examples."
    )
    String comment

) {}
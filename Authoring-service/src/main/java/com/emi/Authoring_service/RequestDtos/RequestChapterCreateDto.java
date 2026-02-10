package com.emi.Authoring_service.RequestDtos;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request payload to create a new draft chapter in the Authoring service")
public record RequestChapterCreateDto(

    @NotNull
    @Schema(
        description = "Identifier of the draft book to which this chapter belongs",
        example = "a34e8c19-1234-4f8b-9a01-8f12c8d9e111"
    )
    UUID draftBookId,

    @NotBlank
    @Schema(
        description = "Title of the draft chapter",
        example = "Understanding Spring Context"
    )
    String title,

    @NotNull
    @Min(1)
    @Schema(
        description = "Order of the chapter within the draft book (used for sequencing)",
        example = "1"
    )
    Integer chapterOrder,

    @NotNull
    @PositiveOrZero
    @Schema(
        description = "Price of the chapter (0 means free)",
        example = "0.00"
    )
    BigDecimal price,

    @NotBlank
    @Schema(
        description = "Draft content of the chapter (editable by the author)",
        example = "In this chapter, we explore how the Spring Context works internally..."
    )
    String content,

    @NotNull
    @Schema(
        description = "Whether a free preview of this chapter is allowed",
        example = "true"
    )
    Boolean freePreview
		
		) {

}

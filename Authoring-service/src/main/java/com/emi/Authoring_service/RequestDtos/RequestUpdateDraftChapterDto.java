package com.emi.Authoring_service.RequestDtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.emi.Authoring_service.enums.ChapterStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


@Schema(description = "Request payload to update an existing draft chapter in the Authoring service")
public record RequestUpdateDraftChapterDto(

    @NotNull
    @Schema(
        description = "Unique identifier of the draft chapter to be updated",
        example = "c12f6a2e-9b8d-4d1f-a72a-91f9d6b4a321"
    )
    UUID id,


    @Schema(
        description = "Updated title of the draft chapter (optional)",
        example = "Refreshed Spring Context Overview"
    )
    String title,

    @Min(1)
    @Schema(
        description = "Updated chapter order within the draft book (optional)",
        example = "2"
    )
    Integer chapterOrder,

    @PositiveOrZero
    @Schema(
        description = "Updated price of the chapter (0 means free)",
        example = "19.99"
    )
    BigDecimal price,

    @Schema(
        description = "Updated free preview flag (optional)",
        example = "false"
    )
    Boolean freePreview,

    @Schema(
        description = "Updated workflow status of the draft chapter",
        example = "DRAFTED",
        allowableValues = {"DRAFTED", "PUBLISHED"}
    )
    ChapterStatus status,
    
    @NotBlank
    @Schema(
        description = "Draft content of the chapter (editable by the author)",
        example = "In this chapter, we explore how the Spring Context works internally..."
    )
    String content


		) {

}

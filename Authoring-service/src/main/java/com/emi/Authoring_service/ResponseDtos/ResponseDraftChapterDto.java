package com.emi.Authoring_service.ResponseDtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.emi.Authoring_service.enums.ChapterStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Draft chapter representation used in Authoring service responses")
public record ResponseDraftChapterDto(

		    @NotNull
		    @Schema(
		        description = "Unique identifier of the draft chapter",
		        example = "c12f6a2e-9b8d-4d1f-a72a-91f9d6b4a321"
		    )
		    UUID id,
		
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
		        example = "3"
		    )
		    Integer chapterOrder,
		
		    @NotNull
		    @PositiveOrZero
		    @Schema(
		        description = "Price of the chapter (0 means free)",
		        example = "29.99"
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
		        description = "Indicates whether a free preview of this chapter is allowed",
		        example = "true"
		    )
		    Boolean freePreview,
		
		    @NotNull
		    @Schema(
		        description = "Workflow status of the draft chapter",
		        example = "DRAFTED",
		        allowableValues = {"DRAFTED" , "PUBLISHED"}
		    )
		    ChapterStatus status
    )
{

}

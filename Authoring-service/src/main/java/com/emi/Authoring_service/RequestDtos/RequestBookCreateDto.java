package com.emi.Authoring_service.RequestDtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.emi.Authoring_service.enums.BookLifeCycleStatus;
import com.emi.Authoring_service.enums.BookVisibilityStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description="Request DTO for book draft")
public record RequestBookCreateDto(
		
        @NotNull(message = "Author ID is required")
        UUID authorId,

        @NotBlank(message = "Title cannot be empty")
        @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
        String title,

        @NotBlank(message = "Description cannot be empty")
        @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
        String description,

        @NotBlank(message = "ISBN is required")
        @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "Invalid ISBN format"
        )
        String isbn,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
        @Digits(integer = 10, fraction = 2, message = "Invalid price format")
        BigDecimal price,

        @NotNull
	    @Schema(
	        description = "Publication status of the book",
	        example = "ONGOING, DRAFT, COMPLETED"
	    )
		BookLifeCycleStatus lifeCycleStatus,
		
	    @NotNull
	    @Schema(
	        description = "Publication status of the book",
	        example = "PRIVATE, PUBLIC"
	    )
		BookVisibilityStatus visibilityStatus,

        @NotNull(message = "Free preview flag is required")
        Boolean freePreview
		) {

}

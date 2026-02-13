package com.emi.User_service.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
		@NotBlank(message = "Display name is required")
	    @Size(min = 3, max = 100, message = "Display name must be between 3 and 100 characters")
	    @Schema(
	        description = "User display name",
	        example = "Himanshu Kushwaha"
	    )
		String displayName,
		

	    @Size(max = 500, message = "Image URL must not exceed 500 characters")
	    @Pattern(
	        regexp = "^(https?://.*)?$",
	        message = "Image URL must be a valid HTTP or HTTPS URL"
	    )
	    @Schema(
	        description = "Profile image URL",
	        example = "https://cdn.library.com/users/profile123.png"
	    )
		String profileImgUrl,
		

	    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
	    @Schema(
	        description = "Short biography of the user",
	        example = "Passionate reader and tech enthusiast."
	    )
		String bio
		) {

}

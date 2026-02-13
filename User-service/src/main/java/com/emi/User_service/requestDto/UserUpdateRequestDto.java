package com.emi.User_service.requestDto;


import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload to update user profile information")
public record UserUpdateRequestDto(

		
    @NotNull(message = "User profile ID is required")
    @Schema(
        description = "Internal user profile ID in user-service",
        example = "e3a424d2-a524-4f65-bb94-e229f73e30fb"
    )
    UUID id,
	    
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
    String imageUrl,

    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
    @Schema(
        description = "Short biography of the user",
        example = "Passionate reader and tech enthusiast."
    )
    String bio

) {}
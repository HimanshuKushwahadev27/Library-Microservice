package com.emi.Author_Service.responseDto;

import java.time.Instant;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload representing an author's public profile")
public record ResponseAuthorDto(

    @Schema(
        description = "Unique identifier of the author profile",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    UUID id,

    @Schema(
        description = "Public pen name of the author",
        example = "Himanshu Kushwaha"
    )
    String penName,

    @Schema(
        description = "Short biography of the author",
        example = "Backend engineer and microservices enthusiast writing about distributed systems."
    )
    String bio,

    @Schema(
        description = "URL of the author's profile image",
        example = "https://cdn.library.com/authors/profile123.png"
    )
    String profileImgUrl,

    @Schema(
        description = "Indicates whether the author is verified by the platform",
        example = "true"
    )
    Boolean isVerified,

    @Schema(
        description = "Timestamp when the author profile was created",
        example = "2026-02-11T18:30:00Z"
    )
    Instant createdAt

) {}
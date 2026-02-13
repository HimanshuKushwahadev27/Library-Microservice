package com.emi.User_service.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "keycloak_id", nullable = false, unique = true)
    private UUID keycloakId;   // JWT sub

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    
    private Boolean isDeleted;
}
package com.emi.Author_Service.entity;

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
@Table(name="authors")
public class Author {

	@Id
	@GeneratedValue
	private UUID id ;
	
    @Column(name = "keycloak_id", nullable = false, unique = true)
	private UUID keycloakId;
    
    @Column(name = "pen_name", nullable = false)
    private String penName;

    @Column(name = "bio", length = 2000)
    private String bio;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "verified", nullable = false)
    private Boolean verified;

   	@Column(name="created_at", nullable=false, updatable=false)
	private Instant createdAt;
	
	@Column(name="updated_at", nullable=false)
	private Instant updatedAt;;
    
	@Column(name="is_deleted", nullable=false)
	private boolean isDeleted;
}

package com.emi.User_service.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookmarks",
       uniqueConstraints = @UniqueConstraint(columnNames = {
           "keycloak_id", "book_id", "chapter_id"
       }))
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "keycloak_id", nullable = false)
    private UUID keycloakId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "chapter_id", nullable = false)
    private UUID chapterId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
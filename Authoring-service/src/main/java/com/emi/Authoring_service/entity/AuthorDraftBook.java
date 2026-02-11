package com.emi.Authoring_service.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.emi.Authoring_service.enums.BookLifeCycleStatus;
import com.emi.Authoring_service.enums.BookVisibilityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="draft_books")
public class AuthorDraftBook {

	@Id
	@GeneratedValue
	UUID id;
	
	@Column(name="author_id", nullable=false)
	private UUID authorId;
	
	@Column(name="catalog_book_id", nullable=true)
	private UUID catalogBookId;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description", nullable=false, length=1000)
	private String description;
	
	@Column(name="ISBN", nullable=false, unique=true)
	private String isbn;
	
	@Column(name="price", nullable=false)
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status_lifecycle", nullable=false)
	private BookLifeCycleStatus statusLifecycle;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status_visible", nullable=false)
	private BookVisibilityStatus statusVisible;
	
	@Column(name="created_at", nullable=false, updatable=false)
	private Instant createdAt;
	
	@Column(name="updated_at", nullable=false)
	private Instant updatedAt;
	
	@Column(name="free_preview", nullable=false)
	private Boolean freePreview;
}

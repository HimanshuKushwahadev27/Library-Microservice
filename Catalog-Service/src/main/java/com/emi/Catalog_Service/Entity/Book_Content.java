package com.emi.Catalog_Service.Entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.emi.Catalog_Service.enums.BookChapter_Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="book_contents")
public class Book_Content {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name="book_id", nullable=false)
	private UUID bookId;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="chapterNumber", nullable=false)
	private Integer chapterNumber;
	
	@Column(name="price", nullable=false)
	private BigDecimal price;
	
	@Lob
	@Column(name="content", nullable=false, columnDefinition="TEXT")
	private String content;
	
	@Column(name="free_preview", nullable=false)
	private Boolean freePreview;	
	
	@Column(name="created_at", nullable=false, updatable=false)
	private Instant createdAt;
	
	@Column(name="updated_at", nullable=false)
	private Instant updatedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private BookChapter_Status status;
	
	private boolean isDeleted;

}

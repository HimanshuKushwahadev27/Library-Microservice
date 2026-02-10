package com.emi.Authoring_service.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.Authoring_service.entity.AuthorDraftChapter;

public interface DraftChapterRepo extends JpaRepository<AuthorDraftChapter, UUID> {

	Optional<List<AuthorDraftChapter>> findByDraftBookId(UUID bookId);

}

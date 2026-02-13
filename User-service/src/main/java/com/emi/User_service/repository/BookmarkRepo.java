package com.emi.User_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.User_service.entity.Bookmark;

public interface BookmarkRepo extends JpaRepository<Bookmark, UUID>  {

	boolean existsByBookIdAndKeycloakId(UUID bookId, UUID keycloakId);

	boolean existsByChapterIdAndKeycloakId(UUID chapterId, UUID keycloakId);

	boolean existsByKeycloakId(UUID keycloakId);

	List<Bookmark> findByKeycloakId(UUID keycloakId);

	boolean existsByIdAndKeycloakId(UUID id, UUID keycloakId);

}

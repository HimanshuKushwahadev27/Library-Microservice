package com.emi.User_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.User_service.entity.Review;

import jakarta.validation.constraints.NotNull;

public interface ReviewRepo extends JpaRepository<Review, UUID> {

	List<Review> findAllByBookId(UUID bookId);

	boolean existsByKeycloakIdAndBookId(UUID keycloakId, @NotNull(message = "Book ID is required") UUID bookId);

}

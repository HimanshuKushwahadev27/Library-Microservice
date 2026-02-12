package com.emi.Author_Service.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.Author_Service.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, UUID> {

	boolean existsByKeycloakId(UUID keycloakId);



}

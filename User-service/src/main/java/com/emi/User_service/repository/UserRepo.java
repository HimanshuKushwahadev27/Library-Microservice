package com.emi.User_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.User_service.entity.User;

public interface UserRepo extends JpaRepository<User, UUID> {

	boolean existsByKeycloakId(UUID keycloakId);

}

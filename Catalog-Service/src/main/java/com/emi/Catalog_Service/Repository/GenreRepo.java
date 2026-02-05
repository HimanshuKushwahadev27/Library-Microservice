package com.emi.Catalog_Service.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.Catalog_Service.Entity.Genre;

public interface GenreRepo extends JpaRepository<Genre, UUID> {


	Optional<Genre> findByName(String name);

}

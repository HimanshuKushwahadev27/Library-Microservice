package com.emi.Catalog_Service.mapper;

import org.springframework.stereotype.Component;

import com.emi.Catalog_Service.Entity.Genre;
import com.emi.Catalog_Service.RequestDtos.RequestGenreDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseGenreDto;

@Component
public class GenreMapper {

	public Genre toEntity(RequestGenreDto request) {
		Genre genre=new Genre();
		
		genre.setDescription(request.description());
		genre.setName(request.name());
		
		return genre;
	}

	public ResponseGenreDto toDto(Genre genre) {
		return new ResponseGenreDto(
				genre.getId(),
				genre.getName(),
				genre.getDescription()
				)
				;
	}

}

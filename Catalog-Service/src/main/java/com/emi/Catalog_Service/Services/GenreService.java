package com.emi.Catalog_Service.Services;

import java.util.List;
import java.util.UUID;


import com.emi.Catalog_Service.RequestDtos.RequestGenreDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseGenreDto;

public interface GenreService {

	public ResponseGenreDto createGenre(RequestGenreDto request);
	public ResponseGenreDto getGenreById(UUID genreID);
	public ResponseGenreDto getGenreByName(String name);
	public List<ResponseGenreDto> getAllGenres();
	
	
}

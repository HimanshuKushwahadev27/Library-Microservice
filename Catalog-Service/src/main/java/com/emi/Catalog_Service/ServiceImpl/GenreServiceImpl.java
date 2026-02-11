package com.emi.Catalog_Service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.Catalog_Service.Entity.Genre;
import com.emi.Catalog_Service.Repository.GenreRepo;
import com.emi.Catalog_Service.RequestDtos.RequestGenreDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseGenreDto;
import com.emi.Catalog_Service.Services.GenreService;
import com.emi.Catalog_Service.exception.GenreNotFoundException;
import com.emi.Catalog_Service.mapper.GenreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	
	private final GenreRepo genreRepo;
	private final GenreMapper genreMapper;
	
	@Override
	public ResponseGenreDto createGenre(RequestGenreDto request) {
		Genre genre= genreMapper.toEntity(request);
		
		genreRepo.save(genre);
		return genreMapper.toDto(genre);
	}

	@Override
	public ResponseGenreDto getGenreById(UUID genreID) {
		Genre genre=genreRepo
				.findById(genreID)
				.orElseThrow(
						() -> new GenreNotFoundException("Genre with the Id " +genreID+ " is not found" )
						);
		return genreMapper.toDto(genre);
	}

	@Override
	public ResponseGenreDto getGenreByName(String name) {
		Genre genre=genreRepo
				.findByName(name)
				.orElseThrow(
						() -> new GenreNotFoundException("Genre with the name " +name+ " is not found" )
						);
		
		return genreMapper.toDto(genre);
	}

	@Override
	public List<ResponseGenreDto> getAllGenres() {
		List<Genre> genres = genreRepo.findAll();
		
		if(genres.isEmpty()) {
			throw  new GenreNotFoundException("No genres created yet" );
		}
		
		return genres.stream().map(genreMapper::toDto).toList();
	}

}

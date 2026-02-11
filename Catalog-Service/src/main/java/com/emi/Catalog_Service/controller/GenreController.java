package com.emi.Catalog_Service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.Catalog_Service.RequestDtos.RequestGenreDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseGenreDto;
import com.emi.Catalog_Service.Services.GenreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books/genres")
public class GenreController {
	
	private final GenreService GenreService;
	
	@PostMapping(value="/create")
	public ResponseEntity<ResponseGenreDto> createGenre(
			@RequestBody @Valid RequestGenreDto request){
		return ResponseEntity.ok(GenreService.createGenre(request));
	}
	
	
	@GetMapping(value="/allGenres")
	public ResponseEntity<List<ResponseGenreDto>> getAllGenres(){
		return ResponseEntity.ok(GenreService.getAllGenres());
	}
	
	
	@GetMapping(value="/genreId/{genreId}")
	public ResponseEntity<ResponseGenreDto> getGenreById(
			@PathVariable UUID genreId){
		return ResponseEntity.ok(GenreService.getGenreById(genreId));
	}
	
	@GetMapping(value="/name/{name}")
	public ResponseEntity<ResponseGenreDto> getGenreByName(
			@PathVariable String name){
		return ResponseEntity.ok(GenreService.getGenreByName(name));
	}


}

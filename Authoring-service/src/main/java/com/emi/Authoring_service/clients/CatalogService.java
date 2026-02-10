package com.emi.Authoring_service.clients;


import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emi.Authoring_service.RequestDtos.RequestBookCreationDto;
import com.emi.Authoring_service.RequestDtos.RequestCreateContentDto;
import com.emi.Authoring_service.RequestDtos.RequsestBookUpdateDto;
import com.emi.Authoring_service.ResponseDtos.ResponseBookDto;
import com.emi.Authoring_service.ResponseDtos.ResponseContentDto;

import jakarta.validation.Valid;

@FeignClient(value="Catalog-service",
             url = "http://Catalog-service:8080")
public interface CatalogService {

	@PostMapping(value="/create")
	public ResponseEntity<ResponseBookDto> createBook(
			@RequestBody @Valid RequestBookCreationDto request);
	
	@PostMapping(value="/create")
	public ResponseEntity<List<ResponseContentDto>> createMultipleBookContents(
			@RequestBody @Valid List<RequestCreateContentDto> request);
	
	@PatchMapping(value="/update/{authorId}")
	public ResponseEntity<ResponseBookDto> updateBook(
			@RequestBody @Valid RequsestBookUpdateDto request, @PathVariable UUID authorId);
}

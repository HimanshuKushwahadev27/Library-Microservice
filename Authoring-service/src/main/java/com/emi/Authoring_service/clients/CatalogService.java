package com.emi.Authoring_service.clients;


import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
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

@FeignClient(value="catalog-service",
             url = "http://catalog-service:8080")
public interface CatalogService {

	@PostMapping(value="/api/books/create")
	public UUID createBook(
			@RequestBody @Valid RequestBookCreationDto request);
	
	@PostMapping(value="/api/books/contents/create")
	public List<ResponseContentDto> createMultipleBookContents(
			@RequestBody @Valid List<RequestCreateContentDto> request);
	
	@PatchMapping(value="api/books/update/{authorId}")
	public ResponseBookDto updateBook(
			@RequestBody @Valid RequsestBookUpdateDto request, @PathVariable UUID authorId);
}

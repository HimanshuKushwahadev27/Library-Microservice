package com.emi.User_service.service;

import java.util.List;
import java.util.UUID;

import com.emi.User_service.requestDto.RequestReviewDto;
import com.emi.User_service.responseDto.ResponseReviewDto;

public interface ReviewService {

	public ResponseReviewDto create(RequestReviewDto request, UUID keycloakId);
	
	public List<ResponseReviewDto> get(UUID bookId);
	
	public String delete(UUID id);
}

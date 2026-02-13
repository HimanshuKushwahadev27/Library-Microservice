package com.emi.User_service.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emi.User_service.entity.Review;
import com.emi.User_service.requestDto.RequestReviewDto;
import com.emi.User_service.responseDto.ResponseReviewDto;

@Component
public class ReviewMapper {

	public Review toEntity(RequestReviewDto request, UUID keycloakId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseReviewDto toDto(Review review) {
		// TODO Auto-generated method stub
		return null;
	}

}

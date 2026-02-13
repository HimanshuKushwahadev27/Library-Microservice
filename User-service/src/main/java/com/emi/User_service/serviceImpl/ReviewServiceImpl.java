package com.emi.User_service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.User_service.entity.Review;
import com.emi.User_service.exception.ReviewNotFoundException;
import com.emi.User_service.mapper.ReviewMapper;
import com.emi.User_service.repository.ReviewRepo;
import com.emi.User_service.requestDto.RequestReviewDto;
import com.emi.User_service.responseDto.ResponseReviewDto;
import com.emi.User_service.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;
	private final ReviewRepo reviewRepo;

	@Override
	public ResponseReviewDto create(RequestReviewDto request, UUID keycloakId) {

		if(reviewRepo.existsByKeycloakIdAndBookId(keycloakId, request.bookId())) {
			throw new IllegalArgumentException("You can't review again");
		}
		Review review = reviewMapper.toEntity(request, keycloakId);
		reviewRepo.save(review);
		return reviewMapper.toDto(review);
	}

	@Override
	public List<ResponseReviewDto> get(UUID bookId) {
		List<Review> review = reviewRepo.findAllByBookId(bookId);

		if (review.isEmpty()) {
			throw new ReviewNotFoundException("no reviews for the book with id " + bookId + " yet");
		}

		return review.stream().map(reviewMapper::toDto).toList();
	}

	@Override
	public String delete(UUID id) {
		Review review = reviewRepo.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("No review found with the id " + id));
		
		reviewRepo.deleteById(review.getId());
		
		return "Review removed successfully";
	}

}

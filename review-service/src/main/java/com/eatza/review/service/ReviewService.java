package com.eatza.review.service;

import java.util.List;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.model.Review;

public interface ReviewService {

	Review getReview(Integer id);
	Review submitReview(ReviewDto reveiwDto);
	boolean updateReview(ReviewDto customerDto, Integer reviewId);
	boolean deleteReview(Integer reviewId);
	List<Review> getAllReviews();
}

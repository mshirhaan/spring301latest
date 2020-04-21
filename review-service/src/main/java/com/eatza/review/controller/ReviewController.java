package com.eatza.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;
import com.eatza.review.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired ReviewService reviewService;
	//@Autowired CustomerServiceProxy customerServiceProxy;
	
	@GetMapping("/review/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Integer reviewId) {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(reviewService.getReview(reviewId));
	}
	
	@GetMapping("/review/reviews")
	public ResponseEntity<List<Review>> getAllReviews() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(reviewService.getAllReviews());
	}
	
	@PostMapping("/review")
	public ResponseEntity<Review> submitReview(@RequestBody ReviewDto reviewDto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(reviewService.submitReview(reviewDto));
	}
	
//	@PostMapping("/review/submitReview-feign")
//	public ResponseEntity<Review> submitReviewFeign(@RequestBody ReviewDto reviewDto) {
//		Customer customer = customerServiceProxy.getCustomer(reviewDto.getCustomerId());
//		reviewDto.setCustomerFirstName(customer.getFirstName());
//		reviewDto.setCustomerLastName(customer.getLastName());
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.body(reviewService.submitReview(reviewDto));
//	}
	
	
	@PutMapping("/review/{reviewId}")
	public ResponseEntity<String> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer reviewId) {
		boolean result = reviewService.updateReview(reviewDto, reviewId);
		if(result) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Review updated Successfully");
		} else {
			throw new ReviewNotFoundException("No records found for respective id");
		}	
	}
	
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) {
		boolean result = reviewService.deleteReview(reviewId);
		if(result) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Review deleted Successfully");
		} else {
			throw new ReviewNotFoundException("No records found for respective id");
		}	
	}
}

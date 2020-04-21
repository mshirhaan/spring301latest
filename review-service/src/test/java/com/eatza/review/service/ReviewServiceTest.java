package com.eatza.review.service;

import static org.junit.Assert.assertEquals;
import static  org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;

@RunWith(SpringRunner.class)
public class ReviewServiceTest {

	@InjectMocks ReviewServiceImpl reviewService;
	@Mock ReviewRepository reviewRepository;
	
	
	Review review;
	ReviewDto reviewDto;
	
	@Before
	public void createStubs() {
		review = new Review(1, 1, 1, 5, "Good");
		reviewDto = new ReviewDto(review);		
	}
	
	@After
	public void deleteStubs() {
		review = null;
		reviewDto = null;	
	}
	
	@Test
	public void findById() {
		when(reviewRepository.findById(Mockito.anyInt()))
			.thenReturn(Optional
					.of(new Review(1,1,1,5,"Good")));
		
		Review review = reviewService.getReview(1);
		assertEquals("Good", review.getReview());
	}
	
	@Test(expected = ReviewNotFoundException.class)
	public void findById_NotFound() {
		when(reviewRepository.findById(Mockito.anyInt()))
			.thenReturn(Optional.empty());
		reviewService.getReview(1);
	}
	
	@Test
	public void submitReview() {
		when(reviewRepository.save(any(Review.class))).thenReturn(review);	
		Review reviewTest = reviewService.submitReview(reviewDto);
		assertEquals("Good", reviewTest.getReview());
	}
	
	@Test
	public void updateReview() {
		when(reviewRepository.findById(Mockito.anyInt()))
		.thenReturn(Optional
				.of(new Review(1,1,1,1,"Bad")));
		boolean updatedStatus = reviewService.updateReview(reviewDto, 1);
		assertEquals(true, updatedStatus);
	}
	
	@Test
	public void updateReviewNotFound() {
		when(reviewRepository.findById(Mockito.anyInt()))
		.thenReturn(Optional.empty());
		boolean updatedStatus = reviewService.updateReview(reviewDto, 2);
		assertEquals(false, updatedStatus);
	}
	
	@Test
	public void deleteReviewNotFound() {
		when(reviewRepository.findById(Mockito.anyInt()))
		.thenReturn(Optional.empty());
		boolean updatedStatus = reviewService.deleteReview(2);
		assertEquals(false, updatedStatus);
	}
	
	@Test
	public void deleteReviewFound() {
		when(reviewRepository.findById(Mockito.anyInt()))
		.thenReturn(Optional
				.of(new Review(1,1,1,1,"Bad")));
		boolean updatedStatus = reviewService.deleteReview(1);
		assertEquals(true, updatedStatus);
	}
}

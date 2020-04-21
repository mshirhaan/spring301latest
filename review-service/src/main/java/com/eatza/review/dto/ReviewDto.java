package com.eatza.review.dto;

import com.eatza.review.model.Review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ReviewDto {

	private Integer customerId;
	private Integer restaurantId;
	private int rating;
	private String review;
	private String customerFirstName;
	private String customerLastName;
	
	public ReviewDto(Review review) {
		super();
		this.customerId = review.getCustomerId();
		this.restaurantId = review.getRestaurantId();
		this.rating = review.getRating();
		this.review = review.getReview();
	}
}

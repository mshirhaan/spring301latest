package com.eatza.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eatza.review.dto.ReviewDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="reviews")
@NoArgsConstructor
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    
    private Integer customerId;
    private Integer restaurantId;
    private int rating;
    private String review;
//    private String customerFirstName;
//    private String customerLastName;
    
	public Review(ReviewDto reviewDto) {

		this.customerId = reviewDto.getCustomerId();
		this.restaurantId = reviewDto.getRestaurantId();
		this.rating = reviewDto.getRating();
		this.review = reviewDto.getReview();
//		this.customerFirstName = reviewDto.getCustomerFirstName();
//		this.customerLastName = reviewDto.getCustomerLastName()
	}

	public Review(Integer id, Integer customerId, Integer restaurantId, int rating, String review) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
		this.review = review;
	}
    
}
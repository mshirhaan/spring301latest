package com.eatza.review.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.review.model.Review;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired ReviewRepository reviewRepository;
	
	@Test
	public void findById() {
		Review reviews =reviewRepository.findById(1).get();
		assertEquals(5, reviews.getRating());
	}
	@Test
	public void findAll() {
		List<Review> reviews =reviewRepository.findAll();
		assertEquals(4, reviews.size());
	}
}

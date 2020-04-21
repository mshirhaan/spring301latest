package com.eatza.review.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;
import com.eatza.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ReviewController.class)
public class ReviewControllerTest {

	@MockBean ReviewService reviewService;
	//@MockBean CustomerServiceProxy customerServiceProxy;
	
	@Autowired MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	Review review;
	ReviewDto reviewDto;
	List<Review> reviews;
	
	@Before
	public void createStubs() {
		review = new Review(1, 1, 1, 5, "Good");
		reviewDto = new ReviewDto(review);
		reviews = new ArrayList<>();
		reviews.add(review);
		reviews.add(new Review(2,2,2,4,"Nice"));
	}
	
	@After
	public void deleteStubs() {
		review = null;
		reviewDto = null;
		reviews.clear();
	}
	
	@Test
	public void getReview_Exists() throws Exception{
		Mockito.when(reviewService.getReview(Mockito.anyInt())).thenReturn(review);
		RequestBuilder request = MockMvcRequestBuilders.get("/review/1").accept(MediaType.ALL);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void getReview_Not_Found() throws Exception{
		Mockito.when(reviewService.getReview(Mockito.anyInt())).thenThrow(new ReviewNotFoundException());
		RequestBuilder request = MockMvcRequestBuilders.get("/review/100").accept(MediaType.ALL);
		mockMvc.perform(request).andExpect(status().is(404)).andReturn();
	}
	
	@Test
	public void getAllReviews() throws Exception{
		Mockito.when(reviewService.getAllReviews()).thenReturn(reviews);
		RequestBuilder request = MockMvcRequestBuilders.get("/review/reviews").accept(MediaType.ALL);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void submitReview() throws Exception{
		Mockito.when(reviewService.submitReview(Mockito.any(ReviewDto.class))).thenReturn(review);
		RequestBuilder request = MockMvcRequestBuilders.post("/review").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((reviewDto))).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(201)).andReturn();
		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void updateReviewFound() throws Exception{
		Mockito.when(reviewService.updateReview(Mockito.any(ReviewDto.class), Mockito.anyInt())).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/review/1").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((reviewDto))).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void updateReviewNotFound() throws Exception{
		Mockito.when(reviewService.updateReview(Mockito.any(ReviewDto.class), Mockito.anyInt())).thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders.put("/review/100").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((reviewDto))).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(404)).andReturn();
		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}
	
	@Test
	public void deleteReview_NotFound() throws Exception{
		Mockito.when(reviewService.deleteReview(Mockito.anyInt())).thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders.delete("/review/200").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(404)).andReturn();
		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}
	
	@Test
	public void deleteReview_Found() throws Exception{
		Mockito.when(reviewService.deleteReview(Mockito.anyInt())).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/review/100").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}

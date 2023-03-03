package com.coducer.review.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.powermock.modules.junit4.PowerMockRunner;
import com.coducer.review.Entity.Review;
import com.coducer.review.Exception.UnauthorizedException;
import com.coducer.review.Repository.ReviewRepository;
import com.coducer.review.controller.ReviewController;

@SpringBootTest
@RunWith(value = PowerMockRunner.class)
public class ReviewControllerTest {

	@Mock
	private ReviewRepository reviewRepository;

	@InjectMocks
	private ReviewController reviewController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetReview() {
		Long id = 1L;
		Review review = new Review();
		review.setId(id);
		when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
		assertEquals(review, reviewController.getReview(id));
	}

	@Test
	public void testGetReviewNotFound() {
		Long id = 1L;
		when(reviewRepository.findById(id)).thenReturn(Optional.empty());
		reviewController.getReview(id);
	}

	@Test
	public void testAddReview() {
		Review review = new Review();
		when(reviewRepository.save(any(Review.class))).thenReturn(review);
		assertEquals(review, reviewController.addReview(review));
	}

	@Test
	public void testDeleteReview() {
		Long id = 1L;
		doNothing().when(reviewRepository).deleteById(id);
		reviewController.deleteReview(id);
	}

	@Test
	public void testGetToken() {
		String username = "user1";
		String password = "pass1";
		assertNotNull(reviewController.getToken(username, password));
	}

	@Test
	public void testGetTokenInvalidCredentials() {
		String username = "user3";
		String password = "pass3";
		reviewController.getToken(username, password);
	}

}
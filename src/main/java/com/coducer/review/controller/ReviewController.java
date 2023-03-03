package com.coducer.review.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coducer.review.Entity.Review;
import com.coducer.review.Exception.UnauthorizedException;
import com.coducer.review.Repository.ReviewRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service/v1")
public class ReviewController {
	@Autowired
	private ReviewRepository reviewRepository;
	private Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
	private static Map<String, String> tokens = new HashMap<>();

	@PostMapping("/token")
	public String getToken(@RequestParam String username, @RequestParam String password) {
		LOGGER.info("Inside ReviewController class getToken() username and password", username, password);
		if (isValidUser(username, password)) {

			String token = UUID.randomUUID().toString();

			tokens.put(username, token);

			return token;
		} else {
			throw new UnauthorizedException("Invalid username or password");
		}

	}

	@GetMapping("/review/{id}")
	public Review getReview(@PathVariable Long id) {
		LOGGER.info("Inside ReviewController class getReview() id", id);
		return reviewRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/review")
	public Review addReview(@RequestBody Review review) {
		LOGGER.info("Inside ReviewController class addReview() review", review);
		return reviewRepository.save(review);
	}

	@DeleteMapping("/review/{id}")
	public void deleteReview(@PathVariable Long id) {
		LOGGER.info("Inside ReviewController class deleteReview() id", id);
		reviewRepository.deleteById(id);
	}

	private boolean isValidUser(@Valid String username,@Valid String password) {
		LOGGER.info("Inside ReviewController class isValidUser() username,  password", username, password);
		if (username.equals("user1") && password.equals("pass1")) {
			return true;
		} else if (username.equals("user2") && password.equals("pass2")) {
			return true;
		} else {
			return false;
		}
	}
}

package com.coducer.review.ControllerTest;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coducer.review.Entity.Review;
import com.coducer.review.Exception.UnauthorizedException;
import com.coducer.review.Repository.ReviewRepository;
import com.coducer.review.controller.ReviewController;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerEndpointsTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testGetReview() throws Exception {
        Review review = new Review();
        review.setId(1L);


        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));

        mockMvc.perform(get("/service/v1/review/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testAddReview() throws Exception {
        Review review = new Review();
        review.setId(1L);
  

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        mockMvc.perform(post("/service/v1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
               
    }

    @Test
    public void testDeleteReview() throws Exception {
        doNothing().when(reviewRepository).deleteById(anyLong());

        mockMvc.perform(delete("/service/v1/review/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetToken() throws Exception {
        mockMvc.perform(post("/service/v1/token")
                .param("username", "user1")
                .param("password", "pass1"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testGetTokenWithInvalidCredentials() throws Exception {
//        when(reviewController.isValidUser(any(), any())).thenReturn(false);
//
//        mockMvc.perform(post("/service/v1/token")
//                .param("username", "invalid")
//                .param("password", "invalid"))
//                .andExpect(status().isUnauthorized())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnauthorizedException));
//    }
    @Test
    public void testIsValidUser() throws Exception {
        ReviewController controller = new ReviewController();
        String username = "user1";
        String password = "pass1";
        Method method = ReviewController.class.getDeclaredMethod("isValidUser", String.class, String.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(controller, username, password);
        Assert.assertTrue(result);
    }
}

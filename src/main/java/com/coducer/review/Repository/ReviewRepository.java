package com.coducer.review.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coducer.review.Entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}

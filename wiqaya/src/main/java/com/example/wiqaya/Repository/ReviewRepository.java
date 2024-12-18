package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);

    @Query("SELECT r FROM Review r WHERE r.serviceProvider.name = :name")
    List<Review> findReviewsByServiceProviderName( String name);

    @Query("SELECT r FROM Review r WHERE r.rating > :rating")
    List<Review> findReviewsByRatingGreaterThan(Double rating);

}

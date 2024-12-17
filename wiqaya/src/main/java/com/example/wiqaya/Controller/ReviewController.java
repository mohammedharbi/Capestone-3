package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.Model.Review;
import com.example.wiqaya.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wigaya/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllReviews(){
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @PostMapping("/add/user-id/{user_id}/provider-id/{provider_id}")
    public ResponseEntity<?> addReview(@PathVariable Integer user_id, @PathVariable Integer provider_id, @RequestBody @Valid Review review){
      reviewService.addReview(user_id,provider_id,review);
      return ResponseEntity.status(200).body(new ApiResponse("Review added"));
    }

    @PutMapping("/update/review-id/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,@RequestBody @Valid Review review){
      reviewService.updateReview(id,review);
      return ResponseEntity.status(200).body(new ApiResponse("Review updated"));
    }

    @DeleteMapping("/delete/review-id/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
      reviewService.deleteReview(id);
      return ResponseEntity.status(200).body(new ApiResponse("Review deleted"));
    }


}

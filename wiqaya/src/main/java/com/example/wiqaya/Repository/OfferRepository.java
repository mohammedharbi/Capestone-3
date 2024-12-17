package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    Offer findOfferById(Integer id);
}

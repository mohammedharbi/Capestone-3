package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Integer> {
 ServiceProvider findServiceProviderById(Integer id);

 List<ServiceProvider> findServiceProviderByDoneOrdersNumGreaterThanEqual(Integer doneOrdersNum);

// @Query("select s from ServiceProvider s where s.averageRating>=?1")
// List<ServiceProvider> findServiceProviderByAverageRatingGreaterThanEqual(Double averageRating);
}

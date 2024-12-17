package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    // query to find users with the role 'user'
    @Query("select u from User u where u.role='user'")
    List<User> findByRole(String role);

}

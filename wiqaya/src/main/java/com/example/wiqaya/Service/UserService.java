package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // add new user
    public void addUser(User user){
        user.setRole("user");
       userRepository.save(user);
    }

    // update existing user info
    public void updateUser(Integer id,User user){
      User oldUser = userRepository.findUserById(id);
      if(oldUser==null){
          throw new ApiException("User not found!");
      }
      oldUser.setName(user.getName());
      oldUser.setEmail(user.getEmail());
      oldUser.setUsername(user.getUsername());
      oldUser.setPassword(user.getPassword());
      oldUser.setPhoneNumber(user.getPhoneNumber());
      userRepository.save(oldUser);
    }

    // delete user
    public void deleteUser(Integer id){
      User user = userRepository.findUserById(id);
      if(user==null){
          throw new ApiException("User not found!");
      }
      userRepository.delete(user);
    }

}

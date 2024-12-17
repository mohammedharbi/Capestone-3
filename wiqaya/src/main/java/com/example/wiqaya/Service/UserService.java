package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.DTO.OUT.UserDTOOUT;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // get all users
    public List<UserDTOOUT> getAllUsers(){
        List<User> users = userRepository.findByRole("user"); // well get the users only (not the admin)
     List<UserDTOOUT> userDTOOUTS = new ArrayList<>();
     for (User u : users){

       UserDTOOUT userDTOOUT = new  UserDTOOUT(u.getName(),u.getEmail(),u.getPhoneNumber(),u.getUsername());
       userDTOOUTS.add(userDTOOUT);
     }
        return userDTOOUTS;
    }

    // add new user
    public void addUser(UserDTOIN userDTOIN){
       User user = new User(null,userDTOIN.getName(),userDTOIN.getEmail(),userDTOIN.getPhoneNumber(),userDTOIN.getUsername(),userDTOIN.getPassword(),"user",null);
       userRepository.save(user);
    }

    // update existing user info
    public void updateUser(Integer id,UserDTOIN userDTOIN){
      User oldUser = userRepository.findUserById(id); // check user is existed !
      if(oldUser==null){
          throw new ApiException("User not found!");
      } // update user info
      oldUser.setName(userDTOIN.getName());
      oldUser.setEmail(userDTOIN.getEmail());
      oldUser.setUsername(userDTOIN.getUsername());
      oldUser.setPassword(userDTOIN.getPassword());
      oldUser.setPhoneNumber(userDTOIN.getPhoneNumber());
      userRepository.save(oldUser); // save the user to the DB
    }

    // delete user
    public void deleteUser(Integer id){
      User user = userRepository.findUserById(id); // check user is existed !
      if(user==null){
          throw new ApiException("User not found!");
      }
      userRepository.delete(user); // delete the user
    }

}

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
     List<User> users = userRepository.findAll();
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
      User oldUser = userRepository.findUserById(id);
      if(oldUser==null){
          throw new ApiException("User not found!");
      }
      oldUser.setName(userDTOIN.getName());
      oldUser.setEmail(userDTOIN.getEmail());
      oldUser.setUsername(userDTOIN.getUsername());
      oldUser.setPassword(userDTOIN.getPassword());
      oldUser.setPhoneNumber(userDTOIN.getPhoneNumber());
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

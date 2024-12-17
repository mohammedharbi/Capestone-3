package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.DTO.OUT.UserDTOOUT;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.EngineerRepository;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EngineerRepository engineerRepository;

    // get all users
    public List<UserDTOOUT> getAllUsers(){
        List<User> users = userRepository.findUserByRole(); // well get the users only (not the admin)
     List<UserDTOOUT> userDTOOUTS = new ArrayList<>();
     for (User u : users){

       UserDTOOUT userDTOOUT = new  UserDTOOUT(u.getId(),u.getName(),u.getEmail(),u.getPhoneNumber(),u.getUsername());
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

    // -----------------

    // // Endpoint No.1
    // admin check Eng isVerified
    public void verifiedEng(Integer userId,Integer engId,String status){
     User user = userRepository.findUserById(userId);
     if(user==null){
        throw new ApiException("User not found");
     }
     if(user.getRole().equals("user")){
         throw new ApiException("Not Authorized user");
        }
        Engineer engineer = engineerRepository.findEngineerById(engId);
     if(engineer==null){
         throw new ApiException("Engineer not found");
     }
        if (!status.equalsIgnoreCase("Approved") && !status.equalsIgnoreCase("Rejected")) {
            throw new ApiException("Invalid status. Status must be 'Approved' or 'Rejected'.");
        }
        engineer.setStatus(status);
     if(status.equalsIgnoreCase("Approved")){
         engineer.setAvailability(true);
     }
     engineerRepository.save(engineer);
    }

    // Endpoint No.2
    // assign eng to a requestInspection
}

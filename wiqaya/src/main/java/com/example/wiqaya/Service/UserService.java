package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.DTO.OUT.UserDTOOUT;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Model.ServiceProvider;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.EngineerRepository;
import com.example.wiqaya.Repository.RequestInspectionRepository;
import com.example.wiqaya.Repository.ServiceProviderRepository;
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
    private final ServiceProviderRepository serviceProviderRepository;
    private final RequestInspectionRepository requestInspectionRepository;

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
       User user = new User(null,userDTOIN.getName(),userDTOIN.getEmail(),userDTOIN.getPhoneNumber(),userDTOIN.getUsername(),userDTOIN.getPassword(),"user",null,null);
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
      user.getHouses().clear();
      userRepository.delete(user); // delete the user
    }

    // -----------------


    // // Endpoint No.1
    //Sara
    // admin check Eng isVerified
    public void verifiedEng(Integer userId, Integer engId, String status, String rejectionReason) {
        // Verify if the user exists
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        // Check if the user has admin privileges
        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new ApiException("Not Authorized: Only admin can perform this action");
        }
        Engineer engineer = engineerRepository.findEngineerById(engId);
        if (engineer == null) {
            throw new ApiException("Engineer not found");
        }
        if (!status.equalsIgnoreCase("Approved") && !status.equalsIgnoreCase("Rejected")) {
            throw new ApiException("Invalid status");
        }
        // Update engineer's status
        engineer.setStatus(status);

        if (status.equalsIgnoreCase("Approved")) {
            engineer.setAvailability(true); // Set the engineer as available if approved
        } else if (status.equalsIgnoreCase("Rejected")) {
            engineer.setAvailability(false); // Set the engineer as unavailable if rejected

            if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                throw new ApiException("Rejection reason must be provided when rejecting an engineer");
            }
            engineer.setRejectionReason(rejectionReason);
        }
        engineerRepository.save(engineer);
    }



    // Endpoint No.2
    //sara
    // Admin will check the license of the ServiceProvider and decide the status
    public void verifiedProvider(Integer userId, Integer providerId, String status, String rejectionReason) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new ApiException("Not Authorized: Only admin can perform this action");
        }

        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(providerId);
        if (serviceProvider == null) {
            throw new ApiException("ServiceProvider not found");
        }

        if (!status.equalsIgnoreCase("Approved") && !status.equalsIgnoreCase("Rejected")) {
            throw new ApiException("Invalid status");
        }
        serviceProvider.setStatus(status);
            if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                throw new ApiException("Rejection reason must be provided when rejecting an engineer");
        }
        serviceProvider.setRejectionReason(rejectionReason);
        serviceProviderRepository.save(serviceProvider);
    }


    // Endpoint No.3
    //sara
    // user will display requestsInceptions that he owns()
    public List<RequestInspection> getAllRequestForOneUser(Integer userId){
     List<RequestInspection> requestInspections = requestInspectionRepository.findAllRequestForOneUser(userId);
     if(requestInspections==null){
         throw new ApiException("No requests found");
     }
     return requestInspections;
    }

    // Endpoint No.4
    //Mohammed
    public List<Engineer> getAllEngUnderReview(Integer adminId){
        User admin = userRepository.findUserById(adminId);
        if(admin==null){throw new ApiException("User not found");}
        if (!admin.getRole().equalsIgnoreCase("admin")) {
            throw new ApiException("Not Authorized: Only admin can perform this action");
        }
        List<Engineer> engineers = engineerRepository.findEngineerByStatusEquals("UnderReview");
        if (engineers.isEmpty()) {throw new ApiException("No engineers found UnderReview");}
        return engineers;
    }



 }

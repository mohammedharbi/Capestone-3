package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // CRUD

    // Get
    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // Create .. add
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTOIN user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    // update .. put
    @PutMapping("/update/user-id/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody @Valid UserDTOIN user){
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }

    // delete
    @DeleteMapping("/delete/user-id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    // --------------------------------------------------
}

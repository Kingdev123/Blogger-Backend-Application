package com.blogapplication.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.Payload.ApiResponse;
import com.blogapplication.Payload.UserDTO;
import com.blogapplication.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    // post create user"/api/users
    @PostMapping("/")
    public ResponseEntity<UserDTO> CreateUser (@Valid @RequestBody UserDTO userDto){
        UserDTO CreateUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(CreateUserDto, HttpStatus.CREATED);
    }
     
    // put update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> UpdateUser (@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId){
        UserDTO updatedUserDto = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUserDto);
    }

    // delete delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> DeleteUser (@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true, LocalDateTime.now()) , HttpStatus.OK);
    }
    
    // get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> GetUserById (@PathVariable Integer userId){
        return new ResponseEntity<UserDTO>(this.userService.getUserById(userId), HttpStatus.FOUND);
    } 

    // get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser (){
        return new ResponseEntity<List<UserDTO>>(this.userService.getAllUser(), HttpStatus.OK);
    }
    
}

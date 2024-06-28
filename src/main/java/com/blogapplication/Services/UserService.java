package com.blogapplication.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapplication.Payload.UserDTO;

@Service
public interface UserService {
    
    UserDTO createUser (UserDTO user);

    UserDTO updateUser (UserDTO user, Integer userId);
    
    UserDTO getUserById (Integer userId);
    
    List<UserDTO> getAllUser ();
    
    void deleteUser(Integer userId);

}

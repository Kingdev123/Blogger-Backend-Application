package com.blogapplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Entity.User;
import com.blogapplication.Exception.ResourceNotFound;
import com.blogapplication.Payload.UserDTO;
import com.blogapplication.Repository.UserRepository;

@Service
public class userServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapping;

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepository.save(user);

        return this.UserToDto(savedUser);
    }

    @Override
    public UserDTO updateUser (UserDTO userDto, Integer userId) {

        User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFound("user", "Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBio(userDto.getBio());

        User updatedUser = this.userRepository.save(user);
        
        return this.UserToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFound("user", "Id", userId));

        return this.UserToDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList()); 

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFound("user", "id", userId));
        this.userRepository.delete(user); 
    }

    public User DtoToUser (UserDTO userDto){
        User user =  this.modelMapping.map(userDto, User.class);
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setBio(userDto.getBio());
        return user;
    }

    public UserDTO UserToDto (User user){
        UserDTO userDto =  this.modelMapping.map(user, UserDTO.class);
        return userDto ;
    }
    
}

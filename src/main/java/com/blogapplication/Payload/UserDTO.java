package com.blogapplication.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty; 
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    
    private int id;

    @NotEmpty
    @Size(min = 3, message = "name must be of 3 characters !!!")
    private String name;

    @Email(message = "email is not valid !!!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 4, message = "password should be in the range of 3 to 10 characters !!!")
    private String password;

    @NotEmpty
    private String bio;
}

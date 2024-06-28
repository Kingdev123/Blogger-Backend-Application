package com.blogapplication.Payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;
    
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private Date addedDate;

    private String imageName;

    private CategoryDTO category;

    private UserDTO user;

    private Set<CommentDTO> comments =  new HashSet<>();
}

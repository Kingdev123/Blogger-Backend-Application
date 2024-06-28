package com.blogapplication.Services;


import com.blogapplication.Payload.CommentDTO;


public interface CommentService {

    CommentDTO CreateComment(CommentDTO commentDto, Integer postId);

    void deleteComment(Integer commentId);
    
    
}

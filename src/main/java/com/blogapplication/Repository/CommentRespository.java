package com.blogapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.Entity.Comment;

public interface CommentRespository extends JpaRepository<Comment, Integer>{
    
}

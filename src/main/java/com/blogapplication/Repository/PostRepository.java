package com.blogapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.Entity.Category;
import com.blogapplication.Entity.Post;
import com.blogapplication.Entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
    
}

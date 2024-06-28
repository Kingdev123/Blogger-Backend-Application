package com.blogapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}

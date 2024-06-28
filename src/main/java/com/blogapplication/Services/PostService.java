package com.blogapplication.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapplication.Payload.PostDTO;
import com.blogapplication.Payload.PostResponse;
  
@Service
public interface PostService {

    // create post
    PostDTO createPost(PostDTO postDto,Integer userId, Integer categoryId); 

    // update post
    PostDTO updatePost(PostDTO postDto, Integer postId); 

    // delete post
    void deletePost(Integer postId); 

    // get post by id
    PostDTO getPostbyId(Integer postId); 

    // get all post 
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy); 

    // get all post by user
    List<PostDTO> getAllPostbyUser(Integer userId); 

    // get all post by category
    List<PostDTO> getAllPostbyCategory(Integer categoryId); 

    // search post by keyword
    List<PostDTO> searchPosts(String keyword);



}

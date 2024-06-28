package com.blogapplication.Controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.blogapplication.Configuration.constants;
import com.blogapplication.Payload.ApiResponse;
import com.blogapplication.Payload.PostDTO;
import com.blogapplication.Payload.PostResponse;
import com.blogapplication.Services.FileService;
import com.blogapplication.Services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService; 

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    
    //  create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestBody PostDTO postDto, 
            @PathVariable Integer userId, 
            @PathVariable Integer categoryId
            ){
        PostDTO createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto ,@PathVariable Integer postId){
        PostDTO updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDTO>(updatedPost ,HttpStatus.OK);
    }

    // get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostbyUser(@PathVariable Integer userId){
        List<PostDTO> posts = this.postService.getAllPostbyUser(userId);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    // get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostbyCategory(@PathVariable Integer categoryId){
        List<PostDTO> posts = this.postService.getAllPostbyCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK); 
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostbyId(@PathVariable Integer postId){
        PostDTO post = this.postService.getPostbyId(postId);
        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    } 

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
    @RequestParam(value = "pageNumber", defaultValue = constants.PAGE_NUMBER, required = false) Integer pageNumber,
    @RequestParam(value = "pageSize", defaultValue = constants.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(value = "sortby", defaultValue = constants.SORT_BY, required = false) String sortby
    ){
        PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize, sortby);
        return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
    }
    
    // delete post by id 
    @DeleteMapping("/posts/{postId}") 
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true, LocalDateTime.now()) ,HttpStatus.OK);
    }

    // search post by title or keyword
    @GetMapping("/posts/search/{title}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(
        @PathVariable("title") String title
    ){
        List<PostDTO> allPosts = this.postService.searchPosts(title);
        return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.OK);
    }

    //  upload post images
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImages(
        @RequestParam("image") MultipartFile image,
        @PathVariable Integer postId
    ) throws IOException{
        
        PostDTO postDto = this.postService.getPostbyId(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDto, postId);
         return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
    }

    
    
}

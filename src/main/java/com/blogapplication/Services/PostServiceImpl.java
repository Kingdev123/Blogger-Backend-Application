package com.blogapplication.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.Entity.Category;
import com.blogapplication.Entity.Post;
import com.blogapplication.Entity.User;
import com.blogapplication.Exception.ResourceNotFound;
import com.blogapplication.Payload.PostDTO;
import com.blogapplication.Payload.PostResponse;
import com.blogapplication.Repository.CategoryRepository;
import com.blogapplication.Repository.PostRepository;
import com.blogapplication.Repository.UserRepository;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFound("user", "userId", userId));
        
        Category category = this.categoryRepository.findById(categoryId)            
                    .orElseThrow(()-> new ResourceNotFound("category", "categoryId", categoryId));

        Post post = this.modelmapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        
        Post savedPost = this.postRepository.save(post);
        return this.modelmapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post savedPost = this.postRepository.save(post);

        return this.modelmapper.map(savedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post", "id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostDTO getPostbyId(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "id", postId));

        PostDTO postDto = this.modelmapper.map(post, PostDTO.class);
        return postDto;
    }

    @Override
    public List<PostDTO> getAllPostbyUser(Integer userId) { 
        User user = this.userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFound("user", "Id", userId));
        List<Post> postsList = this.postRepository.findByUser(user);

        List<PostDTO> postsDtoList = postsList.stream()
                .map(post -> modelmapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postsDtoList;
    }

    @Override
    public List<PostDTO> getAllPostbyCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFound("category", "Id", categoryId));
        List<Post> postsList = this.postRepository.findByCategory(category);

        List<PostDTO> postsDtoList = postsList.stream()
                .map(post -> modelmapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postsDtoList;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortby) {

        Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortby));
        Page<Post> pagePost = this.postRepository.findAll(p);

        List<Post> allPost = pagePost.getContent();
        List<PostDTO> postsDtoList = allPost.stream()
                .map(post -> modelmapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postsDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;        

    }

    @Override
    public List<PostDTO> searchPosts(String title) {
        List<Post> posts = this.postRepository.findByTitleContaining(title);
        List<PostDTO> postDtos = posts.stream().map((post)->this.modelmapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDtos;
    }
}

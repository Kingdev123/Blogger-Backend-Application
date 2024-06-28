package com.blogapplication.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Entity.Comment;
import com.blogapplication.Entity.Post;
import com.blogapplication.Exception.ResourceNotFound;
import com.blogapplication.Payload.CommentDTO;
import com.blogapplication.Repository.CommentRespository;
import com.blogapplication.Repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRespository  commentRespository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO CreateComment(CommentDTO commentDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post", "id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment  = this.commentRespository.save(comment);

        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRespository.findById(commentId).orElseThrow(()-> new ResourceNotFound("comment", "id", commentId));
        

        this.commentRespository.delete(comment);
    }
    
}

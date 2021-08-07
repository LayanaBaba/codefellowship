package com.example.codefellowship.infrastructure.services;

import com.example.codefellowship.domain.Post;
import com.example.codefellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
public class PostImp implements PostService{
    @Autowired
    PostRepository postRepository;
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}

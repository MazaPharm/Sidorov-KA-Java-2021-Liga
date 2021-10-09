package org.example.service;

import org.example.entity.Post;
import org.example.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public void update(Post post){
        post.setText("Updated post's text");
        postRepo.save(post);
    }

    public Post create(){
        Post post = new Post();
        post.setText("Post text");
        postRepo.save(post);
        return post;
    }

    public void delete(Long id){
        postRepo.deleteById(id);
    }

}


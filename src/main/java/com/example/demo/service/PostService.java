package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final  UserService userService;
    private final PostRepo postRepo;

    @Autowired
    public PostService(UserService userService, PostRepo postRepo) {
        this.userService = userService;
        this.postRepo=postRepo;
    }

    public void createPost(Long id, String text){
        Post post = new Post();
        User user = userService.findUserById(id);
        post.setUser(user);
        post.setText(text);
        postRepo.save(post);
    }

    public List<PostDto> allUserPost(Long id){
        User user = userService.findUserById(id);
        List<PostDto> postDtos = new ArrayList<>();
        List<Post> posts = user.getPost();
        for (Post post: posts) {
            postDtos.add(new PostDto(post));
        }

        return postDtos;
    }

}

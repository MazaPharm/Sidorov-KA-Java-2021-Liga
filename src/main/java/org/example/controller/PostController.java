package org.example.controller;

import org.example.entity.Post;
import org.example.repository.PostRepo;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepo postRepo;

    @GetMapping
    public List<Post> allPosts() {
        return postRepo.findAll();
    }

    @PostMapping(value = "/create")
    public Post create() {
        return postService.create();
    }

    @PutMapping(value = "/{id}/update")
    public String update(@PathVariable("id") Long id) {
        Optional<Post> postFound = postRepo.findById(id);
        postService.update(postFound.get());
        return "Updated successfully";

    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return "Post has been deleted";
    }


}

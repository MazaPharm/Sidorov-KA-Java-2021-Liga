package org.example.controller;

import org.example.entity.Post;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> allPosts() {
        return postService.findAll();
    }

    @PostMapping(value = "/create")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping(value = "/{id}/update")
    public String update(@PathVariable("id") Long id) {
        return postService.update(postService.findById(id));
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

}

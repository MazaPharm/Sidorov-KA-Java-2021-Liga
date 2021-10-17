package org.example.controller;

import org.example.entity.Post;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/group")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     *
     * @param id группы
     * @return список постов в группе
     */
    @GetMapping("/{id}")
    public List<Post> allPosts(@PathVariable("id") Long id) {
        return postService.findAll(id);
    }

    /**
     *
     * @param post json создание поста
     * @param id групыы
     * @return созданный пост
     */
    @PostMapping(value = "/{id}/post/create")
    public Post create(@RequestBody Post post, @PathVariable("id") Long id) {
        return postService.create(post, id);
    }

    /**
     *
     * @param id поста
     * @param name новое название поста
     * @return сообщение об успешном обновлении
     */
    @PostMapping(value = "/post/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam("name") String text) {
        return postService.update(id, text);
    }

    /**
     *
     * @param id поста
     * @return сообщение об удачном удалении поста
     */
    @DeleteMapping("/post/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

}

package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class SecurityController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public SecurityController(UserService userService, PostService postService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }

    /**
     * Список всех пользователей
     *
     */
    @GetMapping("/users/all")
    public List<UserDto> allUsers(){
        return userService.allUsers();
    }

    /**
     * Смена логина и пароля у пользователя
     *
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/user/{id}/change")
    public void changeInfo(@PathVariable("id") Long id, @RequestParam("login") String login,
                              @RequestParam("newPassword") String newPassword){
        userService.changeInfo(id, login, newPassword);
    }

    @GetMapping("/delete")
    public void deleteAll(){
        userService.delete();
    }

    /**
     * Поиск пользователя по имени
     *
     */
    @GetMapping("/{id}/user/find")
    public List<UserDto> foundUser(@RequestParam("name") String name,
                                   @PathVariable("id") Long id) {
        return userService.foundUser(name, id);
    }
    /**
     * Информация про пользователя

     *
     */
    @GetMapping("/{id}/user/info")
    public UserDto userInfo(@PathVariable("id") Long id){
       return userService.userInfo(id);
    }

    /**
     * Создание поста у пользователя
     *
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/{id}/user/post/create")
    public void createPost(@PathVariable("id") Long id, @RequestParam("text") String text){
        postService.createPost(id,text);
    }

    /**
     * Просмотр всех постов у пользователя
     *
     */
    @GetMapping("/{id}/user/posts")
    public List<PostDto> allUserPost(@PathVariable("id") Long id){
        return postService.allUserPost(id);
    }

}

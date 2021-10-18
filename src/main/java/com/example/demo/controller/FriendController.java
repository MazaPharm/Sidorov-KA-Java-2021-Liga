package com.example.demo.controller;

import com.example.demo.dto.FriendDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * Выводит список всех друзей пользователя
     */
    @GetMapping("/{id}/friend")
    public List<FriendDto> allFriends(@PathVariable("id") Long id){
         return friendService.allFriends(id);
    }

    /**
     * Создает запрос на добавление в друзья
     * PathVariable("id")  id пользователя, который отправил запрос
     * RequestParam("id") id пользователя, которому отправили запрос
     */
    @PostMapping("/{id}/add/request")
    public String addFriend(@PathVariable("id") Long id, @RequestParam("id") Long idFriend){
        return friendService.addFriend(id, idFriend);
    }
    /**
     * Просмотр полученных запросов
     */
    @GetMapping("/{id}/request")
    public List<RequestDto> userRequest(@PathVariable("id") Long id){
        return friendService.myRequest(id);
    }
    /**
     * Добавление пользователя в друзья
     * PathVariable("id")  id запроса
     *
     */
    @PostMapping("/{id}/request/add")
    public String addedFriend(@PathVariable("id") Long idRequest){
        return friendService.addedFriend(idRequest);
    }

    /**
     * Отказ от добавления пользователя в друзья
     * PathVariable("id")  id запроса
     *
     */
    @PostMapping("/{id}/request/refused")
    public String refusedFriend(@PathVariable("id") Long id){
       return friendService.refusedFriend(id);
    }
    /**
     * Удаление пользователя из друзей
     * PathVariable("id")  id пользователя, который удаляет
     * RequestParam("id") id пользователя, которого удаляют
     */
    @PostMapping("/{id}/friend/delete")
    public String deleteFriend(@PathVariable("id") Long id, @RequestParam("id") Long friendId){
        return friendService.deleteFriend(id, friendId);
    }
}

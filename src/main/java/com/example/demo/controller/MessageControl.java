package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.MessageRoomDto;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MessageControl {

    private final MessageService messageService;

    @Autowired
    public MessageControl(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * @param userId id юзера
     * @return список диалогов юзера
     */
    @GetMapping("/{id}/dialogs")
    public List<MessageRoomDto> allDialogs(@PathVariable("id") Long userId) {
        return messageService.allDialogs(userId);
    }

    /**
     * @param userId   пользователь который создает диалог
     * @param friendId пользователь с кем создают диалог, передается через @RequestParam
     * @return информация о создании диалоги
     */
    @PostMapping("/{id}/dialog/create")
    public String createDialog(@PathVariable("id") Long userId, @RequestParam("id") Long friendId) {
        return messageService.createDialog(userId, friendId);
    }

    /**
     * @param id диалога
     * @return сообщения внутри диалога
     */
    @GetMapping("/dialog/{id}")
    public List<MessageDto> dialog(@PathVariable("id") Long id) {
        return messageService.dialog(id);
    }

    /**
     * @param dialogId id диалога, в котором пишем сообщение
     * @param userId   тот, кто пишет сообщение
     * @param text     текст сообщения
     * @return сообщение об успешном создании сообщения
     */
    @PostMapping("/dialog/{id}/message")
    public String addMessage(@PathVariable("id") Long dialogId, @RequestParam("id") Long userId
            , @RequestParam("text") String text) {
        return messageService.addMessage(dialogId, userId, text);
    }

    /**
     * @param dialodId id диалога, который хотим удалить
     * @return информаця об успешном удалении диалога
     */
    @PostMapping("/dialog/{id}/delete")
    public String deleteDialog(@PathVariable("id") Long dialodId) {
        return messageService.deleteDialog(dialodId);
    }

}

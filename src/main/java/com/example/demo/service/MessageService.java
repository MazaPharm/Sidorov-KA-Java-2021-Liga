package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.MessageRoomDto;
import com.example.demo.entity.Message;
import com.example.demo.entity.MessageRoom;
import com.example.demo.entity.User;
import com.example.demo.repository.MessageRepo;
import com.example.demo.repository.MessageRoomRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRoomRepo messageRoomRepo;
    private final UserService userService;
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    @Autowired
    public MessageService(MessageRoomRepo messageRoomRepo, UserService userService,
                          MessageRepo messageRepo,
                          UserRepo userRepo) {
        this.userService = userService;
        this.messageRoomRepo = messageRoomRepo;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    public List<MessageRoomDto> allDialogs(Long id) {
        List<MessageRoom> messageRooms1 = messageRoomRepo.findByFirstUserId(id);
        List<MessageRoom> messageRooms2 = messageRoomRepo.findBySecondUserId(id);
        List<MessageRoom> messageRooms = new ArrayList<>(messageRooms1);
        messageRooms.addAll(messageRooms2);
        List<MessageRoomDto> messageRoomDtos = new ArrayList<>();
        for (MessageRoom messageRoom : messageRooms) {
            messageRoomDtos.add(new MessageRoomDto(messageRoom));
        }
        return messageRoomDtos;
    }

    private MessageRoom getMessageRoom(Long id) {
        Optional<MessageRoom> messageRoom = messageRoomRepo.findById(id);
        return messageRoom.get();
    }

    public List<MessageDto> dialog(Long id) {
        MessageRoom messageRoom = getMessageRoom(id);
        List<Message> messages = messageRoom.getMessages();
        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message message : messages) {
            messageDtos.add(new MessageDto(message));
        }
        return messageDtos;
    }

    public String createDialog(Long userId, Long friendId) {
        User firstUser = userService.findUserById(userId);
        User secondUser = userService.findUserById(friendId);
        MessageRoom messageRoom = new MessageRoom();
        messageRoom.setFirstUser(firstUser);
        messageRoom.setSecondUser(secondUser);
        messageRoomRepo.save(messageRoom);
        return "Dialog created";
    }

    public String addMessage(Long id, Long userId, String text) {
        Message message = new Message();
        message.setText(text);
        message.setMessageRoom(getMessageRoom(id));
        message.setUser(userService.findUserById(userId));
        messageRepo.save(message);
        return "Message created";
    }


    public String deleteDialog(Long id){
        messageRoomRepo.deleteById(id);
        return "Dialog deleted";
    }
}

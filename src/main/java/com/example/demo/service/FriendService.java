package com.example.demo.service;

import com.example.demo.dto.FriendDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.entity.Friend;
import com.example.demo.entity.FriendRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.FriendRepo;
import com.example.demo.repository.FriendRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    private final UserService userService;
    private final FriendRequestRepo friendRequestRepo;
    private final FriendRepo friendRepo;

    @Autowired
    public FriendService(UserService userService, FriendRequestRepo friendRequestRepo, FriendRepo friendRepo) {
        this.userService = userService;
        this.friendRequestRepo = friendRequestRepo;
        this.friendRepo=friendRepo;
    }


    public List<FriendDto> allFriends(Long id){
        User user = userService.findUserById(id);
        List<Friend> friends = user.getFriends();
        List<FriendDto> friendDtos = new ArrayList<>();
        for (Friend friend: friends) {
            friendDtos.add(new FriendDto(friend));
        }
        return friendDtos;
    }

    public String addFriend(Long id, Long idFriend) {
        if (id.equals(idFriend)) {
            return "You can not add yourself";
        }
        Friend friendExist = friendRepo.findByFriendIdAndUserId(idFriend, id);
        Friend friendExistBack = friendRepo.findByFriendIdAndUserId(id, idFriend);
        if (friendExist != null || friendExistBack !=null) {
            return "This user is your friend";
        } else {
            User user = userService.findUserById(id);
            User friend = userService.findUserById(idFriend);
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setUser(user);
            friendRequest.setFriend(friend);
            friendRequestRepo.save(friendRequest);
            return "Request created";
        }
    }

    public List<RequestDto> myRequest(Long id){
        List<FriendRequest> requests = friendRequestRepo.findByFriendId(id);
        List<RequestDto> requestDtos = new ArrayList<>();
        for (FriendRequest friendRequest: requests) {
            requestDtos.add(new RequestDto(friendRequest));
        }

        return requestDtos;
    }

    private FriendRequest getRequest(Long id){
        Optional<FriendRequest> foundRequest = friendRequestRepo.findById(id);
        FriendRequest request = foundRequest.get();
        return request;
    }

    public String addedFriend(Long id) {
        FriendRequest request = getRequest(id);
        Friend friend = new Friend();
        friend.setUser(request.getUser());
        friend.setFriend(request.getFriend());
        friendRepo.save(friend);
        Friend friend1 = new Friend();
        friend1.setUser(request.getFriend());
        friend1.setFriend(request.getUser());
        friendRepo.save(friend1);
        friendRequestRepo.deleteById(id);
        return "Ok";

    }

    public String deleteFriend(Long id, Long friendId) {
        Friend friend = friendRepo.findByFriendIdAndUserId(friendId, id);
        Friend friend1 = friendRepo.findByFriendIdAndUserId(id, friendId);
        friendRepo.deleteById(friend.getId());
        friendRepo.deleteById(friend1.getId());
        return "deleted";
    }

    public String refusedFriend(Long id){
        friendRequestRepo.deleteById(id);
        return "Deleted";
    }
}

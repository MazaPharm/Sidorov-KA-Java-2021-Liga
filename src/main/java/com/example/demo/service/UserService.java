package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public List<UserDto> allUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    public void changeInfo(Long id, String login, String newPassword) {
        User user = findUserById(id);
        if (login.length() > 0) {
            user.setLogin(login);
        }
        if (newPassword.length() > 0) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        }
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.get();
    }

    public void delete() {
        userRepository.deleteAll();
    }

    public List<UserDto> foundUser(String name, Long id) {
        User user = findUserById(id);
        List<User> users = userRepository.findUsersByName(name);
        List<UserDto> userDtos = new ArrayList<>();
        users.removeIf(user1 -> user1.getId().equals(user.getId()));
        for (User u: users) {
            userDtos.add(new UserDto(u));
        }
        return userDtos;
    }

    public UserDto userInfo(Long id){
        User user = findUserById(id);
        return new UserDto(user);
    }
}

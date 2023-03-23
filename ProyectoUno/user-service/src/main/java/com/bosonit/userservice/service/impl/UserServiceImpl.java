package com.bosonit.userservice.service.impl;

import com.bosonit.userservice.entities.User;
import com.bosonit.userservice.exceptions.ResourceNotFoundException;
import com.bosonit.userservice.repositories.UserRepository;
import com.bosonit.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(Long.valueOf(userId)).orElseThrow(
                ()->new ResourceNotFoundException("User not found with id " + userId)
        );
    }
}

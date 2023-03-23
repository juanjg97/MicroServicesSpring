package com.bosonit.userservice.service;

import com.bosonit.userservice.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser (String userId);

}

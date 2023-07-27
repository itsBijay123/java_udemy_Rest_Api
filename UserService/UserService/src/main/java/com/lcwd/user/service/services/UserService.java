package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User getUser(String userId);

    List<User> getAllUser();


    //user operation

    //create
   // User saveUser(User user);
}

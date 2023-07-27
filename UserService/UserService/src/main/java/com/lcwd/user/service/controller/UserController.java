package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return new ResponseEntity(user1, HttpStatus.CREATED);
    }

    //single user get

    @GetMapping("/{id}")
    public ResponseEntity<User>getSingleUser(@PathVariable("id") String userId){
        User user=userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    //all user get

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User>allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}

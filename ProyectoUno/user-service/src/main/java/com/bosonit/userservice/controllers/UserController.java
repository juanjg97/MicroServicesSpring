package com.bosonit.userservice.controllers;

import com.bosonit.userservice.entities.User;
import com.bosonit.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @Operation(summary = "Save a user into database")
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @Operation(summary = "Get a user from database")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @Operation(summary = "Get all users from database")
    @GetMapping
    public ResponseEntity<List<User>> usersList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

}
package com.bosonit.userservice.controllers;

import com.bosonit.userservice.entities.User;
import com.bosonit.userservice.service.UserService;
import com.sun.jdi.LongValue;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private UserService userService;

    @Operation(summary = "Save a user into database")
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @Operation(summary = "Get a user from database using restTemplate")
    @GetMapping("/rest-template/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserRT(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserWithRestTemplate(userId));
    }

    @Operation(summary = "Get a user from database using FeignClient")
    @GetMapping("/feign-client/{userId}")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserFC(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserWithFeignClient(userId));
    }

    @Operation(summary = "Get all users from database")
    @GetMapping
    public ResponseEntity<List<User>> usersList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    public ResponseEntity<User> ratingHotelFallback(String usuarioId,Exception exception){
        log.info("El respaldo se ejecuta porque el servicio esta inactivo : ",exception.getMessage());
        User usuario = User.builder()
                .email("root1@gmail.com")
                .name("root")
                .information("Este usuario se crea por defecto cuando un servicio se cae")
                .build();
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

}

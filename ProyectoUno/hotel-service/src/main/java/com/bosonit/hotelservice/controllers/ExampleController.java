package com.bosonit.hotelservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-controller")
public class ExampleController {
    @GetMapping("/hello")
    public String sayHello(){
        return "This is a example controller used to try two controllers at api-gateway level";
    }
}

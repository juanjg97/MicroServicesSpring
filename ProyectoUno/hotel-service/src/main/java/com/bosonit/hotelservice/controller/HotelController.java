package com.bosonit.hotelservice.controller;

import com.bosonit.hotelservice.entity.Hotel;
import com.bosonit.hotelservice.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {
    private HotelService hotelService;

    @Operation(summary = "Save a hotel into database")
    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotelRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotelRequest));
    }

    @Operation(summary = "Get a hotel from database")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }

    @Operation(summary = "Get all hotels from database")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }

}

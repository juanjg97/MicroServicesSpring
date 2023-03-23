package com.bosonit.rateservice.controller;

import com.bosonit.rateservice.entities.Rate;
import com.bosonit.rateservice.services.RateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/rate")
public class RateController {
    private RateService rateService;

    @Operation(summary = "Save a rate into database")
    @PostMapping
    public ResponseEntity<Rate> saveRate(@RequestBody Rate rateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rateService.saveRate(rateRequest));
    }

    @Operation(summary = "Get all rates from the database")
    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        return ResponseEntity.status(HttpStatus.OK).body(rateService.getAllRates());
    }

    @Operation(summary = "Get a rate from a specific user")
    @GetMapping("/users/{idUser}")
    public ResponseEntity<List<Rate>> getRateByIdUser(@PathVariable String idUser) {
        return ResponseEntity.status(HttpStatus.OK).body(rateService.getRateByUserId(idUser));
    }

    @Operation(summary = "Get a rate from a hotel user")
    @GetMapping("/hotels/{idHotel}")
    public ResponseEntity<List<Rate>> getRateByIdHotel(@PathVariable String idHotel) {
        return ResponseEntity.status(HttpStatus.OK).body(rateService.getRateByHotelId(idHotel));
    }
}

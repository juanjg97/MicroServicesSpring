package com.bosonit.userservice.feign.config;

import com.bosonit.userservice.resttemplate.models.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATE-SERVICE")
public interface RateServiceFeign {

    @GetMapping("/api/rate/users/{idUser}")
    List<Rate> getRatesByUserId(@PathVariable String idUser);

    /*

    @PostMapping
    public ResponseEntity<Rate> saveRate(Rate rate);

    @PutMapping
    public ResponseEntity<Rate> updateRate(@PathVariable String idRate, Rate rate);

    @DeleteMapping
    public ResponseEntity<Rate> deleteRate(@PathVariable String idRate);
    */


}

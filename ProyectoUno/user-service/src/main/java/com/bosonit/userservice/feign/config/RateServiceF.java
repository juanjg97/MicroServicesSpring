package com.bosonit.userservice.feign.config;

import com.bosonit.userservice.models.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATE-SERVICE")
public interface RateServiceF {
    @PostMapping
    public ResponseEntity<Rate> saveRate(Rate rate);

    @PostMapping("/api/rate/{rateId}")
    public ResponseEntity<Rate> updateRate(@PathVariable String rateId ,Rate rate);

    @DeleteMapping("/api/rate/{rateId}")
    public void deleteRate(@PathVariable String rateId);
}

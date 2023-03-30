package com.bosonit.userservice.feign.config;

import com.bosonit.userservice.models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceF {
    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}

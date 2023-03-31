package com.bosonit.userservice.feign.config;

import com.bosonit.userservice.resttemplate.models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceFeign {

    //Obten el hotel por id desde el servicio de hotel
    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}

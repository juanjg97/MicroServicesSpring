package com.bosonit.hotelservice.service;

import com.bosonit.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(Long id);

}

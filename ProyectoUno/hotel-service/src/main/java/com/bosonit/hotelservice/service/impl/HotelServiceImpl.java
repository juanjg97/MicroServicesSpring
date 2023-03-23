package com.bosonit.hotelservice.service.impl;


import com.bosonit.hotelservice.entity.Hotel;
import com.bosonit.hotelservice.exceptions.ResourceNotFoundException;
import com.bosonit.hotelservice.repository.HotelRepository;
import com.bosonit.hotelservice.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Hotel not found with id " + id)
        );

    }
}

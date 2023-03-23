package com.bosonit.rateservice.services;

import com.bosonit.rateservice.entities.Rate;

import java.util.List;

public interface RateService {
    Rate saveRate(Rate rate);
    List<Rate> getAllRates();
    List<Rate> getRateByUserId(String idUser);
    List<Rate> getRateByHotelId(String idHotel);
}

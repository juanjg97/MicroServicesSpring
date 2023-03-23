package com.bosonit.rateservice.services.impl;

import com.bosonit.rateservice.entities.Rate;
import com.bosonit.rateservice.repository.RateRepository;
import com.bosonit.rateservice.services.RateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RateServiceImpl implements RateService {
    private RateRepository rateRepository;


    @Override
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public List<Rate> getRateByUserId(String idUser) {
        return rateRepository.findByIdUser(idUser);
    }

    @Override
    public List<Rate> getRateByHotelId(String idHotel) {
        return rateRepository.findByIdHotel(idHotel);
    }
}

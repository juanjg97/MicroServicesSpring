package com.bosonit.rateservice.repository;


import com.bosonit.rateservice.entities.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RateRepository extends MongoRepository<Rate,Long> {
    List<Rate> findByIdUser(String idUser);
    List<Rate> findByIdHotel(String idHotel);
}

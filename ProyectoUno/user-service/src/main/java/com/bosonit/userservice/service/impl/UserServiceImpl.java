package com.bosonit.userservice.service.impl;

import com.bosonit.userservice.entities.User;
import com.bosonit.userservice.exceptions.ResourceNotFoundException;
import com.bosonit.userservice.feign.config.HotelServiceFeign;
import com.bosonit.userservice.feign.config.RateServiceFeign;
import com.bosonit.userservice.models.Hotel;
import com.bosonit.userservice.models.Rate;
import com.bosonit.userservice.repositories.UserRepository;
import com.bosonit.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RestTemplate restTemplate;
    private HotelServiceFeign hotelService;
    private RateServiceFeign rateService;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserWithRestTemplate(String userId) {

        // Busca el usuario por su ID, en este momento el usuario no tiene seteado la calificación
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        //Ahora primero vamos a setear la calificacióin en el usuario
        //Aplicando balanceo de carga ya no es necesario especificar el puerto
        String endpointGetRateByUserId = "http://RATE-SERVICE/api/rate/users/" + user.getUserId().toString();

        // Obtiene las calificaciones del usuario utilizando la API externa en forma de array y las pasamos a una lista
        Rate[] ratesByUserArray = restTemplate.getForObject(endpointGetRateByUserId, Rate[].class);
        List<Rate> ratesByUserList = Arrays.asList(ratesByUserArray);

        // Para cada calificación, obtiene la información del hotel que fue calificado
        List<Rate> ratesByUserWithHotelList = ratesByUserList.stream().map(rate -> {

            String endpointGetHotelByRateId = "http://HOTEL-SERVICE/api/hotels/" + rate.getIdHotel();
            ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity(endpointGetHotelByRateId, Hotel.class);
            Hotel hotel = hotelResponseEntity.getBody();

            // Asigna la información del hotel a la calificación
            rate.setHotel(hotel);
            return rate;
        }).collect(Collectors.toList());

        // Asigna la lista de calificaciones al usuario y devuelve el objeto User
        user.setRates(ratesByUserWithHotelList);
        return user;
    }

    @Override
    public User getUserWithFeignClient(String userId) {

        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        List<Rate> ratesByUserList = rateService.getRatesByUserId(user.getUserId().toString());

        List<Rate> ratesByUserWithHotelList = ratesByUserList.stream().map(rate -> {
            Hotel hotel = hotelService.getHotel(rate.getIdHotel());
            rate.setHotel(hotel);
            return rate;
        }).collect(Collectors.toList());

        user.setRates(ratesByUserWithHotelList);
        return user;
    }



}

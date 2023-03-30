package com.bosonit.userservice.service.impl;

import com.bosonit.userservice.entities.User;
import com.bosonit.userservice.exceptions.ResourceNotFoundException;
import com.bosonit.userservice.feign.config.HotelServiceF;
import com.bosonit.userservice.models.Hotel;
import com.bosonit.userservice.models.Rate;
import com.bosonit.userservice.repositories.UserRepository;
import com.bosonit.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // Busca el usuario por su ID
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        String endpointRate = "http://localhost:8083/api/rate/users/" + user.getUserId().toString();

        // Obtiene las calificaciones del usuario utilizando la API externa
        Rate[] ratesByUser = restTemplate.getForObject(endpointRate, Rate[].class);

        // Convierte el arreglo de calificaciones en una lista
        List<Rate> rates = Arrays.asList(ratesByUser);

        // Para cada calificación, obtiene la información del hotel que fue calificado
        List<Rate> ratesList = rates.stream().map(rate -> {
            // Construye el endpoint de la API externa para obtener información del hotel
            String endpointHotel = "http://localhost:8082/api/hotels/" + rate.getIdHotel();

            // Obtiene la información del hotel utilizando la API externa
            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity(endpointHotel, Hotel.class);
            Hotel hotel = responseEntity.getBody();

            // Asigna la información del hotel a la calificación
            rate.setHotel(hotel);

            return rate;
        }).collect(Collectors.toList());

        // Asigna la lista de calificaciones al usuario y devuelve el objeto User
        user.setRates(ratesList);
        return user;
    }

    public List<Rate> getUserRatesRestTemplate(User user) {
        // Construye el endpoint de la API externa para obtener las calificaciones del usuario
        String endpointRate = "http://localhost:8083/api/rate/users/" + user.getUserId().toString();

        // Obtiene las calificaciones del usuario utilizando la API externa
        Rate[] ratesByUser = restTemplate.getForObject(endpointRate, Rate[].class);

        // Convierte el arreglo de calificaciones en una lista
        List<Rate> rates = Arrays.asList(ratesByUser);

        // Para cada calificación, obtiene la información del hotel que fue calificado
        List<Rate> ratesList = rates.stream().map(rate -> {
            // Construye el endpoint de la API externa para obtener información del hotel
            String endpointHotel = "http://localhost:8082/api/hotels/" + rate.getIdHotel();

            // Obtiene la información del hotel utilizando la API externa
            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity(endpointHotel, Hotel.class);
            Hotel hotel = responseEntity.getBody();

            // Asigna la información del hotel a la calificación
            rate.setHotel(hotel);

            return rate;
        }).collect(Collectors.toList());

        return ratesList;
    }


}

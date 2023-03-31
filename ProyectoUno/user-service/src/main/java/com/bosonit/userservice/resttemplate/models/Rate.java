package com.bosonit.userservice.resttemplate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private String idRate;
    private String idUser;
    private String idHotel;
    private int rate;
    private String observation;
    private Hotel hotel;
}

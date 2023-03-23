package com.bosonit.rateservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("rates")
public class Rate {
    @Id
    private String idRate;
    private String idUser;
    private String idHotel;
    private int rate;
    private String observation;

}

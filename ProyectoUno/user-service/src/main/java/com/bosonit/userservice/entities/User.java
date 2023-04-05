package com.bosonit.userservice.entities;

import com.bosonit.userservice.resttemplate.models.Rate;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String email;
    private String information;

    @Transient
    private List<Rate> rates = new ArrayList<>();
}

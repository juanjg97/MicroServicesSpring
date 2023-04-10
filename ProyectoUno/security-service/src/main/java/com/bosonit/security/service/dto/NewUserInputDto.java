package com.bosonit.security.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NewUserInputDto {
    private String userName;
    private String password;
    private String role;
}

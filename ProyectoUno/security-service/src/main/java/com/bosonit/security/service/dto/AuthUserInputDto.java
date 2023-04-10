package com.bosonit.security.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthUserInputDto {
    private String userName;
    private String password;
}

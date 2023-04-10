package com.bosonit.api.gateway.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TokenInputDto {
    private String uri;
    private String method;
}

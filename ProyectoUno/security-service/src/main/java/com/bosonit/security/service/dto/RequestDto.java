package com.bosonit.security.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto {

    private String uri;
    private String method;

}

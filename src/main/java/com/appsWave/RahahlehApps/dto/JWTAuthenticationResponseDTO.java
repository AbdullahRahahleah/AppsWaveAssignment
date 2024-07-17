package com.appsWave.RahahlehApps.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponseDTO {
    private String token;

    private String refreshToken;
}

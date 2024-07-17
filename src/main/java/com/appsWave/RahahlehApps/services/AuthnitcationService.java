package com.appsWave.RahahlehApps.services;

import com.appsWave.RahahlehApps.dto.JWTAuthenticationResponseDTO;
import com.appsWave.RahahlehApps.dto.RefreshTokenRequestDTO;
import com.appsWave.RahahlehApps.dto.SignInRequestDTO;
import com.appsWave.RahahlehApps.dto.SignUpRequestDTO;
import com.appsWave.RahahlehApps.entities.User;

public interface AuthnitcationService {

    User signUp(SignUpRequestDTO signUpRequestDTO);

     JWTAuthenticationResponseDTO signIn(SignInRequestDTO signInRequestDTO);

    JWTAuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}

package com.appsWave.RahahlehApps.controller;

import com.appsWave.RahahlehApps.dto.JWTAuthenticationResponseDTO;
import com.appsWave.RahahlehApps.dto.RefreshTokenRequestDTO;
import com.appsWave.RahahlehApps.dto.SignInRequestDTO;
import com.appsWave.RahahlehApps.dto.SignUpRequestDTO;
import com.appsWave.RahahlehApps.entities.User;
import com.appsWave.RahahlehApps.services.AuthnitcationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthinticationController {

    private final AuthnitcationService authnitcationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(authnitcationService.signUp(signUpRequestDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponseDTO> signin(@RequestBody SignInRequestDTO signInRequestDTO){
        return ResponseEntity.ok(authnitcationService.signIn(signInRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return ResponseEntity.ok(authnitcationService.refreshToken(refreshTokenRequestDTO));
    }

}

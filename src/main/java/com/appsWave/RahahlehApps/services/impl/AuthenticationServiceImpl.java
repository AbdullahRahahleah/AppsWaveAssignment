package com.appsWave.RahahlehApps.services.impl;

import com.appsWave.RahahlehApps.dto.JWTAuthenticationResponseDTO;
import com.appsWave.RahahlehApps.dto.RefreshTokenRequestDTO;
import com.appsWave.RahahlehApps.dto.SignInRequestDTO;
import com.appsWave.RahahlehApps.dto.SignUpRequestDTO;
import com.appsWave.RahahlehApps.entities.Role;
import com.appsWave.RahahlehApps.entities.User;
import com.appsWave.RahahlehApps.repositorty.UserRepository;
import com.appsWave.RahahlehApps.services.AuthnitcationService;
import com.appsWave.RahahlehApps.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthnitcationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signUp(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setFullName(signUpRequestDTO.getFullName());
        user.setRole(signUpRequestDTO.getRoleAsEnumValue());
        user.setDateOfBirth(signUpRequestDTO.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));

        return userRepository.save(user);
    }

    public JWTAuthenticationResponseDTO signIn(SignInRequestDTO signInRequestDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getEmail(), signInRequestDTO.getPassword()));

        // if he is authenticated, so we will prepare the response
        User user = userRepository.findByEmail(signInRequestDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("invalid email or password"));

        String jwt = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthenticationResponseDTO jwtAuthenticationResponseDTO = new JWTAuthenticationResponseDTO();
        jwtAuthenticationResponseDTO.setToken(jwt);
        jwtAuthenticationResponseDTO.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDTO;
    }

    /** We mostly use it to ask the server to re-generate a token without asking the client to re-enter
            his creds.
        We create it with a long live (week,month or year)
     */
    public JWTAuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String userEmail = jwtService.extractUserName(refreshTokenRequestDTO.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequestDTO.getToken(), user)) {

            String jwt = jwtService.generateToken(user);

            JWTAuthenticationResponseDTO jwtAuthenticationResponseDTO = new JWTAuthenticationResponseDTO();
            jwtAuthenticationResponseDTO.setToken(jwt);
            jwtAuthenticationResponseDTO.setRefreshToken(refreshTokenRequestDTO.getToken());

            return jwtAuthenticationResponseDTO;
        }
        return null;
    }

}


package com.appsWave.RahahlehApps.confi;


import com.appsWave.RahahlehApps.entities.Role;
import com.appsWave.RahahlehApps.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecuirtyConfiguration {

    private final JWTAuthinticationFilter jwtAuthinticationFilter;

    private final UserService userService;

    /**
     * here /auth we set it to permitAll which means any user can open this api
     * By using requestMatcheres method, we do define roles.
     * The system takes the first url matching the passed url, so the order here is critical
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/api/v1/news/read/**").hasAnyAuthority(Role.ADMIN.name(),Role.NORMAL.name(),Role.CONTENT_WRITER.name())
                        .requestMatchers("/api/v1/news/forceDelete/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/news/**").hasAnyAuthority(Role.ADMIN.name(),Role.CONTENT_WRITER.name())
                        .requestMatchers("/api/v1/**").hasAnyAuthority(Role.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //use stateless session policy
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthinticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    /**we define the AuthenticationProvider which returns the user information
     and password encoder method
    */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

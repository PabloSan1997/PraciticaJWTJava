package com.ejemplo.prueba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ejemplo.prueba.security.filters.JwtAuthenticationFilter;
import com.ejemplo.prueba.security.filters.JwtValidateAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager getAuthenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean 
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.authorizeHttpRequests(
                autrh -> autrh
                        .requestMatchers(HttpMethod.GET, "/api/v1/images", "/api/v1/users").hasAnyRole( "ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/images").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/images/{id}/{username}").hasRole("USER")
                        .anyRequest().authenticated())
                        .addFilter(new JwtAuthenticationFilter(getAuthenticationManager()))
                        .addFilter(new JwtValidateAuthFilter(getAuthenticationManager()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

       
    }

}

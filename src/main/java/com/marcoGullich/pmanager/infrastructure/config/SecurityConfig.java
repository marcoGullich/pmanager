package com.marcoGullich.pmanager.infrastructure.config;

import com.marcoGullich.pmanager.infrastructure.security.AuthenticationFilter;
import com.marcoGullich.pmanager.infrastructure.security.AuthenticationServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings("unused")
public class SecurityConfig {

    private final AuthenticationServices authenticationServices;

    public SecurityConfig(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(c ->
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(r ->
                        r.requestMatchers("/**").authenticated()
                )
                .addFilterBefore(
                        new AuthenticationFilter(authenticationServices),
                        UsernamePasswordAuthenticationFilter.class
                ).build();

    }
}

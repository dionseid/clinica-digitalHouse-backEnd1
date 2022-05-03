package com.dh.clinica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomPasswordEncoder {
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    // Los próximos 2 métodos los tenía Julián
    public String encodePassword(String rawPassword) {
        return bCryptPasswordEncoder().encode(rawPassword).toString();
    }

    public boolean matchesPassword(String rawPassword, String encodePassword) {
        return bCryptPasswordEncoder().matches(rawPassword, encodePassword);
    }
}

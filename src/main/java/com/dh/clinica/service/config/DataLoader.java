package com.dh.clinica.service.config;

import com.dh.clinica.model.auth.Roles;
import com.dh.clinica.model.auth.Usuarie;
import com.dh.clinica.repository.auth.UsuarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private /*final*/ UsuarieRepository/*Service*/ usuarieRepository;

    @Autowired
    public DataLoader(UsuarieRepository usuarieRepository) {
        this.usuarieRepository = usuarieRepository;
    }

    public void run(ApplicationArguments args) /*throws BadRequestException*/ {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("user");
        usuarieRepository.save(new Usuarie(123456789, "admin", "admin@gmail.com", hashedPassword, List.of(Roles.ADMIN, Roles.USER)));
        usuarieRepository.save(new Usuarie(123456789, "user", "user@gmail.com", hashedPassword2, List.of(Roles.USER)));
    }
}

package com.dh.clinica.service.config;

import com.dh.clinica.model.auth.Roles;
import com.dh.clinica.model.auth.Usuarie;
import com.dh.clinica.model.dto.user.CreateUserDto;
import com.dh.clinica.repository.auth.UsuarieRepository;
import com.dh.clinica.service.impl.auth.UsuarieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UsuarieService usuarieService;
    private final UsuarieRepository usuarieRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void run(ApplicationArguments args) {
        String hashedPassword = passwordEncoder.encode("admin");
        usuarieRepository.save(new Usuarie(123456789, "admin", "admin@gmail.com", hashedPassword, Set.of(Roles.ADMIN, Roles.USER)));
        usuarieService.guardar(new CreateUserDto("user", "user", "user"));
    }
}

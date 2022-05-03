package com.dh.clinica.service.impl.auth;

import com.dh.clinica.entity.auth.Roles;
import com.dh.clinica.entity.auth.Usuarie;
import com.dh.clinica.repository.auth.UsuarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
        usuarieRepository.save(new Usuarie(123456789, "admin", "admin@gmail.com", hashedPassword, Roles.ADMIN/*Set.of(new Rol("ADMIN"), new Rol("USER"))*/));
        usuarieRepository.save(new Usuarie(123456789, "user", "user@gmail.com", hashedPassword2, Roles.USER/*Set.of(new Rol("USER"))*/));
    }
}

package com.dh.clinica.service.impl.auth;

import com.dh.clinica.entity.auth.Rol;
import com.dh.clinica.entity.auth.Usuarie;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.repository.auth.UsuarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final UsuarieService usuarieService;

    @Autowired
    public DataLoader(UsuarieService usuarieService) {
        this.usuarieService = usuarieService;
    }

    public void run(ApplicationArguments args) throws BadRequestException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("admin");
        String password2 = passwordEncoder.encode("user");
        usuarieService.guardar(new Usuarie(123456789, "admin", "admin@gmail.com", password, Set.of(new Rol("ADMIN"), new Rol("USER"))));
        usuarieService.guardar(new Usuarie(123456789, "user", "user@gmail.com", password2, Set.of(new Rol("USER"))));
    }
}

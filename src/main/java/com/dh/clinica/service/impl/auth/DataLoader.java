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
        usuarieRepository.save(new Usuarie(123456789, "admin", "admin@gmail.com", hashedPassword, Rol.ADMIN/*Set.of(new Rol("ADMIN"), new Rol("USER"))*/));
        usuarieRepository.save(new Usuarie(123456789, "user", "user@gmail.com", hashedPassword2, Rol.USER/*Set.of(new Rol("USER"))*/));
    }
}

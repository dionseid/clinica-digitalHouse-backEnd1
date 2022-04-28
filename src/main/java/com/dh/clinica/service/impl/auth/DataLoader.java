package com.dh.clinica.service.impl.auth;

import com.dh.clinica.persistance.entity.auth.Role;
import com.dh.clinica.persistance.entity.auth.User;
import com.dh.clinica.persistance.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("user");
        userRepository.save(new User(123456789, "admin", "admin@gmail.com", hashedPassword, Role.ADMIN/*Set.of(new Rol("ADMIN"), new Rol("USER"))*/));
        userRepository.save(new User(123456789, "user", "user@gmail.com", hashedPassword2, Role.USER/*Set.of(new Rol("USER"))*/));
    }
}

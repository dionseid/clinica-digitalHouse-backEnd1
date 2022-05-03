package com.dh.clinica.service.impl.auth;

import com.dh.clinica.config.CustomPasswordEncoder;
import com.dh.clinica.persistance.entity.auth.Role;
import com.dh.clinica.persistance.entity.auth.User;
import com.dh.clinica.persistance.repository.auth.RoleRepository;
import com.dh.clinica.persistance.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("user");
        userRepository.save(new User(123456789, "admin", "admin@gmail.com", hashedPassword, Set.of(new Role("ADMIN"), new Role("USER"))));
        userRepository.save(new User(123456789, "user", "user@gmail.com", hashedPassword2, Set.of(new Role("USER"))));*/

        String hashedPassword = customPasswordEncoder.encodePassword("admin");
        Role roleAdmin = new Role("ADMIN");
        roleRepository.save(roleAdmin);
        User user = new User("dionseid", hashedPassword);
        user.addRole(roleRepository.getRoleByName("ADMIN").get());
        userRepository.save(user);

        String hashedPassword2 = customPasswordEncoder.encodePassword("user");
        Role roleUser = new Role("USER");
        roleRepository.save(roleUser);
        User user2 = new User("jpablos16", hashedPassword2);
        user2.addRole(roleRepository.getRoleByName("USER").get());
        userRepository.save(user2);
    }
}

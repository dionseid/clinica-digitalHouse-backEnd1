package com.dh.clinica.persistance.repository.auth;

import com.dh.clinica.persistance.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrMail(String username, String email);
    Optional<User> findByUsername(String email);
}

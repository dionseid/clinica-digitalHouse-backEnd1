package com.dh.clinica.repository.auth;

import com.dh.clinica.model.auth.Usuarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UsuarieRepository extends JpaRepository<Usuarie, Long> {
    Optional<Usuarie> findByUsername(String username);
}

package com.dh.clinica.repository.impl;

import com.dh.clinica.dominio.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> { // Por defecto toma ID como Long, por más que lo setee como Integer
}
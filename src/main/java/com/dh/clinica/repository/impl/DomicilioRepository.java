package com.dh.clinica.repository.impl;

import com.dh.clinica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> { // Por defecto toma ID como Long, por más que lo setee como Integer
}
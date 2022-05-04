package com.dh.clinica.repository.impl;

import com.dh.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> { // Por defecto toma ID como Long, por más que lo setee como Integer
}
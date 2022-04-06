package com.dh.clinica.repository.impl;

import com.dh.clinica.dominio.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { // Por defecto toma ID como Long, por más que lo setee como Integer
}
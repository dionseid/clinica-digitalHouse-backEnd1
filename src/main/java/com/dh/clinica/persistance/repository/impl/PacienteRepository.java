package com.dh.clinica.persistance.repository.impl;

import com.dh.clinica.persistance.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
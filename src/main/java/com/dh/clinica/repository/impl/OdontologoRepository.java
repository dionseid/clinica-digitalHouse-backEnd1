package com.dh.clinica.repository.impl;

import com.dh.clinica.dominio.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> { // Por defecto toma ID como Long, por más que lo setee como Integer
}

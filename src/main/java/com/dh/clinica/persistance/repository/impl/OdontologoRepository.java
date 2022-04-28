package com.dh.clinica.persistance.repository.impl;

import com.dh.clinica.persistance.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    @Query("FROM Odontologo o WHERE o.apellido LIKE %:apellido%")
    Set<Odontologo> listarPorNombre(@Param("apellido") String apellido);
}

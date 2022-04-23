package com.dh.clinica.repository.impl;

import com.dh.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> { // Por defecto toma ID como Long, por más que lo setee como Integer
    // Para crear un nuevo odontólogo usamos .save()

    @Query(/*"SELECT o */"FROM Odontologo o WHERE o.apellido LIKE %:apellido%"/*= ?1"*/)
    Set/*Optional*/<Odontologo> listarPorNombre(@Param("apellido") String apellido);
}

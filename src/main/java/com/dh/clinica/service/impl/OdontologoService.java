package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Turno;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.dto.OdontologoDto;
import com.dh.clinica.entity.dto.OdontologoTurnosDto;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    ObjectMapper mapper;

    /*private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }*/

    public OdontologoDto/*Odontologo*/ guardar(OdontologoDto/*Odontologo*/ o) {
        Odontologo odontologo = mapper.convertValue(o, Odontologo.class);
        odontologoRepository.save(odontologo);
        o.setId(odontologo.getId());
        return o;
    }

    public Set/*List*/<OdontologoDto/*Odontologo*/> listar() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDto> odontologoDtos = new HashSet<>();
        for (Odontologo o : odontologos) odontologoDtos.add(mapper.convertValue(o, OdontologoDto.class));
        return odontologoDtos;
    }

    public OdontologoDto/*Odontologo*/ actualizar(OdontologoDto/*Odontologo*/ o) { // Considerando esta implementación ¿No debería eliminar el odontólogo desactualizado?
        Odontologo odontologo = mapper.convertValue(o, Odontologo.class);
        odontologoRepository.save(odontologo);
        return o;
    }

    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Set<OdontologoTurnosDto> listarTurnos(Long id) {
        Odontologo o = odontologoRepository.findById(id).get();
        Set<OdontologoTurnosDto> odontologoTurnosDto = new HashSet<>();
        for (Turno t: o.getTurnos()) odontologoTurnosDto.add(mapper.convertValue(t, OdontologoTurnosDto.class));
        return odontologoTurnosDto;
    }

    public OdontologoDto/*Optional<Odontologo>*//*Odontologo*/ buscar(Long id) {
        //return odontologoRepository.findById(id).get();
        // return odontologoRepository.findById(id);
        return mapper.convertValue(odontologoRepository.findById(id), OdontologoDto.class);
    }

    public Set<OdontologoDto>/*Odontologo*/ listarPorNombre(String apellido) {
        //return odontologoRepository.buscarPorNombre(apellido).get();
        Set<Odontologo> odontologos = odontologoRepository.listarPorNombre(apellido);
        Set<OdontologoDto> odontologoDtos = new HashSet<>();
        for (Odontologo o: odontologos) odontologoDtos.add(mapper.convertValue(o, OdontologoDto.class));
        return odontologoDtos;
    }

    public Odontologo agregarTurno(Long id, Turno t) {
        // Optional porque puede ser nulo
        Optional<Odontologo> o = odontologoRepository.findById(id);
        // Se asignó el odontólogo al turno, como es un Optional, usamos t.get() para tener un objeto Odontologo y no un objeto Optional<Odontologo>
        t.setOdontologo(o.get());
        // Agregar, a la colección de turnos del odontólogo, al nuevo turno
        o.get().getTurnos().add(t);
        return odontologoRepository.save(o.get());
    }
}

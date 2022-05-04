package com.dh.clinica.service.impl;

import com.dh.clinica.util.SpringConfig;
import com.dh.clinica.model.Turno;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.dto.OdontologoDto;
import com.dh.clinica.model.dto.OdontologoTurnosDto;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.service.CrudService;
import com.dh.clinica.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements CrudService<OdontologoDto> {
    /*@Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    ObjectMapper mapper;*/

    private final OdontologoRepository odontologoRepository;
    private final SpringConfig springConfig;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, SpringConfig springConfig) {
        this.odontologoRepository = odontologoRepository;
        this.springConfig = springConfig;
    }

    @Override
    public OdontologoDto guardar(OdontologoDto odontologoDto) throws BadRequestException, ResourceNotFoundException {
        if (odontologoDto == null) throw new BadRequestException("El odontólogo no puede ser null");
        //Odontologo odontologo = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologo = springConfig.getModelMapper().map(odontologoDto, Odontologo.class);
        odontologoRepository.save(odontologo);
        odontologoDto = springConfig.getModelMapper().map(odontologoRepository.save(odontologo), OdontologoDto.class);
        odontologoDto.setId(odontologo.getId());
        return odontologoDto;
        /*odontologoRepository.save(odontologo);
        odontologoDto.setId(odontologo.getId());
        return odontologoDto;*/
    }

    @Override
    public /*Set*/List<OdontologoDto/*Odontologo*/> listar() {
        /*List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDto> odontologoDtos = new HashSet<>();
        for (Odontologo o : odontologos) odontologoDtos.add(mapper.convertValue(o, OdontologoDto.class));
        return odontologoDtos;*/
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return Mapper.mapList(springConfig.getModelMapper(), odontologos, OdontologoDto.class);
    }

    @Override
    public OdontologoDto/*Odontologo*/ actualizar(OdontologoDto/*Odontologo*/ o) throws BadRequestException, ResourceNotFoundException { // Considerando esta implementación ¿No debería eliminar el odontólogo desactualizado?
        /*Odontologo odontologo = mapper.convertValue(o, Odontologo.class);
        odontologoRepository.save(odontologo);
        return o;*/
        OdontologoDto odontologoActualizado;
        if (o == null) throw new BadRequestException("El odontólogo no puede ser null");
        if (o.getId() == null) throw new BadRequestException("El id del odontólogo no puede ser null");
        Optional<Odontologo> odontologoEnBD = odontologoRepository.findById(o.getId());
        if (odontologoEnBD.isPresent()) {
            Odontologo actualizado = odontologoEnBD.get();
            if (o.getNombre() != null) odontologoEnBD.get().setNombre(o.getNombre());
            if (o.getApellido() != null) odontologoEnBD.get().setApellido(o.getApellido());
            if (o.getMatricula() != null) odontologoEnBD.get().setMatricula(o.getMatricula());
            Odontologo guardado = odontologoRepository.save(actualizado);
            odontologoActualizado = springConfig.getModelMapper().map(guardado, OdontologoDto.class);
        } else throw new ResourceNotFoundException("El odontólogo no existe");
        return odontologoActualizado;
    }

    @Override
    public OdontologoDto/*Optional<Odontologo>*//*Odontologo*/ buscar(Integer id) throws BadRequestException {
        //return odontologoRepository.findById(id).get();
        // return odontologoRepository.findById(id);
        if (id == null || id < 1)
            throw new BadRequestException("El ID del odontólogo no puede ser null ni negativo");
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        if (o == null) throw new BadRequestException("No se encontró el odontólogo con ID " + id);
        OdontologoDto odontologoDto = null;
        odontologoDto = springConfig.getModelMapper().map(o, OdontologoDto.class);
        return odontologoDto;
        //return mapper.convertValue(odontologoRepository.findById(id), OdontologoDto.class);
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    public Set<OdontologoDto>/*Odontologo*/ listarPorNombre(String apellido) {
        //return odontologoRepository.buscarPorNombre(apellido).get();
        Set<Odontologo> odontologos = odontologoRepository.listarPorNombre(apellido);
        Set<OdontologoDto> odontologoDtos = new HashSet<>();
        for (Odontologo o: odontologos) odontologoDtos.add(/*mapper.convertValue(o, OdontologoDto.class)*/springConfig.getModelMapper().map(o, OdontologoDto.class));
        return odontologoDtos;
    }

    public Odontologo agregarTurno(Integer id, Turno t) {
        // Optional porque puede ser nulo
        Optional<Odontologo> o = odontologoRepository.findById(id);
        // Se asignó el odontólogo al turno, como es un Optional, usamos t.get() para tener un objeto Odontologo y no un objeto Optional<Odontologo>
        t.setOdontologo(o.get());
        // Agregar, a la colección de turnos del odontólogo, al nuevo turno
        o.get().getTurnos().add(t);
        return odontologoRepository.save(o.get());
    }

    public Set<OdontologoTurnosDto> listarTurnos(Integer id) {
        Odontologo o = odontologoRepository.findById(id).get();
        Set<OdontologoTurnosDto> odontologoTurnosDto = new HashSet<>();
        //for (Turno t: o.getTurnos()) odontologoTurnosDto.add(mapper.convertValue(t, OdontologoTurnosDto.class));
        for (Turno t: o.getTurnos()) odontologoTurnosDto.add(springConfig.getModelMapper().map(t, OdontologoTurnosDto.class));
        /*Mapper.mapList(springConfig.getModelMapper(), odontologos, OdontologoDto.class);
        springConfig.getModelMapper().map(guardado, OdontologoDto.class);*/
        return odontologoTurnosDto;
    }
}

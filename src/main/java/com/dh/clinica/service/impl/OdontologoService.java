package com.dh.clinica.service.impl;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.persistance.entity.Turno;
import com.dh.clinica.persistance.entity.Odontologo;
import com.dh.clinica.persistance.entity.dto.OdontologoDto;
import com.dh.clinica.persistance.entity.dto.OdontologoTurnosDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistance.repository.impl.OdontologoRepository;
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
        Odontologo odontologo = springConfig.getModelMapper().map(odontologoDto, Odontologo.class);
        odontologoRepository.save(odontologo);
        odontologoDto = springConfig.getModelMapper().map(odontologoRepository.save(odontologo), OdontologoDto.class);
        odontologoDto.setId(odontologo.getId());
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listar() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return Mapper.mapList(springConfig.getModelMapper(), odontologos, OdontologoDto.class);
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto o) throws BadRequestException, ResourceNotFoundException {
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
    public OdontologoDto buscar(Integer id) throws BadRequestException {
        if (id == null || id < 1)
            throw new BadRequestException("El ID del odontólogo no puede ser null ni negativo");
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        if (o == null) throw new BadRequestException("No se encontró el odontólogo con ID " + id);
        OdontologoDto odontologoDto = null;
        odontologoDto = springConfig.getModelMapper().map(o, OdontologoDto.class);
        return odontologoDto;
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    public Set<OdontologoDto> listarPorNombre(String apellido) {
        Set<Odontologo> odontologos = odontologoRepository.listarPorNombre(apellido);
        Set<OdontologoDto> odontologoDtos = new HashSet<>();
        for (Odontologo o: odontologos) odontologoDtos.add(springConfig.getModelMapper().map(o, OdontologoDto.class));
        return odontologoDtos;
    }

    public Odontologo agregarTurno(Integer id, Turno t) {
        Optional<Odontologo> o = odontologoRepository.findById(id);
        t.setOdontologo(o.get());
        o.get().getTurnos().add(t);
        return odontologoRepository.save(o.get());
    }

    public Set<OdontologoTurnosDto> listarTurnos(Integer id) {
        Odontologo o = odontologoRepository.findById(id).get();
        Set<OdontologoTurnosDto> odontologoTurnosDto = new HashSet<>();
        for (Turno t: o.getTurnos()) odontologoTurnosDto.add(springConfig.getModelMapper().map(t, OdontologoTurnosDto.class));
        return odontologoTurnosDto;
    }
}

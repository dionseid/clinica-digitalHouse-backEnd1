package com.dh.clinica.service.impl;


import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.dto.DomicilioDto;
import com.dh.clinica.entity.dto.PacienteDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.impl.DomicilioRepository;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    /*@Autowired
    private PacienteRepository pacienteRepository;*/

    private final PacienteRepository pacienteRepository;
    private final DomicilioService domicilioService;
    private final SpringConfig springConfig;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioService domicilioService, SpringConfig springConfig) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioService = domicilioService;
        this.springConfig = springConfig;
    }

    @Override
    public PacienteDto guardar(PacienteDto pacienteDto) throws BadRequestException, ResourceNotFoundException {
        //if (pacienteDto == null) throw new BadRequestException("No se puede crear un paciente null");
        if (pacienteDto.getDomicilio() == null)
            throw new BadRequestException("El domicilio es null");
        DomicilioDto domicilio = domicilioService.guardar(pacienteDto.getDomicilio());
        Long domicilioId = domicilio.getId();
        //Long domicilioId = pacienteDto.getDomicilio().getId();
        if (domicilioService.buscar(domicilioId) != null) {
            Paciente paciente = Mapper.map(springConfig.getModelMapper(), pacienteDto, Paciente.class);
            pacienteDto = Mapper.map(springConfig.getModelMapper(), pacienteRepository.save(paciente), PacienteDto.class);
            pacienteDto.setDomicilio(domicilioService.buscar(domicilioId));
        } else
            throw new BadRequestException("No se pudo cargar el domicilio");
        return pacienteDto;
        //Paciente p = springConfig.getModelMapper().map(pacienteDto, Paciente.class);
        //return springConfig.getModelMapper().map(pacienteRepository.save(p), PacienteDto.class);
    }

    @Override
    public List<PacienteDto> listar() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return Mapper.mapList(springConfig.getModelMapper(), pacientes, PacienteDto.class);
    }

    @Override
    public PacienteDto actualizar(PacienteDto pacienteDto) throws BadRequestException, ResourceNotFoundException {
        if (pacienteDto == null)
            throw new BadRequestException("No se pudo actualizar le paciente null");
        if (pacienteDto.getId() == null)
            throw new BadRequestException("El ID del paciente a actualizar no puede ser null");
        Optional<Paciente> pacienteEnBD = pacienteRepository.findById(pacienteDto.getId());
        if (pacienteEnBD.isPresent()) {
            if (pacienteDto.getNombre() != null) pacienteEnBD.get().setNombre(pacienteDto.getNombre());
            if (pacienteDto.getApellido() != null) pacienteEnBD.get().setApellido(pacienteDto.getApellido());
            if (pacienteDto.getDni() != null) pacienteEnBD.get().setDni(pacienteDto.getDni());
            if (pacienteDto.getEmail() != null) pacienteEnBD.get().setEmail(pacienteDto.getEmail());
            if (pacienteDto.getFechaIngreso() != null)
                pacienteEnBD.get().setFechaIngreso(pacienteDto.getFechaIngreso());
            if (pacienteDto.getDomicilio() != null) {
                DomicilioDto actualizado = domicilioService.actualizar(pacienteDto.getDomicilio());
                pacienteEnBD.get().setDomicilio(springConfig.getModelMapper().map(actualizado, Domicilio.class));
            }
            Paciente actualizade = pacienteEnBD.get();
            pacienteDto = springConfig.getModelMapper().map(pacienteRepository.save(actualizade), PacienteDto.class);
        } else throw new ResourceNotFoundException("Le paciente no existe");
        return pacienteDto;
    }

    /*public Paciente actualizar(Paciente p) {
        return pacienteRepository.save(p);
    }*/

    @Override
    public PacienteDto buscar(Long id) throws BadRequestException{
        if (id == null || id < 1)
            throw new BadRequestException("El ID del paciente no puede ser null ni negativo");
        Paciente p = pacienteRepository.findById(id).orElse(null);
        if (p == null)
            throw new BadRequestException("No se encontró le paciente con ID " + id);
        return springConfig.getModelMapper().map(p, PacienteDto.class);
    }

    /*public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }*/
}

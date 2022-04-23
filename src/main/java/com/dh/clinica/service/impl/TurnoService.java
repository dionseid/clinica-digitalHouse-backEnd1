package com.dh.clinica.service.impl;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.entity.dto.OdontologoDto;
import com.dh.clinica.entity.dto.PacienteDto;
import com.dh.clinica.entity.dto.TurnoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.CrudService;
import com.dh.clinica.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements CrudService<TurnoDto> {
    /*@Autowired
    private TurnoRepository turnoRepository;*/

    @Autowired
    private CrudService<OdontologoDto> odontologoService;
    @Autowired
    private CrudService<PacienteDto> pacienteService;
    private final TurnoRepository turnoRepository;
    @Autowired
    private SpringConfig springConfig;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public TurnoDto guardar(TurnoDto turnoSolicitado) throws BadRequestException, ResourceNotFoundException {
        //t.setFechaIngreso(new Date());
        //return turnoRepository.save(t);

        if (turnoSolicitado.getPaciente() == null || turnoSolicitado.getOdontologo() == null) throw new BadRequestException("Le paciente u odontólogo es null");
        Integer pacienteId = turnoSolicitado.getPaciente().getId();
        Integer odontologoId = turnoSolicitado.getOdontologo().getId();
        PacienteDto pacienteEnBd = pacienteService.buscar(pacienteId);
        OdontologoDto odontologoEnBd = odontologoService.buscar(odontologoId);
        if (pacienteEnBd != null && odontologoEnBd != null) {
            if (this.hayDisponibilidad(turnoSolicitado)) {
                Turno turnoAGuardar = Mapper.map(springConfig.getModelMapper(), turnoSolicitado, Turno.class);
                turnoSolicitado = Mapper.map(springConfig.getModelMapper(), turnoRepository.save(turnoAGuardar), TurnoDto.class);
                turnoSolicitado.setPaciente(pacienteService.buscar(pacienteId));
                turnoSolicitado.setOdontologo(odontologoService.buscar(odontologoId));
            } else throw new BadRequestException("El odontólogo ya tiene un turno programado para ese día en ese horario");
        } else throw new BadRequestException("Le paciente u odontólogo no existe");
        return turnoSolicitado;
    }

    private boolean hayDisponibilidad(TurnoDto turnoAGuardar) throws BadRequestException, ResourceNotFoundException {
        OdontologoDto odontologoRequerido = odontologoService.buscar(turnoAGuardar.getOdontologo().getId());
        return turnoRepository.findAll()
                .stream()
                .map(turno -> Mapper.map(springConfig.getModelMapper(), turno, TurnoDto.class))
                .noneMatch(turno -> turno.getOdontologo().equals(odontologoRequerido) && turno.getDiaHora().equals(turnoAGuardar.getDiaHora()));
    }

    @Override
    public List<TurnoDto> listar() {
        //return turnoRepository.findAll();
        List<Turno> turnosEntidades = turnoRepository.findAll();
        return Mapper.mapList(springConfig.getModelMapper(), turnosEntidades, TurnoDto.class);
    }

    @Override
    public TurnoDto actualizar(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    /*public Turno actualizar(Turno t) {
        return turnoRepository.save(t);
    }*/

    @Override
    public TurnoDto buscar(Integer id) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    /*public Optional<Turno>/*Turno*/ /*buscar(Long id) {
        return turnoRepository.findById(id);
    }*/

    @Override
    public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {

    }

    /*public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }*/
}

package com.dh.clinica.service.impl;

import com.dh.clinica.util.SpringConfig;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.model.dto.OdontologoDto;
import com.dh.clinica.model.dto.PacienteDto;
import com.dh.clinica.model.dto.TurnoDto;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.CrudService;
import com.dh.clinica.util.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @SneakyThrows
    @Override
    public TurnoDto actualizar(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        if (turnoDto == null) throw new BadRequestException("No se pudo actualizar el turno null");
        if (turnoDto.getId() == null) throw new BadRequestException("El ID del turno a actualizar no puede ser null");
        if (turnoDto.getPaciente() == null || turnoDto.getOdontologo() == null) throw new BadRequestException("Le paciente u odontólogo es null");
        Turno turnoEnBd = turnoRepository.findById(turnoDto.getId()).get();

        if (turnoEnBd != null) {
            if (hayDisponibilidad(turnoDto)) {
                if (turnoDto.getDiaHora() != null) turnoEnBd.setDiaHora(turnoDto.getDiaHora());
                if (turnoDto.getPaciente() != null) {
                    //pacienteService.actualizar(turnoDto.getPaciente());
                    PacienteDto pacienteEnBd = pacienteService.buscar(turnoDto.getPaciente().getId());
                    turnoEnBd.setPaciente(springConfig.getModelMapper().map(pacienteEnBd, Paciente.class));
                }
                if (turnoDto.getOdontologo() != null) {
                    //odontologoService.actualizar(turnoDto.getOdontologo());
                    OdontologoDto odontologoEnBd = odontologoService.buscar(turnoDto.getOdontologo().getId());
                    turnoEnBd.setOdontologo(springConfig.getModelMapper().map(odontologoEnBd, Odontologo.class));
                }
                Turno turnoActualizado = turnoEnBd;
                turnoDto = springConfig.getModelMapper().map(turnoRepository.save(turnoActualizado), TurnoDto.class);
            } else throw new BadRequestException("El odontólogo ya tiene un turno programado para ese día en ese horario");
        } else throw new ResourceNotFoundException("El odontólogo no existe");
        return turnoDto;
    }

    /*public Turno actualizar(Turno t) {
        return turnoRepository.save(t);
    }*/

    @Override
    public TurnoDto buscar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if (id == null || id < 1) throw new BadRequestException("El ID del turno no puede ser null ni negativo");
        Turno turnoEncontrado = turnoRepository.findById(id).orElse(null);
        if (turnoEncontrado == null) throw new ResourceNotFoundException("No se encontró el turno con ID " + id);
        return Mapper.map(springConfig.getModelMapper(), turnoEncontrado, TurnoDto.class);
    }

    /*public Optional<Turno>/*Turno*/ /*buscar(Long id) {
        return turnoRepository.findById(id);
    }*/

    @Override
    public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if (id == null || id < 1) throw new BadRequestException("El ID del turno no puede ser null ni negativo");
        if (!turnoRepository.existsById(id)) throw new ResourceNotFoundException("No existe ningún turno con ID: " + id);
        turnoRepository.deleteById(id);
    }

    /*public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }*/
}

package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.impl.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    /*@Autowired
    private TurnoRepository turnoRepository;*/

    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardar(Turno t) {
        //t.setFechaIngreso(new Date());
        return turnoRepository.save(t);
    }

    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    public Optional<Turno>/*Turno*/ buscar(Long id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    public Turno actualizar(Turno t) {
        return turnoRepository.save(t);
    }
}

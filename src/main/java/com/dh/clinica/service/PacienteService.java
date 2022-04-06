package com.dh.clinica.service;


import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.repository.impl.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    /*@Autowired
    private PacienteRepository pacienteRepository;*/

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente p) {
        //p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente>/*Paciente*/ buscar(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente actualizar(Paciente p) {
        return pacienteRepository.save(p);
    }
}

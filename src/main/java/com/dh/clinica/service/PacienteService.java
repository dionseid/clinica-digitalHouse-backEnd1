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

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardar(Paciente p) {
        //p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Paciente buscar(Long id) {
        return pacienteRepository.findById(id).get();
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    /*public Paciente actualizar(Paciente p) {
        return pacienteRepository.actualizar(p);
    }*/
}

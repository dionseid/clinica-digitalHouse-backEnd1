package com.dh.clinica.service;

import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Turno;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.repository.impl.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardar(Turno t) {
        //t.setFechaIngreso(new Date());
        return turnoRepository.save(t);
    }

    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    public Turno buscar(Long id) {
        return turnoRepository.findById(id).get();
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    /*public Turno actualizar(Turno t) {
        return turnoRepository.actualizar(t);
    }*/
}

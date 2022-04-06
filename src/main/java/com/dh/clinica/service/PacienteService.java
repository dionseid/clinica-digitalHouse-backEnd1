package com.dh.clinica.service;


import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Paciente;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente p) {
        //p.setFechaIngreso(new Date());
        return pacienteIDao.guardar(p);
    }

    public Paciente buscar(Long id) {
        return pacienteIDao.buscar(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteIDao.buscarTodos();
    }

    public void eliminar(Long id) {
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizar(Paciente p) {
        return pacienteIDao.actualizar(p);
    }
}

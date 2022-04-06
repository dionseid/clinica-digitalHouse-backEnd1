package com.dh.clinica.service;

import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Odontologo;
import org.springframework.stereotype.Service;


import java.util.List;

//@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoDao.guardar(odontologo);
    }

    public void eliminar(Long id) {
        odontologoDao.eliminar(id);
    }

    public Odontologo buscar(Long id) {
        return odontologoDao.buscar(id);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoDao.buscarTodos();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoDao.actualizar(odontologo);
    }
}

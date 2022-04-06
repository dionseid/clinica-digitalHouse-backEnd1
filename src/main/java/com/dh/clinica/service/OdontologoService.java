package com.dh.clinica.service;

import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardar(Odontologo o) {
        return odontologoRepository.save(o);
    }

    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Odontologo buscar(Long id) {
        return odontologoRepository.findById(id).get();
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    /*public Odontologo actualizar(Odontologo o) {
        return odontologoRepository.actualizar(o);
    }*/
}

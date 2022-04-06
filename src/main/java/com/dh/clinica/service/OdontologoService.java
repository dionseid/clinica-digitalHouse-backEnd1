package com.dh.clinica.service;

import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    /*@Autowired
    private OdontologoRepository odontologoRepository;*/

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo o) {
        return odontologoRepository.save(o);
    }

    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Optional<Odontologo>/*Odontologo*/ buscar(Long id) {
        //return odontologoRepository.findById(id).get();
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> listar() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo o) {
        return odontologoRepository.save(o); // Considerando esta implementación ¿No debería eliminar el odontólogo desactualizado?
    }
}

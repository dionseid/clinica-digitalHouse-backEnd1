package com.dh.clinica.service;


import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Domicilio;
import com.dh.clinica.repository.impl.DomicilioRepository;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    /*@Autowired
    private DomicilioRepository domicilioRepository;*/

    private final DomicilioRepository domicilioRepository; // Está bien que no podamos cambiar a posteriori la implementación del servicio?

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio d) {
        return domicilioRepository.save(d);
    }

    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

    public Optional<Domicilio>/*Domicilio*/ buscar(Long id) {
        //return domicilioRepository.findById(id).get();
        //return Optional.of(domicilioRepository.getById(id));
        return domicilioRepository.findById(id);
    }

    public List<Domicilio> listar() {
        return domicilioRepository.findAll();
    }

    /*public Domicilio actualizar(Domicilio d) {
        return domicilioRepository.actualizar(d);
    }
    }*/
}

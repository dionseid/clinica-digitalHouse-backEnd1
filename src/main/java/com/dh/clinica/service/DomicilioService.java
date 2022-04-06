package com.dh.clinica.service;


import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Domicilio;
import com.dh.clinica.repository.impl.DomicilioRepository;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DomicilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    public Domicilio guardar(Domicilio d) {
        return domicilioRepository.save(d);
    }

    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

    public Domicilio buscar(Long id) {
        return domicilioRepository.findById(id).get();
    }

    public List<Domicilio> buscarTodos() {
        return domicilioRepository.findAll();
    }

    /*public Domicilio actualizar(Domicilio d) {
        return domicilioRepository.actualizar(d);
    }
    }*/
}

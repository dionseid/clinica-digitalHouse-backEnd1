package com.dh.clinica.service;


import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Domicilio;
import org.springframework.stereotype.Service;


import java.util.List;

//@Service
public class DomicilioService {
    private IDao<Domicilio> domicilioDao;

    public DomicilioService(IDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }
    public Domicilio guardar(Domicilio d){
        domicilioDao.guardar(d);
        return d;
    }
    public Domicilio buscar(Long id){
        return domicilioDao.buscar(id);
    }
    public List<Domicilio> buscarTodos(){
        return domicilioDao.buscarTodos();
    }
    public void eliminar(Long id){
        domicilioDao.eliminar(id);
    }
}

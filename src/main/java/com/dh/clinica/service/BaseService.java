package com.dh.clinica.service;

import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, Id, R extends JpaRepository<T, Id>> {
    @Autowired
    protected R repositorio;

    public T guardar(T t) {
        return repositorio.save(t);
    }

    public List<T> listar() {
        return repositorio.findAll();
    }

    public T actualizar(T t) throws BadRequestException, ResourceNotFoundException {
        return repositorio.save(t);
    }

    public Optional<T> buscar(Id id) throws BadRequestException, ResourceNotFoundException {
        return repositorio.findById(id);
    }

    public void eliminar(Id id) throws BadRequestException, ResourceNotFoundException {
        repositorio.deleteById(id);
    }

    public void delete(T t) {
        repositorio.delete(t);
    }
}

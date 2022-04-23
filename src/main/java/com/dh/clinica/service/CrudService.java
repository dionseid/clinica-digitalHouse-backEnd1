package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService<T> {
    T guardar(T t) throws BadRequestException, ResourceNotFoundException;

    List<T> listar();

    T actualizar(T t) throws BadRequestException, ResourceNotFoundException;

    T buscar(Integer id) throws BadRequestException, ResourceNotFoundException;

    void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException;
}

package com.dh.clinica.repository;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

     T guardar(T t);
     T buscar(Long id);
     void eliminar(Long id);
     List<T> buscarTodos();
     T actualizar(T t);


}

package com.dh.clinica.controller;

/*import com.valva.proyectointegrador.exceptions.BadRequestException;
import com.valva.proyectointegrador.exceptions.ResourceNotFoundException;*/
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<T> {
    ResponseEntity<?> guardar(@RequestBody T t) throws BadRequestException, ResourceNotFoundException; //throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<?> listar() throws BadRequestException, ResourceNotFoundException;

    //ResponseEntity<?> actualizar(@RequestBody T t); //throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<?> buscar(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException;

    /*ResponseEntity<String> eliminar(@PathVariable Integer id); //throws BadRequestException, ResourceNotFoundException;*/
}
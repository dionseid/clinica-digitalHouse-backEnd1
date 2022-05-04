package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CrudController;
import com.dh.clinica.model.dto.DomicilioDto;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController implements CrudController<DomicilioDto> {
    // Puede ser que no necesite este API!
    /*@Autowired
    private DomicilioService domicilioService;

    @PostMapping()
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio) {
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }*/

    @Override
    public ResponseEntity<?> guardar(DomicilioDto domicilioDto) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<?> listar() throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<?> actualizar(DomicilioDto domicilioDto) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<?> buscar(Integer id) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<String> eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {
        return null;
    }
}

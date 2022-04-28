package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CrudController;
import com.dh.clinica.persistance.entity.dto.OdontologoDto;
import com.dh.clinica.persistance.entity.dto.OdontologoTurnosDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("odontologos")
public class OdontologoController implements CrudController<OdontologoDto> {
    @Autowired
    private OdontologoService odontologoService;

    @Override
    @PostMapping()
    public ResponseEntity<?> guardar(@RequestBody OdontologoDto o) throws BadRequestException, ResourceNotFoundException {
        odontologoService.guardar(o);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(odontologoService.listar());
    }

    @Override
    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDto o) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<?> response = null;

        if (o.getId() != null && odontologoService.buscar(o.getId()) != null) {
            odontologoService.actualizar(o);
            response = ResponseEntity.ok(o);
        } else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @Override
    @GetMapping("id/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws BadRequestException {
        OdontologoDto o = odontologoService.buscar(id);

        return ResponseEntity.ok(o);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException {
        ResponseEntity<String> response = null;

        if (odontologoService.buscar(id) != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping("{id}/turnos")
    public ResponseEntity<Set<OdontologoTurnosDto>> listarTurnos(@PathVariable Integer id) {
        return ResponseEntity.ok(odontologoService.listarTurnos(id));
    }

    @GetMapping("nombre")
    public Set<OdontologoDto> listarPorNombre(@RequestParam String apellido) {
        return odontologoService.listarPorNombre(apellido);
    }
}

package com.dh.clinica.controller.impl;

//import com.dh.clinica.repository.impl.OdontologoDaoH2;

import com.dh.clinica.entity.dto.OdontologoDto;
import com.dh.clinica.entity.dto.OdontologoTurnosDto;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<?/*OdontologoDto*/> guardar(@RequestBody OdontologoDto o) {
        //return ResponseEntity.ok(odontologoService.guardar(o));
        odontologoService.guardar(o);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public Collection<OdontologoDto>/*ResponseEntity<Set*//*List<OdontologoDto/*Odontologo>>*/ listar() {
        //return ResponseEntity.ok(odontologoService.listar());
        return odontologoService.listar();
    }

    @PutMapping()
    public ResponseEntity<?/*OdontologoDto*//*Odontologo*/> actualizar(@RequestBody OdontologoDto/*Odontologo*/ o) {
        ResponseEntity<?/*OdontologoDto*//*Odontologo*/> response = null;

        if (o.getId() != null && odontologoService.buscar(o.getId()) != null/*.isPresent()*/) {
            odontologoService.actualizar(o);
            response = ResponseEntity.ok(HttpStatus.OK);
        } else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping("id/{id}")
    public OdontologoDto/*ResponseEntity<OdontologoDto/*Odontologo>*/ buscar(@PathVariable Long id) {
        OdontologoDto o = odontologoService.buscar(id)/*.orElse(null)*/;

        //return ResponseEntity.ok(o); // Me va a devolver status OK aunque la respuesta sea un objeto nulo
        return o;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?/*String*/> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (odontologoService.buscar(id) != null/*.isPresent()*/) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping("{id}/turnos")
    public ResponseEntity<Set<OdontologoTurnosDto>> listarTurnos(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.listarTurnos(id));
    }

    @GetMapping("nombre")
    public Set/*ResponseEntity*/<OdontologoDto/*Odontologo*/> listarPorNombre(@RequestParam/*@PathVariable*/ String apellido) { // Considerando la explicación del prof de DTO, me podría servir tener un DTO de Odontologo con la propiedad nombre que combine nombre y apellido
        /*Odontologo o = odontologoService.listarPorNombre(apellido);
        if (o != null) return ResponseEntity.ok(o);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();*/
        return odontologoService.listarPorNombre(apellido);
    }

    /*@PostMapping("/agregarTurno/{id}")
    public ResponseEntity<Odontologo> agregarTurno(@RequestBody Turno t, @PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.agregarTurno(id, t));
    }*/ // No agrego turnos en vistas de Odontologo, sino en la de Turno
}

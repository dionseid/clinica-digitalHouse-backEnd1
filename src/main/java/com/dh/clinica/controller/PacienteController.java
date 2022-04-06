package com.dh.clinica.controller;

//import com.dh.clinica.repository.impl.PacienteDaoH2;
import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente p) {
        return ResponseEntity.ok(pacienteService.guardar(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Paciente p = pacienteService.buscar(id).orElse(null);

        return ResponseEntity.ok(p);
    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente p) { // ResponseEntity<Paciente> indica que retornamos un Paciente en body
        ResponseEntity<Paciente> response = null;

        if (p.getId() != null && pacienteService.buscar(p.getId())/* != null*/.isPresent())
            response = ResponseEntity.ok(pacienteService.actualizar(p));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscar(id)/* != null*/.isPresent()) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminade");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    // Listar todes les pacientes
    /*@GetMapping()
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.listar());
    }*/
}

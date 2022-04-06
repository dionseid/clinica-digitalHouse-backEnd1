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
    /*@Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscar(id);

        return ResponseEntity.ok(paciente);
    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) { // ResponseEntity<Paciente> indica que retornamos un Paciente en body
        ResponseEntity<Paciente> response = null;

        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()) != null)
            response = ResponseEntity.ok(pacienteService.actualizar(paciente));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscar(id) != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    // Listar todes les pacientes
    @GetMapping()
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }*/
}

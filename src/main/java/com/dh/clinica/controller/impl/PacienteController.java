package com.dh.clinica.controller.impl;

//import com.dh.clinica.repository.impl.PacienteDaoH2;
import com.dh.clinica.controller.CrudController;
import com.dh.clinica.model.dto.PacienteDto;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.impl.PacienteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements CrudController<PacienteDto> {
    @Autowired
    private PacienteService pacienteService;

    //@Override
    @ApiOperation(value = "Crear un nueve paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success | OK"),
            @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping()
    public ResponseEntity<?> guardar(@RequestBody PacienteDto p) throws BadRequestException, ResourceNotFoundException {
        p.setFechaIngreso(LocalDate.now());
        PacienteDto pacienteInsertade = pacienteService.guardar(p);
        return ResponseEntity.ok(pacienteInsertade);
    }

    // Listar todes les pacientes
    //@Override
    @ApiOperation(value = "Busca todes les pacientes")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request") })
    @GetMapping()
    public ResponseEntity<List<PacienteDto>> listar() throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.listar());
    }

    /*@PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente p) { // ResponseEntity<Paciente> indica que retornamos un Paciente en body
        ResponseEntity<Paciente> response = null;

        if (p.getId() != null && pacienteService.buscar(p.getId())/* != null*//*.isPresent())*/
            /*response = ResponseEntity.ok(pacienteService.actualizar(p));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }*/

    //@Override
    @ApiOperation(value = "Actualiza un paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success | OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    @PutMapping
    public ResponseEntity<?> actualizar(PacienteDto pacienteDto) throws BadRequestException, ResourceNotFoundException {
        Integer pacienteDtoId = pacienteDto.getId();

        if (pacienteDtoId != null && pacienteService.buscar(pacienteDtoId) != null)
            return ResponseEntity.ok(pacienteService.actualizar(pacienteDto));
        else throw new ResourceNotFoundException("No existe ningún paciente con ID: " + pacienteDtoId);
    }

    //@Override
    @ApiOperation(value = "Busca un paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success | OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request") })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        PacienteDto p = pacienteService.buscar(id);
        return ResponseEntity.ok(p);
    }

    /*

    @Override
    public ResponseEntity<String> eliminar(Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscar(id)*//* != null*//*.isPresent()) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminade");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }*/

    @Override
    @ApiOperation(value = "Elimina un paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Se eliminó le paciente con ID: " + id);
    }
}

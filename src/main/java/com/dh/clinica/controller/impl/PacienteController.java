package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CrudController;
import com.dh.clinica.persistance.entity.dto.PacienteDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.impl.DomicilioService;
import com.dh.clinica.service.impl.PacienteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/pacientes")
public class PacienteController implements CrudController<PacienteDto> {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    @Override
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

    @Override
    @ApiOperation(value = "Busca todes les pacientes")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request") })
    @GetMapping()
    public ResponseEntity<List<PacienteDto>> listar() throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @Override
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

    @Override
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

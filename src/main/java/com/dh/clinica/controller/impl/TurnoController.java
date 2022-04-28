package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CrudController;
import com.dh.clinica.persistance.entity.dto.TurnoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.service.impl.TurnoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController implements CrudController<TurnoDto> {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Override
    @ApiOperation(value = "Crea un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 400, message = "bad request") })
    @PostMapping()
    public ResponseEntity<?> guardar(@RequestBody TurnoDto turno) throws BadRequestException, ResourceNotFoundException {
        TurnoDto turnoGuardado = turnoService.guardar(turno);
        return ResponseEntity.ok(turnoGuardado);
    }

    @Override
    @ApiOperation(value = "Busca todos los turnos")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "bad request") })
    @GetMapping
    public ResponseEntity<List<TurnoDto>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @Override
    @ApiOperation(value = "Actualiza un turno")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        TurnoDto actualizado = turnoService.actualizar(turnoDto);
        return ResponseEntity.ok(actualizado);
    }

    @Override
    @ApiOperation(value = "Busca un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        TurnoDto turnoABuscar = turnoService.buscar(id);
        return ResponseEntity.ok(turnoABuscar);
    }

    @Override
    @ApiOperation(value = "Elimina un turno")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> response;
        if (turnoService.buscar(id) != null) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}

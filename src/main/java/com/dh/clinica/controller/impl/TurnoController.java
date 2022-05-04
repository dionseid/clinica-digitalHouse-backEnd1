package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CrudController;
import com.dh.clinica.model.dto.TurnoDto;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.util.exceptions.ResourceNotFoundException;
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
        //ResponseEntity<Turno> response;
        //if (pacienteService.buscar(turno.getPaciente().getId()) != null/*.isPresent()*/ && odontologoService.buscar(turno.getOdontologo().getId()) != null/*.isPresent()*/) //{
            //response = ResponseEntity.ok(turnoService.guardar(turno));
            //odontologoService.agregarTurno(t.getPaciente().getId(), t);
        /*}*/ //else
            //response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        //return response;

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
        if (turnoService.buscar(id) != null) { // Esta validacion no esta en el enunciado del ejericio, pero se las dejo para que la tengan
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    /*@GetMapping("/turnoAlta")
    public String welcome(Model model) {
        model.addAttribute("nombre", "Juan Perez 2");
        model.addAttribute("apellido", "Garcia");
        model.addAttribute("turnos", turnoService.listar());
        return "turnoAlta";
    }

    /*@RequestMapping(value="/developer/{id}/skills", method=RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        Skill skill = skillRepository.findOne(skillId);
        Developer developer = repository.findOne(id);

        if (developer != null) {
            if (!developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
            }
            repository.save(developer);
            model.addAttribute("developer", repository.findOne(id));
            model.addAttribute("turnos", turnoService.listar());
            return "redirect:/developer/" + developer.getId();
        }

        model.addAttribute("developers", repository.findAll());
        return "redirect:/developers";
    }*/
}

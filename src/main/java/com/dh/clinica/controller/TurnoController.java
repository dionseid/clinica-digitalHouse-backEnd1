package com.dh.clinica.controller;

import com.dh.clinica.repository.impl.TurnoListRepository;
import com.dh.clinica.dominio.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    /*@Autowired
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;
        if (pacienteService.buscar(turno.getPaciente().getId()) != null && odontologoService.buscar(turno.getOdontologo().getId()) != null)
            response = ResponseEntity.ok(turnoService.registrarTurno(turno));

        else
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return response;


    }

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response;
        if (turnoService.buscar(id) != null) { // Esta validacion no esta en el enunciado del ejericio, pero se las dejo para que la tengan.
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.actualizar(turno));

    }

    @GetMapping("/turnoAlta")
    public String welcome(Model model) {*/
        /*model.addAttribute("nombre", "Juan Perez 2");
        model.addAttribute("apellido", "Garcia");*/
        /*model.addAttribute("turnos", turnoService.listar());
        return "turnoAlta";
    }*/

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

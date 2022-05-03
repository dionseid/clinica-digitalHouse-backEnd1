package com.dh.clinica.service.impl;

import com.dh.clinica.persistance.entity.dto.DomicilioDto;
import com.dh.clinica.persistance.entity.dto.PacienteDto;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    private PacienteDto paciente;

    @BeforeEach
    public void setUp() {
        DomicilioDto d = new DomicilioDto("Av Santa fe", 444, "CABA", "Buenos Aires");
        paciente = new PacienteDto("Santiago", "Paz", 88888888, "santiagopaz@gmail.com", d);
    }

    @Test
    public void guardar() throws Exception {
        PacienteDto p = pacienteService.guardar(paciente);
        assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void listar() throws Exception {
        pacienteService.guardar(paciente);
        assertNotEquals(0, pacienteService.listar().size());
    }

    @Test
    public void actualizar() throws Exception {
        PacienteDto p = pacienteService.guardar(paciente);
        PacienteDto original = pacienteService.buscar(p.getId());
        p.setNombre("Pablito");
        PacienteDto actualizade = pacienteService.actualizar(p);
        assertNotEquals(actualizade, original);
    }
}*/

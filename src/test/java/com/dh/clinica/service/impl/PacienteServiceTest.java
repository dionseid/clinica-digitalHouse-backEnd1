package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.dto.DomicilioDto;
import com.dh.clinica.entity.dto.PacienteDto;
import com.dh.clinica.service.impl.PacienteService;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(/*JUnit4*/SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    /*private static PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
    private DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());*/
    @Autowired
    private PacienteService pacienteService;
    private PacienteDto paciente;

    //@BeforeClass
    @BeforeEach
    public /*static*/ void setUp() {
        DomicilioDto d = new DomicilioDto("Av Santa fe", 444, "CABA", "Buenos Aires");
        paciente = new PacienteDto("Santiago", "Paz", 88888888, "santiagopaz@gmail.com", d);
        //Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "santiagopaz@gmail.com", 88888888, new Date(), d));
        //Domicilio d1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        //Paciente p1 = pacienteService.guardar(new Paciente("Micaela", "Perez", "micaelaperez@gmail.com", 99999999, new Date(), d1));
    }

    @Test
    public void guardar() throws Exception {
        PacienteDto p = pacienteService.guardar(paciente);
        assertNotNull(pacienteService.buscar(p.getId()));
    }

    //@Test
    //public void agregarYBuscarPacienteTest() {
        //this.cargarDataSet();
        //Domicilio d = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        //Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "tomaspereyra@gmail.com", 12345678, new Date(), d));

        //Assert.assertNotNull(pacienteService.buscar(p.getId()));
    //}

    /*@Test
    public void eliminarPacienteTest() {
        //pacienteService.eliminar(3L);
        //Assert.assertTrue(pacienteService.buscar(3L)/* == null*///.isEmpty());

    //}

    /*@Test
    public void traerTodos() {
        //List<Paciente> pacientes = pacienteService.listar();
        //Assert.assertTrue(!pacientes.isEmpty());
        //Assert.assertTrue(pacientes.size() /*> 0*///== 2);
        //System.out.println(pacientes);
    //}
}

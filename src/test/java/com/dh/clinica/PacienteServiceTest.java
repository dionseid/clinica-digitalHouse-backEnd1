package com.dh.clinica;

import com.dh.clinica.dominio.Domicilio;
import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.service.DomicilioService;
import com.dh.clinica.service.PacienteService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(/*JUnit4*/SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    /*private static PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
    private DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());*/
    @Autowired
    private PacienteService pacienteService;

    //@BeforeClass
    public /*static*/ void cargarDataSet() {
        Domicilio d = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "santiagopaz@gmail.com", 88888888, new Date(), d));
        Domicilio d1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        Paciente p1 = pacienteService.guardar(new Paciente("Micaela", "Perez", "micaelaperez@gmail.com", 99999999, new Date(), d1));
    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        Domicilio d = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "tomaspereyra@gmail.com", 12345678, new Date(), d));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void eliminarPacienteTest() {
        pacienteService.eliminar(3L);
        Assert.assertTrue(pacienteService.buscar(3L)/* == null*/.isEmpty());

    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.listar();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() /*> 0*/== 2);
        System.out.println(pacientes);
    }
}

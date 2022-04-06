package com.dh.clinica;

import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.service.OdontologoService;

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

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(/*JUnit4*/SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    //private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    @Autowired
    private OdontologoService odontologoService;

    //@BeforeClass
    public /*static*/ void cargarDataSet() {
        this.odontologoService.guardar(new Odontologo("Santiago", "Paz", 3455647));


    }

    @Test
    public void guardarOdontologo() {
        this.cargarDataSet();
        Odontologo o = odontologoService.guardar(new Odontologo("Juan", "Ramirez", 348971960));
        Assert.assertTrue(o.getId() != null);
    }

    @Test
    public void eliminarOdontologoTest() {
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscar(1L)/* == null*/.isEmpty());

    }

    @Test
    public void traerTodos() {
        List<Odontologo> odontologos = odontologoService.listar();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size()/* > 0*/ == 1);
        System.out.println(odontologos);
    }
}

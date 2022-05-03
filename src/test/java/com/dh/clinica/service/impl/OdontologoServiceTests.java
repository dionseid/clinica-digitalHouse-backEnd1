package com.dh.clinica.service.impl;

import com.dh.clinica.persistance.entity.dto.OdontologoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private OdontologoService odontologoService;
    private OdontologoDto odontologo;

    @BeforeEach
    public void setUp() {
        odontologo = new OdontologoDto();
        odontologo.setNombre("Enzo");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(24242424L);
    }

    public void cargarDataSet() throws Exception {
        odontologoService.guardar(new OdontologoDto("Perez", "Enzo", 24242424L));
        odontologoService.guardar(new OdontologoDto("Alvarez", "Julian", 9999L));
        odontologoService.guardar(new OdontologoDto("Armani", "Franco", 1111L));
    }

    @Test
    public void guardar() throws Exception {
        OdontologoDto odontologoGuardado = odontologoService.guardar(odontologo);
        Assert.assertTrue(odontologoGuardado.getId() != null);
    }

    @Test
    public void listar() throws Exception {
        cargarDataSet();
        List<OdontologoDto> odontologos = odontologoService.listar();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);
    }

    @Test
    public void buscar() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto odontologoABuscar = odontologoService.guardar(odontologo);
        OdontologoDto odontologoEncontrado = odontologoService.buscar(odontologoABuscar.getId());
        Assert.assertTrue(odontologoEncontrado != null);
    }

    @Test
    public void eliminar() throws Exception {
        OdontologoDto odontologoGuardado = odontologoService.guardar(odontologo);
        assertNotNull(odontologoService.buscar(odontologoGuardado.getId()));
        odontologoService.eliminar(odontologoGuardado.getId());
        assertThrows(com.dh.clinica.exceptions.BadRequestException.class, () -> odontologoService.buscar(odontologoGuardado.getId()));
    }

    @Test
    public void listarPorNombre() throws Exception {
        cargarDataSet();
        assertNotNull(odontologoService.listarPorNombre("Armani"));
        assertNotNull(odontologoService.listarPorNombre("Alvarez"));
    }
}*/

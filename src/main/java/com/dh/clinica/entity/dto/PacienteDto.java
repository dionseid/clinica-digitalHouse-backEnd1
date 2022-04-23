package com.dh.clinica.entity.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class PacienteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilio;

    public PacienteDto() {}

    public PacienteDto(String nombre, String apellido, int dni, String email, DomicilioDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.domicilio = domicilio;
    }

    public PacienteDto(String nombre, String apellido, int dni, String email, LocalDate fechaIngreso, DomicilioDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }
}

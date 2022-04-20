package com.dh.clinica.entity.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class PacienteDto {
    private Long id;
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

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDto domicilio) {
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
}

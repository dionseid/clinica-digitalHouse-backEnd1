package com.dh.clinica.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OdontologoTurnosDto {
    private Long id;
    private Date fecha;
    private PacienteDto paciente;

    // constructor para les tests
    public OdontologoTurnosDto(Date fecha, PacienteDto paciente) {
        this.fecha = fecha;
        this.paciente = paciente;
    }
}

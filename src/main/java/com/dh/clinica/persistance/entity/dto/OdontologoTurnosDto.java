package com.dh.clinica.persistance.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OdontologoTurnosDto {
    private Integer id;
    private Date fecha;
    private PacienteDto paciente;

    public OdontologoTurnosDto(Date fecha, PacienteDto paciente) {
        this.fecha = fecha;
        this.paciente = paciente;
    }
}

package com.dh.clinica.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurnoDto {
    private Integer id;

    private LocalDateTime diaHora;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public TurnoDto() { }

    public TurnoDto(LocalDateTime diaHora, PacienteDto paciente, OdontologoDto odontologo) {
        this.diaHora = diaHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }
}

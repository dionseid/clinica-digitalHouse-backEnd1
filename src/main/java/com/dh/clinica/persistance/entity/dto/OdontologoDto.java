package com.dh.clinica.persistance.entity.dto;

import lombok.Data;

@Data
public class OdontologoDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private Long matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(String apellido, String nombre, Long matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }
}

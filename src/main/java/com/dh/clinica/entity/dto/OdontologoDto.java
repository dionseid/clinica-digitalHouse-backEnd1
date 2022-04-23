package com.dh.clinica.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OdontologoDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private Long matricula;

    public OdontologoDto() {
    }

    // constructor para les tests
    public OdontologoDto(String apellido, String nombre, Long matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }
}

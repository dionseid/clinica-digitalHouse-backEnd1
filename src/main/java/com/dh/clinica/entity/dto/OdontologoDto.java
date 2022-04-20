package com.dh.clinica.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OdontologoDto {
    private Long id;
    private String apellido;
    private String nombre;
    private Integer matricula;

    public OdontologoDto() {
    }

    // constructor para les tests
    public OdontologoDto(String apellido, String nombre, Integer matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
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
}

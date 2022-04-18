package com.dh.clinica.entity.dto;

import com.dh.clinica.entity.Domicilio;
import lombok.Data;

@Data
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public DomicilioDto() {}

    public DomicilioDto(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }
}

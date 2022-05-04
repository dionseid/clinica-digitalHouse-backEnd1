package com.dh.clinica.service;

import com.dh.clinica.persistance.entity.Odontologo;
import com.dh.clinica.persistance.entity.Turno;
import com.dh.clinica.persistance.entity.dto.OdontologoDto;

public interface IOdontologoService extends CrudService<OdontologoDto> {
    Odontologo agregarTurno(Integer idOdontologo, Turno t);
}

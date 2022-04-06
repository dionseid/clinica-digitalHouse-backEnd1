package com.dh.clinica.repository.impl;

import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoListRepository implements IDao<Turno> {
    private List<Turno> turnos;

    public TurnoListRepository() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Long id) {
        for(Turno turno : turnos){
            if(turno.getId().equals(id)){
                return turno;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        for(Turno turno : turnos){
            if(turno.getId().equals(id)){
                turnos.remove(turno);
                return;
            }
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return turno;
    }
}

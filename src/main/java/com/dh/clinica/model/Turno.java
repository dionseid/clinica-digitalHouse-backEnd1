package com.dh.clinica.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    //@SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue//(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Integer id;

    @Setter
    private LocalDateTime diaHora;

    @Setter
    @ManyToOne(fetch = FetchType./*LAZY*/EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id"/*, referencedColumnName = "id"*/, nullable = false)
    private Paciente paciente;

    @Setter
    @ManyToOne(fetch = FetchType./*LAZY*/EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id"/*, referencedColumnName = "id"*/, nullable = false)
    private Odontologo odontologo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(id, turno.id) && Objects.equals(diaHora, turno.diaHora) && Objects.equals(paciente, turno.paciente) && Objects.equals(odontologo, turno.odontologo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diaHora, paciente, odontologo);
    }
}

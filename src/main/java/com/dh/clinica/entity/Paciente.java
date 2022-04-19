package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Getter
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    //@SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue//(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Long id;

    @Setter
    private String nombre;
    @Setter
    private String apellido;
    @Setter
    private String email;
    @Setter
    private Integer dni;
    @Setter
    private LocalDate fechaIngreso;

    @Setter
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id"/*, referencedColumnName = "id"*/, nullable = false)
    private Domicilio domicilio;
    @Setter
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Turno> turnos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id.equals(paciente.id) &&
                Objects.equals(dni, paciente.dni) &&
                Objects.equals(domicilio, paciente.domicilio) &&
                Objects.equals(nombre, paciente.nombre) &&
                Objects.equals(apellido, paciente.apellido) &&
                Objects.equals(email, paciente.email) &&
                Objects.equals(fechaIngreso, paciente.fechaIngreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, dni, fechaIngreso, domicilio);
    }
}

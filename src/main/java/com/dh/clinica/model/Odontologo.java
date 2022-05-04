package com.dh.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*@Entity
@Table
public class Odontologo {
    @Id
    @SequenceGenerator(name = "odnontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1) // Configurar la secuencia. allocationSize podría ser en cuánte incrementa el ID
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence") // Que genere de manera automática, incremental, los IDs. Pasamos como parámetro le SequenceGenerator creade
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public Odontologo() {
    }

    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo(Integer id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +
                '}';
    }
}*/



@Entity
@Table(name = "odontologos")
@Getter
public class Odontologo {
    @Id
    //@SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue//(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    @Column(name = "odontologo_id")
    private Integer id;

    @Setter
    private String nombre;
    @Setter
    private String apellido;
    @Setter
    private Long matricula;

    @OneToMany(
            mappedBy = "odontologo",
            fetch = FetchType.LAZY
    )
    @JsonIgnore // Sin esta anotación obtengo el error java.lang.IllegalArgumentException: failed to lazily initialize a collection of role: com.dh.clinica.entity.Odontologo.turnos, could not initialize proxy - no Session (through reference chain: com.dh.clinica.entity.Odontologo["turnos"])
    private Set<Turno> turnos = new HashSet<>();

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +
                ", turnos=" + turnos +
                '}';
    }
}
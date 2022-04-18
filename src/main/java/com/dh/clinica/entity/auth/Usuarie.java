package com.dh.clinica.entity.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Usuarie {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Integer dni;
    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UsuarieRoles",
            joinColumns = @JoinColumn(name = "id_usuario"), // No me toma "id_usuarie", me pregunto a qué hace referencia
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles;

    public Usuarie(Integer dni, String username, String email, String password, Set<Rol> roles) {
        this.dni = dni;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

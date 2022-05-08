package com.dh.clinica.model.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuarie implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Integer dni;
    @Setter
    @Column(unique = true)
    private String username;
    @Setter
    private String email;
    @Setter
    private String password;

    /*@Enumerated(EnumType.STRING)
    private Roles rol;*/
    /*@ElementCollection
    private List<String> roles = new ArrayList<>();
    public void addRole(Roles role){
        this.roles.add(role.toString());
    }*/
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UsuarieRoles",
            joinColumns = @JoinColumn(name = "id_usuario"), // No me toma "id_usuarie", me pregunto a qué hace referencia
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles;*/

    public Usuarie(Integer dni, String username, String email, String password, Set<Roles> roles) {
        this.dni = dni;
        this.username = username;
        this.email = email;
        this.password = password;
        //this.rol = rol;
        this.roles = roles;
    }

    public Usuarie(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

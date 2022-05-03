package com.dh.clinica.entity.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Getter
//@NoArgsConstructor
@Entity
public class Usuarie implements UserDetails {
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

    /*@Enumerated(EnumType.STRING)
    private Roles rol;*/
    @ElementCollection
    private List<String> roles = new ArrayList<>();
    public void addRole(Roles role){
        this.roles.add(role.toString());
    }
    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UsuarieRoles",
            joinColumns = @JoinColumn(name = "id_usuario"), // No me toma "id_usuarie", me pregunto a qué hace referencia
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles;*/

    public Usuarie() {
    }

    public Usuarie(Integer dni, String username, String email, String password, List<Roles> roles) {
        this.dni = dni;
        this.username = username;
        this.email = email;
        this.password = password;
        //this.rol = rol;
        this.roles = Stream.of(roles.toString()).collect(Collectors.toList());
    }

    public Usuarie(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.name());

        //return Collections.singletonList(grantedAuthority);

        Collection<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
        return authorities;
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

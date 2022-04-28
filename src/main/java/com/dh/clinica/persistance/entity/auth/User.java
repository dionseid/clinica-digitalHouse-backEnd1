package com.dh.clinica.persistance.entity.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@ToString
@Getter
@Entity
public class User implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public User() {
    }

    public User(Integer dni, String username, String email, String password, Rol rol) {
        this.dni = dni;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
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
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.name());

        return Collections.singletonList(grantedAuthority);
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

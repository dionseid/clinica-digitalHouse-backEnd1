package com.dh.clinica.persistance.entity.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private Integer dni;
    private String username;
    private String mail;
    private String password;
    /*@Enumerated(EnumType.STRING)
    private Role role;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Role> roles = new HashSet<>();
    public void addRole(Role role){
        this.roles.add(role);
    }

    public User(Integer dni, String username, String mail, String password, Set<Role> roles) {
        this.dni = dni;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        //SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
        Collection<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        //return Collections.singletonList(grantedAuthority);
        return authorities;
    }
}

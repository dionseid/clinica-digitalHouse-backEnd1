package com.dh.clinica.entity.auth;

/*import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "roles")
@Getter
@NoArgsConstructor
@Entity
public class Rol {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private String name;

    public Rol(String name) {
        this.name = name;
    }
}*/

public enum Roles {
    USER,ADMIN
}

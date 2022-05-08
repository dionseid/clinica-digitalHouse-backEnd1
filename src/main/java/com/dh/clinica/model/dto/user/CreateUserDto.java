package com.dh.clinica.model.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private String password;
    private String password2;
}

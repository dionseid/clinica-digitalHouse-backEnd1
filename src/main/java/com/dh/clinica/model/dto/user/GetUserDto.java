package com.dh.clinica.model.dto.user;

import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetUserDto {
    private String username;
    private Set<String> roles;
}

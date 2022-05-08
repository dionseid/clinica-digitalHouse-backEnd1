package com.dh.clinica.model.dto.user;

import com.dh.clinica.model.auth.Roles;
import com.dh.clinica.model.auth.Usuarie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    public GetUserDto convertUsuarieToGetUserDto(Usuarie usuarie) {
        return GetUserDto.builder()
                .username(usuarie.getUsername())
                .roles(usuarie.getRoles().stream()
                        .map(Roles::name)
                        .collect(Collectors.toSet())
                ).build();
    }
}

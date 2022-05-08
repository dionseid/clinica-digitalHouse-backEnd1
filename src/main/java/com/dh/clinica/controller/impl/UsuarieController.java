package com.dh.clinica.controller.impl;

import com.dh.clinica.model.auth.Usuarie;
import com.dh.clinica.model.dto.user.CreateUserDto;
import com.dh.clinica.model.dto.user.GetUserDto;
import com.dh.clinica.model.dto.user.UserDtoConverter;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.service.impl.auth.UsuarieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarieController {
    private final UsuarieService usuarieService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping
    public ResponseEntity<GetUserDto> guardar(@RequestBody CreateUserDto nueveUsuarie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userDtoConverter.convertUsuarieToGetUserDto(
                    usuarieService.guardar(nueveUsuarie)));
    }

    @ApiOperation(value = "Busca todes les usuaries")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request") })
    @GetMapping("/usuaries")
    public ResponseEntity<List<Usuarie>> listar() {
        return ResponseEntity.ok(usuarieService.listar());
    }

    @GetMapping("/403")
    public String error() {
        return "<h1>Error 403: acceso denegado o prohibido</h1>";
    }
}

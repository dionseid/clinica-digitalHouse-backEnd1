package com.dh.clinica.controller.impl;

import com.dh.clinica.model.auth.Usuarie;
import com.dh.clinica.util.exceptions.BadRequestException;
import com.dh.clinica.service.impl.auth.UsuarieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarieController {
    private final UsuarieService usuarieService;

    @ApiOperation(value = "Crea un nueve usuarie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success | OK"),
            @ApiResponse(code = 400, message = "Bad Request") })



    @PostMapping
    public ResponseEntity<Usuarie> crear(@RequestBody Usuarie nueveUsuarie) throws BadRequestException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuarieService.crear(nueveUsuarie));
        } catch () {}



        Usuarie usuarie = usuarieService.guardar(nueveUsuarie);
        return ResponseEntity.ok(usuarie);
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

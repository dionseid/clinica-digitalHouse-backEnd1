/*package com.dh.clinica.controller.impl;

import com.dh.clinica.entity.auth.Usuarie;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.service.impl.auth.UsuarieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarieController {
    private final UsuarieService usuarieService;

    @Autowired
    public UsuarieController(UsuarieService usuarieService) {
        this.usuarieService = usuarieService;
    }

    @ApiOperation(value = "Crea un nueve usuarie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success | OK"),
            @ApiResponse(code = 400, message = "Bad Request") })
    @PostMapping("/usuaries")
    public ResponseEntity<Usuarie> crear(@RequestBody Usuarie u) throws BadRequestException {
        Usuarie usuarie = usuarieService.guardar(u);
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
}*/

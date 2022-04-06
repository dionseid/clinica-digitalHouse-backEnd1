package com.dh.clinica.controller;

import com.dh.clinica.dominio.Domicilio;
import com.dh.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    /*@Autowired
    private DomicilioService domicilioService;

    @PostMapping()
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio) {
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }*/
}

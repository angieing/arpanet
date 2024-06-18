package com.arpanet.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpanet.code.exception.ModeloNotFoundException;
import com.arpanet.code.model.VentaEntitie;
import com.arpanet.code.service.Ventaservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @GetMapping("validar-sesion")
    public ResponseEntity<?> validarSesion() {        
        try {            
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("VER ex general: " + e.getMessage());            
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

}
package com.arpanet.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.arpanet.code.exception.ModeloNotFoundException;
import com.arpanet.code.model.ClienteEntitie;
import com.arpanet.code.respuestas.Respuesta;
import com.arpanet.code.service.LoginService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
	private LoginService loginService; 

    @GetMapping("validar-sesion")
    public ResponseEntity<?> validarSesion() {        
        try {            
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("VER ex general: " + e.getMessage());            
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Login
     * @param venta
     * @return
     */
	@PostMapping(value = "/validar-credenciales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta> validarCredencialesAcceso(
            @RequestParam(value = "usuario", required = false) String usuario,
            @RequestParam(value = "password", required = false) String password) {
        
        //Date x = new Date(System.currentTimeMillis() + (60000 * 1));
        System.out.println("FEcha: " + usuario);
        Respuesta out = new Respuesta();
        try {
            out = loginService.validarCredenciales(usuario, password);
            if (out.getStatus().equals(HttpStatus.UNAUTHORIZED)) {
                throw new ResponseStatusException(out.getStatus());
            }
            if(out.getStatus().equals(HttpStatus.CONFLICT)){
                throw new ResponseStatusException(out.getStatus());
            }
            return new ResponseEntity<>(out, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
        }
    }

   
}
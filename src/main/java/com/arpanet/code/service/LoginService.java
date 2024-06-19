package com.arpanet.code.service;

import com.arpanet.code.respuestas.Respuesta;

public interface LoginService {
    public Respuesta validarCredenciales(String usuario, String password);
}  


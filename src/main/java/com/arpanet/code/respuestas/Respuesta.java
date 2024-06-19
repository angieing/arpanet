package com.arpanet.code.respuestas;

import org.springframework.http.HttpStatus;

//import io.jsonwebtoken.Claims;
import lombok.Data;

@Data
public class Respuesta {
    private Object obj;
    private HttpStatus status;    
    private String msn;

}

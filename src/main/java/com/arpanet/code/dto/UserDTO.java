package com.arpanet.code.dto;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;



import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String usuario;
    private String pass;    
   
}

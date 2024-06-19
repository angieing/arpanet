package com.arpanet.code.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.arpanet.code.dto.UserDTO;
import com.arpanet.code.model.UsuarioEntitie;
import com.arpanet.code.repository.UserRepository;
import com.arpanet.code.respuestas.Respuesta;
import java.util.Optional;

@Service
public class LoginImplService implements LoginService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public Respuesta validarCredenciales(String usuario, String password) {
        Respuesta out = new Respuesta();
        try {
            Optional<UsuarioEntitie> pasEncode = userRepository.findByUsuario(usuario);
            //Boolean ex = userRepository.existsByUsuarioAndPass(usuario, password);
            if (pasEncode.isPresent()) {              
                    UsuarioEntitie obj = userRepository.getLogin(usuario, pasEncode.get().getPass());
                    if (obj != null) {
                        UserDTO objDto = modelMapper.map(obj, UserDTO.class);
                        out.setObj(objDto.getId());
                        out.setStatus(HttpStatus.ACCEPTED);
                    } else {
                        out.setStatus(HttpStatus.UNAUTHORIZED);
                    }
                
            } else {
                out.setStatus(HttpStatus.CONFLICT);
                out.setMsn("Usuario no es correcto");
            }

        } catch (Exception e) {
            //logger.info(e.getMessage());
            out.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            out.setMsn(e.getMessage());
        }
        return out;
    }
    
}

package com.arpanet.code.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpanet.code.model.UsuarioEntitie;


@Repository
public interface UserRepository extends JpaRepository<UsuarioEntitie, Long> {
    Optional<UsuarioEntitie> findByUsuario(String username);
    //Boolean existsByUsuarioAndPass(String username, String password);

    @Query(value="select usuario, pass from usuarios where usuario=:usuario and pass=:password", nativeQuery = true)
    UsuarioEntitie getLogin(String usuario, String password);
}

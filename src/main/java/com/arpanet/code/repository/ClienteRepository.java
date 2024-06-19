package com.arpanet.code.repository;

import com.arpanet.code.model.ClienteEntitie;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntitie, Long> {

    @Query(value = " select * from clientes ", nativeQuery = true)
    List<Object[]> buscar();

    @Transactional 
    @Modifying
    @Query(value="insert into clientes  VALUES (:tipo_identificacion, :identificacion, :nombres, :apellidos, :telefono, :direccion, :correo)", nativeQuery=true)
    int guardar(@Param("tipo_identificacion") String tipo_identificacion,@Param("identificacion") Long identificacion,  @Param("nombres") String nombres, @Param("apellidos")String apellidos, @Param("telefono")String telefono, @Param("direccion")String direccion,@Param("correo") String correo);

    @Transactional 
    @Modifying
    @Query(value="update clientes set nombres =:valor where identificacion =:id", nativeQuery=true)
    int actualizar(@Param("id") Long id, @Param("valor") String valor);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM clientes n WHERE n.identificacion =:id", nativeQuery=true)
    int borrar(@Param("id") Long id);

}

package com.arpanet.code.repository;

import com.arpanet.code.model.ProductosEntitie;

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
public interface ProductosRepository extends JpaRepository<ProductosEntitie, Long> {

    @Query(value = " select * from Productos ", nativeQuery = true)
    List<Object[]> buscar();

    @Transactional 
    @Modifying
    @Query(value="insert into productos  VALUES (sec_ventas.nextval, :nombre, :porcentaje)", nativeQuery=true)
    int guardar(@Param("nombre") String nombre, @Param("porcentaje") Float porcentaje);

    @Transactional 
    @Modifying
    @Query(value="update Productos set nombre =:valor where id =:id", nativeQuery=true)
    int actualizar(@Param("id") Long id, @Param("valor") String valor);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Productos p WHERE p.id =:id", nativeQuery=true)
    int borrar(@Param("id") Long id);

}

package com.arpanet.code.repository;

import com.arpanet.code.model.VentaEntitie;

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
public interface VentaRepositorio extends JpaRepository<VentaEntitie, Long> {

    @Query(value = "select * from ventas", nativeQuery = true)
    List<Object[]> buscar();

    @Transactional 
    @Modifying
    @Query(value="insert into ventas  VALUES (sec_ventas.nextval, :fecha, :subtotal, :impuestos, :total, :vendedor, :cliente, :tipoClientes, :tipoVendedor)", nativeQuery=true)
    int guardarVenta(@Param("fecha") Date fecha, @Param("subtotal") Float subtotal, @Param("impuestos") Float impuestos, @Param("total")Float total,@Param("vendedor") Integer vendedor, @Param("cliente")Integer cliente, @Param("tipoClientes") String tipoClientes, @Param("tipoVendedor") String tipoVendedor);

    @Transactional 
    @Modifying
    @Query(value="update ventas set subtotal =:valor where id_factura =:id", nativeQuery=true)
    int actualizarVenta(@Param("id") Long id, @Param("valor") Float valor);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM ventas n WHERE n.id_factura =:id", nativeQuery=true)
    int borrar(@Param("id") Long id);

}

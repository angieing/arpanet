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
    @Query(value="insert into ventas  VALUES (sec_ventas.nextval, :fecha, :subtotal, :impuestos, :total, :vendedor, :cliente)", nativeQuery=true)
    int guardarVenta(@Param("fecha") Date fecha, @Param("subtotal") Float subtotal, @Param("impuestos") Float impuestos, @Param("total")Float total,@Param("vendedor") Integer vendedor, @Param("cliente")Integer cliente);

    @Transactional 
    @Modifying
    @Query(value="update ventas set cliente=:cliente, fecha=:fecha, impuestos=:impuestos, subtotal=:subtotal, total=:total, vendedor=:vendedor where id_factura=:id", nativeQuery=true)
    int actualizarVenta(@Param("id") Long id, @Param("cliente") Integer cliente, @Param("fecha") Date fecha,@Param("impuestos") Float impuestos,@Param("subtotal") Float subtotal, @Param("total") Float total, @Param("vendedor") Integer vendedor);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM ventas n WHERE n.id_factura =:id", nativeQuery=true)
    int borrar(@Param("id") Long id);

    //indicadores
    @Query(value = "SELECT * FROM ventas WHERE EXTRACT(year FROM fecha) =:anio", nativeQuery = true)
    List<Object[]> buscarPorAnio(@Param("anio") int anio);

    @Query(value = "SELECT identificacion, nombres, apellidos, total_ventas FROM ( " +
            " SELECT v.vendedor as identificacion, " +
            "   ven.nombres  as nombres, " +
            "  ven.apellidos as apellidos, " +
            "  COUNT (*) AS total_ventas      " +          
            "  FROM ventas v  " +
            "  INNER JOIN vendedores ven ON v.vendedor = ven.identificacion " +
            "  GROUP BY v.vendedor, ven.nombres , ven.apellidos " +
            "  ORDER BY total_ventas DESC ) " +
            " WHERE ROWNUM=1", nativeQuery = true)
    List<Object[]> buscarVendedorMasVende();

    @Query(value = " SELECT EXTRACT(YEAR FROM fecha) AS año, SUM(subtotal) AS subtotal, SUM(impuestos) AS impuestos, SUM(total) AS total FROM ventas " +
    " where  EXTRACT(YEAR FROM fecha) =:anio  GROUP BY EXTRACT(YEAR FROM fecha)", nativeQuery = true)
    List<Object[]> buscarVentaAnioEspecifico(int anio);

    @Query(value = " SELECT v.cliente AS identificacion ,c.nombres ||' '|| c.apellidos AS nombres " +
    " , avg(v.total) as promedio " +
    " FROM ventas v  INNER JOIN clientes c ON c.identificacion = v.cliente " +
    " GROUP BY v.cliente,c.nombres ||' '|| c.apellidos ", nativeQuery = true)
    List<Object[]> buscarPromedioVentasCliente();

    @Query(value = " SELECT  EXTRACT (MONTH FROM fecha) AS Mes, EXTRACT (YEAR FROM fecha) AS Año, SUM(total) AS Total " +
    " FROM ventas GROUP BY EXTRACT (MONTH FROM fecha), EXTRACT (YEAR FROM fecha)", nativeQuery = true)
    List<Object[]> buscarAnioMesEspecifico();

}

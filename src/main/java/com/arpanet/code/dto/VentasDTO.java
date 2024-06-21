package com.arpanet.code.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class VentasDTO{
   private Long id=0L;
   private Date fecha;           
   private Float subtotal;            
   private Float impuestos;      
   private Float total;     
   private Integer vendedor;
   private Integer cliente;
   private String tipo_clientes;
   private String tipo_vendedor;
   private String nombres;
   private String apellidos;
   private String totalVentas;
   private BigDecimal anio;
   private BigDecimal mes;
   
}
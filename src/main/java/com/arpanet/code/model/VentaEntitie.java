package com.arpanet.code.model;

import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="ventas")
public class VentaEntitie{
        @Id
        @Column(name = "id_factura")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_ventas")
        @SequenceGenerator(name = "sec_ventas", allocationSize = 1, sequenceName = "SEQ_VENTAS")
        @Builder.Default
        Long id=0L;

        Date fecha;
        
        //@NotNull @NotBlank
        Float subtotal;
        
        //@NotNull @NotBlank
        Float impuestos;

        //@NotNull @NotBlank
        Float total;
        //@NotBlank(message = "Direccion es requerida")
        //@Size(min = 5, max = 50)

        Integer vendedor;
        Integer cliente;
       
}

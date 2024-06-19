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
@Table(name="vendedores")
public class VendedorEntitie{
        @Id
        @Column(name = "identificacion")
        //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_ventas")
        //@SequenceGenerator(name = "sec_ventas", allocationSize = 1, sequenceName = "SEQ_VENTAS")
        @Builder.Default
        Long id=0L;
        String tipoIdentificacion;
        String nombres;
        String apellidos;
        String direccion;
        String telefono;
        String correo;        
       
}

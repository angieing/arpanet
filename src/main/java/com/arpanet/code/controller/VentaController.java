package com.arpanet.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpanet.code.exception.ModeloNotFoundException;
import com.arpanet.code.model.VentaEntitie;
import com.arpanet.code.service.Ventaservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private Ventaservice service;

	@GetMapping("validar-sesion")
    public ResponseEntity<?> validarSesion() {        
        try {            
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("VER ex general: " + e.getMessage());            
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }
	
	
	@GetMapping(value = "listar/ventas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		System.out.println("verentraraqui>>");
		List<VentaEntitie> Ventas = new ArrayList<>();
		 Ventas = service.listaAll();
		 System.out.println("Resultado:>>" + Ventas.getClass().getSimpleName());
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}
	
	@PostMapping
    public ResponseEntity<VentaEntitie> crearVenta(@Valid @RequestBody VentaEntitie Venta){
        service.createVenta(Venta);
        return new ResponseEntity<VentaEntitie>(HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<VentaEntitie> getVentaById(@PathVariable("id") Long id){
		System.out.println("Est:>>" + id);
		VentaEntitie Venta = service.getVenta(id).orElseThrow(() -> new ModeloNotFoundException("Venta no encontrado $id"));
		return new ResponseEntity<VentaEntitie>(Venta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VentaEntitie> deleteVenta(@PathVariable("id") Long id){
		service.deleteVenta(id);
		return ResponseEntity.ok().build();
	}

	/*@PutMapping("/{id}")
	public Venta updateVenta(@PathVariable("id") Long id, @Valid @RequestBody Venta Venta) {
		Venta dbVenta = service.getVenta(id).orElseThrow(() -> new ModeloNotFoundException("Venta No enocntrado"));
		dbVenta.setNombres(Venta.getNombres());
		dbVenta.setApellidos(Venta.getApellidos());
		dbVenta.setDireccion(Venta.getDireccion());
		dbVenta.setEdad(Venta.getEdad());
		dbVenta.setCiudad(Venta.getCiudad());
		dbVenta.setPais(Venta.getPais());
		return service.updateVenta(dbVenta);
	}*/
}
package com.arpanet.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpanet.code.exception.ModeloNotFoundException;
import com.arpanet.code.model.ClienteEntitie;
import com.arpanet.code.service.Clienteservice;
import com.arpanet.code.service.Ventaservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
	private Clienteservice service;

    @GetMapping("validar-sesion")
    public ResponseEntity<?> validarSesion() {        
        try {            
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("VER ex general: " + e.getMessage());            
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * listar clientes
     * @return
     */
    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		
		List<ClienteEntitie> clientes = new ArrayList<>();
		 clientes = service.listaAll(); 
         System.out.println("verentraraqui>>" + clientes.size());
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

    /**
     * Crear clientes
     * @param venta
     * @return
     */
	@PostMapping(value = "crear")
	public ResponseEntity<ClienteEntitie> crearVentaSql(@Valid @RequestBody ClienteEntitie cliente){
		System.out.println(" BODY: " + cliente);
		try {
			int ver = service.crearVentaSql(cliente);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:: " + e.getMessage());
		}
				
		return new ResponseEntity<ClienteEntitie>(HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	public ResponseEntity<ClienteEntitie> getVentaById(@PathVariable("id") Long id){
		System.out.println("Est:>>" + id);
		ClienteEntitie cliente = service.getVenta(id).orElseThrow(() -> new ModeloNotFoundException("No encontrado $id: "+ id));
		return new ResponseEntity<ClienteEntitie>(cliente, HttpStatus.OK);
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<ClienteEntitie> deleteVenta(@PathVariable("id") Long id){
		service.deleteVenta(id);
		return ResponseEntity.ok().build();
	}*/

	@PutMapping("/{id}")
	public int updateVenta(@PathVariable("id") Long id, @Valid @RequestBody ClienteEntitie cliente) {
		System.out.println("==========>>> " + cliente + " /// "+ id);
        ClienteEntitie clienteUp = service.getVenta(id).orElseThrow(() -> new ModeloNotFoundException("Venta No enocntrado"));
		clienteUp.setTipoIdentificacion(cliente.getTipoIdentificacion());
		clienteUp.setId(cliente.getId());
		clienteUp.setNombres(cliente.getNombres());
		clienteUp.setApellidos(cliente.getApellidos());	
		clienteUp.setTelefono(cliente.getTelefono());	
		clienteUp.setDireccion(cliente.getDireccion());	
		clienteUp.setCorreo(cliente.getCorreo());
		
		return service.actualizarVentaSql(clienteUp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteEntitie> deleteVentaSql(@PathVariable("id") Long id){
		service.borrarVentaSql(id);
		return ResponseEntity.ok().build();
	}

}
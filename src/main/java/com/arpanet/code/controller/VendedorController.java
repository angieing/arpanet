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
import com.arpanet.code.model.VendedorEntitie;
import com.arpanet.code.model.VentaEntitie;
import com.arpanet.code.service.Clienteservice;
import com.arpanet.code.service.Vendedoresservice;
import com.arpanet.code.service.Ventaservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private Vendedoresservice service;

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
     * listar vendedores
     * @return
     */
    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		
		List<VendedorEntitie> vendedores = new ArrayList<>();
		 vendedores = service.listaAll(); 
         System.out.println("verentraraqui>>" + vendedores.size());
		return new ResponseEntity<>(vendedores, HttpStatus.OK);
	}

    /**
     * Crear vendedores
     * @param venta
     * @return
     */
	@PostMapping(value = "crear")
	public ResponseEntity<VendedorEntitie> crearVendedoresSql(@Valid @RequestBody VendedorEntitie vendedores){
		System.out.println(" BODY: " + vendedores);
		try {
			int ver = service.crearVendedoresSql(vendedores);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:: " + e.getMessage());
		}
				
		return new ResponseEntity<VendedorEntitie>(HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	public ResponseEntity<VendedorEntitie> getVendedorById(@PathVariable("id") Long id){
		System.out.println("Est:>>" + id);
		VendedorEntitie vendedores = service.getVendedores(id).orElseThrow(() -> new ModeloNotFoundException("No encontrado $id: "+ id));
		return new ResponseEntity<VendedorEntitie>(vendedores, HttpStatus.OK);
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<VendedorEntitie> deleteVenta(@PathVariable("id") Long id){
		service.deleteVenta(id);
		return ResponseEntity.ok().build();
	}*/

	@PutMapping("/{id}")
	public int updateVenta(@PathVariable("id") Long id, @Valid @RequestBody VendedorEntitie vendedores) {
		System.out.println("==========>>> " + vendedores + " /// "+ id);
        VendedorEntitie vendedoresUp = service.getVendedores(id).orElseThrow(() -> new ModeloNotFoundException("No encontrado"));
		vendedoresUp.setTipoIdentificacion(vendedores.getTipoIdentificacion());
		vendedoresUp.setId(vendedores.getId());
		vendedoresUp.setNombres(vendedores.getNombres());
		vendedoresUp.setApellidos(vendedores.getApellidos());	
		vendedoresUp.setTelefono(vendedores.getTelefono());	
		vendedoresUp.setDireccion(vendedores.getDireccion());	
		vendedoresUp.setCorreo(vendedores.getCorreo());
		
		return service.actualizarVendedoresSql(vendedoresUp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VendedorEntitie> deleteVentaSql(@PathVariable("id") Long id){
		service.borrarVendedoresSql(id);
		return ResponseEntity.ok().build();
	}

}
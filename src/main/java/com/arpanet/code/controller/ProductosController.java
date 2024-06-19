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
import com.arpanet.code.model.ProductosEntitie;
import com.arpanet.code.model.ProductosEntitie;
import com.arpanet.code.service.Productosservice;
import com.arpanet.code.service.Ventaservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
	private Productosservice service;

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
     * listar productos
     * @return
     */
    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {		
		List<ProductosEntitie> productos = new ArrayList<>();
        productos = service.listaAll(); 
         System.out.println("aqui>>" + productos.size());
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

    @PostMapping(value = "crear")
	public ResponseEntity<ProductosEntitie> crearSql(@Valid @RequestBody ProductosEntitie  productos){
		System.out.println(" BODY: " + productos);
		try {
			int ver = service.crearSql(productos);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Venta registrada: " + e.getMessage());
		}
				
		return new ResponseEntity<ProductosEntitie>(HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	public ResponseEntity<ProductosEntitie> getProductoById(@PathVariable("id") Long id){
		System.out.println("Est:>>" + id);
		ProductosEntitie prod = service.buscarPorId(id).orElseThrow(() -> new ModeloNotFoundException("Venta no encontrado $id"));
		return new ResponseEntity<ProductosEntitie>(prod, HttpStatus.OK);
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<ProductosEntitie> deleteVenta(@PathVariable("id") Long id){
		service.deleteVenta(id);
		return ResponseEntity.ok().build();
	}*/

	@PutMapping("/{id}")
	public int update(@PathVariable("id") Long id, @Valid @RequestBody ProductosEntitie productos) {
		ProductosEntitie prod = service.buscarPorId(id).orElseThrow(() -> new ModeloNotFoundException("No enocntrado"));
		prod.setId(productos.getId());
		prod.setNombre(productos.getNombre());
		prod.setPorcentaje_impuesto(productos.getPorcentaje_impuesto());		
		return service.actualizarSql(prod);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductosEntitie> deleteSql(@PathVariable("id") Long id){
		service.borrarSql(id);
		return ResponseEntity.ok().build();
	}

}
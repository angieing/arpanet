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

import com.arpanet.code.dto.VentasDTO;
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
	
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		System.out.println("verentraraqui>>");
		List<VentaEntitie> Ventas = new ArrayList<>();
		 Ventas = service.listaAll();
		 System.out.println("Resultado:>>" + Ventas);
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}

	@PostMapping(value = "crear")
	public ResponseEntity<VentaEntitie> crearVentaSql(@Valid @RequestBody VentaEntitie venta){
		System.out.println("Venta BODY: " + venta);
		try {
			int ver = service.crearVentaSql(venta);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Venta registrada: " + e.getMessage());
		}
				
		return new ResponseEntity<VentaEntitie>(HttpStatus.CREATED);
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

	/*@DeleteMapping("/{id}")
	public ResponseEntity<VentaEntitie> deleteVenta(@PathVariable("id") Long id){
		service.deleteVenta(id);
		return ResponseEntity.ok().build();
	}*/

	@PutMapping("/{id}")
	public int updateVenta(@PathVariable("id") Long id, @Valid @RequestBody VentaEntitie venta) {
		VentaEntitie dbVenta = service.getVenta(id).orElseThrow(() -> new ModeloNotFoundException("Venta No enocntrado"));
		System.out.println("EXITE?:: " +dbVenta);
		dbVenta.setFecha(venta.getFecha());
		dbVenta.setSubtotal(venta.getSubtotal());
		dbVenta.setImpuestos(venta.getImpuestos());
		dbVenta.setTotal(venta.getTotal());		
		System.out.println("SIEXITE?:: " +dbVenta);
		return service.actualizarVentaSql(id,dbVenta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VentaEntitie> deleteVentaSql(@PathVariable("id") Long id){
		service.borrarVentaSql(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Buscar todas las ventas por un año específico
	 * @return
	 */
	@GetMapping(value = "buscar/{anio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorAnio(@PathVariable("anio") int anio) {		
		List<VentaEntitie> Ventas = new ArrayList<>();
		System.out.println("===>" + anio);
		 Ventas = service.buscarPorAnio(anio);		 
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}

	/**
	 *  Cual es el vendedor que más ha vendido
	 * @return
	 */
	@GetMapping(value = "/ver-mas-vendido", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarVendedorMasVendido() {		
		List<VentasDTO> Ventas = new ArrayList<>();		
		 Ventas = service.buscarMasVendidoVendedor();		 
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}

	/**
	 *  Cuanto se ha vendido en un año específico
	 * @return
	 */
	@GetMapping(value = "/anio-especifico/{anio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorAnioEspecifico(@PathVariable("anio") int anio) {		
		List<VentasDTO> Ventas = new ArrayList<>();		
		 Ventas = service.buscarPorAnioEspecifico(anio);		 
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}

	/**
	 *  Promedio ventas por cliente
	 * @return
	 */
	@GetMapping(value = "/promedio-ventas-cliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPromedioVentasCliente() {		
		List<VentasDTO> Ventas = new ArrayList<>();		
		 Ventas = service.buscarPromedioVentasCliente();		 
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}

	/**
	 *  Cuanto se ha vendido en un año y mes específico
	 * @return
	 */
	@GetMapping(value = "/anio-mes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarVentasAnioMesEspecifico() {		
		List<VentasDTO> Ventas = new ArrayList<>();		
		 Ventas = service.buscarVentasAnioMesEspecifico();		 
		return new ResponseEntity<>(Ventas, HttpStatus.OK);
	}


}

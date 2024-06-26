package com.arpanet.code.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.arpanet.code.dto.VentasDTO;
import com.arpanet.code.model.VentaEntitie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpanet.code.repository.VentaRepositorio;

@Service
public class VentaserviceImpl implements Ventaservice{

	@Autowired
	private VentaRepositorio ventaRepositorio;
	
	@Override
	public List<VentaEntitie> findVentaAll() {
		return ventaRepositorio.findAll();
	}

	@Override
	public VentaEntitie createVenta(VentaEntitie venta) {
		return ventaRepositorio.saveAndFlush(venta);
	}

	@Override
	public VentaEntitie updateVenta(VentaEntitie Venta) {
		return ventaRepositorio.save(Venta);
	}

	@Override
	public void deleteVenta(Long id) {
		ventaRepositorio.deleteById(id);
	}

	@Override
	public Optional<VentaEntitie> getVenta(Long id) {
		return ventaRepositorio.findById(id);
	}

	@Override
	public List<VentaEntitie> listaAll() {
		List<VentaEntitie> lista = new ArrayList<>();
		List<Object[]> ver = ventaRepositorio.buscar();	
		
        for (int i = 0; i < ver.size(); i++) {
            VentaEntitie obj = new VentaEntitie();
			BigDecimal x = (BigDecimal) ver.get(i)[0];

			BigDecimal a  = (BigDecimal) ver.get(i)[2]; 
  
        // Using floatValue() method 
        float f = a.floatValue(); 
            obj.setId(x.longValue());
            obj.setFecha((Date) ver.get(i)[1]);
			obj.setSubtotal(a.floatValue());
			obj.setImpuestos(((BigDecimal)ver.get(i)[3]).floatValue());
			obj.setTotal(((BigDecimal)ver.get(i)[4]).floatValue());
			obj.setVendedor(((BigDecimal)ver.get(i)[5]).intValue());
			obj.setCliente(((BigDecimal)ver.get(i)[6]).intValue());
			obj.setTipo_clientes(String.valueOf(ver.get(i)[7]));
			obj.setTipo_vendedor(String.valueOf(ver.get(i)[8]));
            lista.add(obj);
        }       
		return lista;
	}

	/** inserta ventas nativa */
	@Override
    public int crearVentaSql(VentaEntitie obj) {
        int retorno = 0;		
        retorno = ventaRepositorio.guardarVenta(obj.getFecha(), obj.getSubtotal(), obj.getImpuestos(), obj.getTotal(), obj.getVendedor(),obj.getCliente(),obj.getTipo_clientes(),obj.getTipo_vendedor());
        return retorno;
    }

	/**
	 * Actualizar tabla ventas
	 */
	@Override
	public int actualizarVentaSql(Long id,VentaEntitie venta){
		
		int retorno = 0;
		try {
			System.out.println("REsibe?:: " +venta);
			retorno = ventaRepositorio.actualizarVenta(id, venta.getSubtotal());
			System.out.println("etorna?:: " +retorno);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return retorno;
	}

	@Override
	public int borrarVentaSql(Long id){
		int retorno = 0;
		retorno = ventaRepositorio.borrar(id);
		return retorno;
	}

	//indicadores

	@Override
	public List<VentaEntitie> buscarPorAnio(int anio) {
		List<VentaEntitie> lista = new ArrayList<>();
		List<Object[]> ver = ventaRepositorio.buscarPorAnio(anio);	
		
        for (int i = 0; i < ver.size(); i++) {
            VentaEntitie obj = new VentaEntitie();
			BigDecimal x = (BigDecimal) ver.get(i)[0];

			BigDecimal a  = (BigDecimal) ver.get(i)[2]; 
  
        // Using floatValue() method 
        float f = a.floatValue(); 
            obj.setId(x.longValue());
            obj.setFecha((Date) ver.get(i)[1]);
			obj.setSubtotal(a.floatValue());
			obj.setImpuestos(((BigDecimal)ver.get(i)[3]).floatValue());
			obj.setTotal(((BigDecimal)ver.get(i)[4]).floatValue());
			obj.setVendedor(((BigDecimal)ver.get(i)[5]).intValue());
			obj.setCliente(((BigDecimal)ver.get(i)[6]).intValue());
			obj.setTipo_clientes(String.valueOf(ver.get(i)[7]));
			obj.setTipo_vendedor(String.valueOf(ver.get(i)[8]));
            lista.add(obj);
        }       
		return lista;
	}

	@Override
	public List<VentasDTO> buscarMasVendidoVendedor() {
		List<VentasDTO> lista = new ArrayList<>();
		List<Object[]> ver = ventaRepositorio.buscarVendedorMasVende();	
		System.out.println("ver: " + ver.get(0)[2].getClass().getSimpleName());
        for (int i = 0; i < ver.size(); i++) {
            VentasDTO obj = new VentasDTO();		         
            obj.setId(((BigDecimal)ver.get(i)[0]).longValue());
            obj.setNombres(String.valueOf(ver.get(i)[1]));
			obj.setTotalVentas(String.valueOf(ver.get(i)[2]));					
            lista.add(obj);
        }       
		return lista;
	}

	@Override
	public List<VentasDTO> buscarPorAnioEspecifico(int anio) {
		List<VentasDTO> lista = new ArrayList<>();
		List<Object[]> ver = ventaRepositorio.buscarVentaAnioEspecifico(anio);	
				
        for (int i = 0; i < ver.size(); i++) {
            VentasDTO obj = new VentasDTO();		         
            obj.setAnio((BigDecimal)ver.get(i)[0]);
            obj.setSubtotal(((BigDecimal)ver.get(i)[1]).floatValue());
			obj.setImpuestos(((BigDecimal)ver.get(i)[2]).floatValue());
			obj.setTotal(((BigDecimal)ver.get(i)[3]).floatValue());			
            lista.add(obj);
        }       
		return lista;
	}

	@Override
	public List<VentasDTO> buscarPromedioVentasCliente() {
		List<VentasDTO> lista = new ArrayList<>();
		List<Object[]> ver = ventaRepositorio.buscarPromedioVentasCliente();	
		System.out.println("ver: " + ver.get(0)[0].getClass().getSimpleName());
		System.out.println("ver: " + ver.get(0)[1].getClass().getSimpleName());
		System.out.println("ver: " + ver.get(0)[2].getClass().getSimpleName());
		
        for (int i = 0; i < ver.size(); i++) {
            VentasDTO obj = new VentasDTO();		         
            obj.setCliente(((BigDecimal)ver.get(i)[0]).intValue());
            obj.setNombres((String)ver.get(i)[1]);
			obj.setTotal(((BigDecimal)ver.get(i)[2]).floatValue());				
            lista.add(obj);
        }       
		return lista;
	}
}

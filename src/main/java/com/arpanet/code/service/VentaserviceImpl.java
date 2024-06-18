package com.arpanet.code.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VentaEntitie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpanet.code.repository.VentaRepositorio;

@Service
public class VentaserviceImpl implements Ventaservice{

	@Autowired
	private VentaRepositorio VentaRepositorio;
	
	@Override
	public List<VentaEntitie> findVentaAll() {
		return VentaRepositorio.findAll();
	}

	@Override
	public VentaEntitie createVenta(VentaEntitie venta) {
		return VentaRepositorio.saveAndFlush(venta);
	}

	@Override
	public VentaEntitie updateVenta(VentaEntitie Venta) {
		return VentaRepositorio.save(Venta);
	}

	@Override
	public void deleteVenta(Long id) {
		VentaRepositorio.deleteById(id);
	}

	@Override
	public Optional<VentaEntitie> getVenta(Long id) {
		return VentaRepositorio.findById(id);
	}

	@Override
	public List<VentaEntitie> listaAll() {
		List<VentaEntitie> lista = new ArrayList<>();
		List<Object[]> ver = VentaRepositorio.buscar();	
		System.out.println("====>>> " + ver.get(1)[1]);	
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
            lista.add(obj);
        }       
		return lista;
	}

	/** inserta ventas nativa */
	@Override
    public int crearVentaSql(VentaEntitie objContrato) {
        int retorno = 0;		
        retorno = VentaRepositorio.guardarVenta(objContrato.getFecha(), objContrato.getSubtotal(), objContrato.getImpuestos(), objContrato.getTotal());
        return retorno;
    }

}

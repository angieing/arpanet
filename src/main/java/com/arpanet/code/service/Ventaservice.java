package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VentaEntitie;

public interface Ventaservice {
	
    public List<VentaEntitie> findVentaAll();
    public List<VentaEntitie> listaAll();
    public VentaEntitie createVenta(VentaEntitie venta);    
    public VentaEntitie updateVenta(VentaEntitie venta);
    public void deleteVenta(Long id);
    public Optional<VentaEntitie> getVenta(Long id);
    public int crearVentaSql(VentaEntitie venta);
    public int actualizarVentaSql(Long id,VentaEntitie venta);
    public int borrarVentaSql(Long id);

}


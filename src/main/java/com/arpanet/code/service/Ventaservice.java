package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VentaEntitie;

public interface Ventaservice {
	
    public List<VentaEntitie> findVentaAll();
    public List<VentaEntitie> listaAll();
    public VentaEntitie createVenta(VentaEntitie Venta);
    public VentaEntitie updateVenta(VentaEntitie Venta);
    public void deleteVenta(Long id);
    public Optional<VentaEntitie> getVenta(Long id);

}


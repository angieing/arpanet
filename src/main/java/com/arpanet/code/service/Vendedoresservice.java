package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VendedorEntitie;

public interface Vendedoresservice {
	    
    public List<VendedorEntitie> listaAll();    
    public Optional<VendedorEntitie> getVenta(Long id);
    public int crearVentaSql(VendedorEntitie venta);
    public int actualizarVentaSql(VendedorEntitie venta);
    public int borrarVentaSql(Long id);

}
package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VendedorEntitie;

public interface Vendedoresservice {
	    
    public List<VendedorEntitie> listaAll();    
    public Optional<VendedorEntitie> getVendedores(Long id);
    public int crearVendedoresSql(VendedorEntitie venta);
    public int actualizarVendedoresSql(VendedorEntitie venta);
    public int borrarVendedoresSql(Long id);

}
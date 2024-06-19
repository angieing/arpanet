package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.ClienteEntitie;

public interface Clienteservice {
	    
    public List<ClienteEntitie> listaAll();    
    public Optional<ClienteEntitie> getVenta(Long id);
    public int crearVentaSql(ClienteEntitie venta);
    public int actualizarVentaSql(ClienteEntitie venta);
    public int borrarVentaSql(Long id);

}


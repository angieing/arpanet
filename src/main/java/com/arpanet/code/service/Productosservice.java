package com.arpanet.code.service;

import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.ProductosEntitie;

public interface Productosservice {
	    
    public List<ProductosEntitie> listaAll();    
    public Optional<ProductosEntitie> buscarPorId(Long id);
    public int crearSql(ProductosEntitie venta);
    public int actualizarSql(ProductosEntitie venta);
    public int borrarSql(Long id);

}


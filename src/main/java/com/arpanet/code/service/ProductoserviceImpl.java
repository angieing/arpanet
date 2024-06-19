package com.arpanet.code.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpanet.code.model.ProductosEntitie;
import com.arpanet.code.repository.ProductosRepository;

@Service
public class ProductoserviceImpl implements Productosservice{

	@Autowired
	private ProductosRepository productosRepository;
	
	
	@Override
	public List<ProductosEntitie> listaAll() {
		List<ProductosEntitie> lista = new ArrayList<>();
		List<Object[]> prod = productosRepository.buscar();	
		
        for (int i = 0; i < prod.size(); i++) {
            ProductosEntitie obj = new ProductosEntitie();			
			obj.setId(((BigDecimal) prod.get(i)[0]).longValue());
			obj.setNombre(String.valueOf(prod.get(i)[1]));
			obj.setPorcentaje_impuesto(((BigDecimal)(prod.get(i)[2])).floatValue());
					
            lista.add(obj);
        }       
		return lista;
	}

    @Override
	public Optional<ProductosEntitie> buscarPorId(Long id) {
		return productosRepository.findById(id);
	}

	/** inserta nativa */
	@Override
    public int crearSql(ProductosEntitie obj) {
        int retorno = 0;		
        retorno = productosRepository.guardar(obj.getNombre(), obj.getPorcentaje_impuesto());
        return retorno;
    }

	/**
	 * Actualizar tabla 
	 */
	@Override
	public int actualizarSql(ProductosEntitie venta){
		int retorno = 0;
		try {
			retorno = productosRepository.actualizar(venta.getId(), venta.getNombre());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return retorno;
	}

	/**
	 * Borrar
	 * @param id
	 * @return
	 */
	@Override
	public int borrarSql(Long id){
		int retorno = 0;
		retorno = productosRepository.borrar(id);
		return retorno;
	}

}

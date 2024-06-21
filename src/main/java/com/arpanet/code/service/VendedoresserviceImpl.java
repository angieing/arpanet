package com.arpanet.code.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.VendedorEntitie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpanet.code.repository.VendedoresRepository;

@Service
public class VendedoresserviceImpl implements Vendedoresservice{

	@Autowired
	private VendedoresRepository VendedoresRepository;
	
	
	@Override
	public List<VendedorEntitie> listaAll() {
		List<VendedorEntitie> lista = new ArrayList<>();
		List<Object[]> Vendedores = VendedoresRepository.buscar();	
		
        for (int i = 0; i < Vendedores.size(); i++) {
            VendedorEntitie obj = new VendedorEntitie();			
			obj.setTipoIdentificacion(String.valueOf(Vendedores.get(i)[0]));
            obj.setId(((BigDecimal) Vendedores.get(i)[1]).longValue());
			obj.setNombres(String.valueOf(Vendedores.get(i)[2]));
			obj.setApellidos(String.valueOf(Vendedores.get(i)[3]));
			obj.setTelefono(String.valueOf(Vendedores.get(i)[4]));
			obj.setDireccion(String.valueOf(Vendedores.get(i)[5]));
			obj.setCorreo(String.valueOf(Vendedores.get(i)[6]));
			
            lista.add(obj);
        }       
		return lista;
	}

    @Override
	public Optional<VendedorEntitie> getVendedores(Long id) {
		return VendedoresRepository.findById(id);
	}

	/** inserta ventas nativa */
	@Override
    public int crearVendedoresSql(VendedorEntitie obj) {
        int retorno = 0;		
        retorno = VendedoresRepository.guardarVendedor(obj.getTipoIdentificacion(), obj.getId(), obj.getNombres(), obj.getApellidos(),obj.getTelefono(),obj.getDireccion(),obj.getCorreo());
        return retorno;
    }

	/**
	 * Actualizar tabla ventas
	 */
	@Override
	public int actualizarVendedoresSql(VendedorEntitie venta){
		int retorno = 0;
		try {
			retorno = VendedoresRepository.actualizarVendedor(venta.getId(), venta.getNombres());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return retorno;
	}

	@Override
	public int borrarVendedoresSql(Long id){
		int retorno = 0;
		retorno = VendedoresRepository.borrar(id);
		return retorno;
	}

}

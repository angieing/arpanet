package com.arpanet.code.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.arpanet.code.model.ClienteEntitie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpanet.code.repository.ClienteRepository;

@Service
public class ClienteserviceImpl implements Clienteservice{

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	public List<ClienteEntitie> listaAll() {
		List<ClienteEntitie> lista = new ArrayList<>();
		List<Object[]> cliente = clienteRepository.buscar();	
		
        for (int i = 0; i < cliente.size(); i++) {
            ClienteEntitie obj = new ClienteEntitie();			
			obj.setTipoIdentificacion(String.valueOf(cliente.get(i)[0]));
            obj.setId(((BigDecimal) cliente.get(i)[1]).longValue());
			obj.setNombres(String.valueOf(cliente.get(i)[2]));
			obj.setApellidos(String.valueOf(cliente.get(i)[3]));
			obj.setTelefono(String.valueOf(cliente.get(i)[4]));
			obj.setDireccion(String.valueOf(cliente.get(i)[5]));
			obj.setCorreo(String.valueOf(cliente.get(i)[6]));
			
            lista.add(obj);
        }       
		return lista;
	}

    @Override
	public Optional<ClienteEntitie> getVenta(Long id) {
		return clienteRepository.findById(id);
	}

	/** inserta ventas nativa */
	@Override
    public int crearVentaSql(ClienteEntitie obj) {
        int retorno = 0;		
        retorno = clienteRepository.guardar(obj.getTipoIdentificacion(), obj.getId(), obj.getNombres(), obj.getApellidos(),obj.getTelefono(),obj.getDireccion(),obj.getCorreo());
        return retorno;
    }

	/**
	 * Actualizar tabla ventas
	 */
	@Override
	public int actualizarVentaSql(ClienteEntitie venta){
		int retorno = 0;
		try {
			retorno = clienteRepository.actualizar(venta.getId(), venta.getNombres());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return retorno;
	}

	@Override
	public int borrarVentaSql(Long id){
		int retorno = 0;
		retorno = clienteRepository.borrar(id);
		return retorno;
	}

}

package com.exemplo.gerenciaclientes.negocio;

import java.util.List;

import com.exemplo.gerenciaclientes.models.ClienteModel;
import com.exemplo.gerenciaclientes.persistencia.ClienteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteNegocio {
    private final ClienteRepository clienteRepository;

    public void criar(ClienteModel cliente) throws Exception{
        if(clienteRepository.findByNome(cliente.getNome()).isPresent()){
            throw new Exception("Já existe cliente cadastrado com mesmo nome!");
        }
        clienteRepository.save(cliente);
    }

	public List<ClienteModel> findAll() {
		return clienteRepository.findAll();
    }

	public void deleteById(Long id) {
        clienteRepository.deleteById(id);
	}

	public ClienteModel findById(Long id) {
		return clienteRepository.findById(id).get();
	}

	public void saveAndFlush(ClienteModel cliente) {
        clienteRepository.saveAndFlush(cliente);
	}
    
    
}
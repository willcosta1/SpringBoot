package com.exemplo.gerenciaclientes.persistencia;

import java.util.Optional;

import com.exemplo.gerenciaclientes.models.ClienteModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{
    public Optional<ClienteModel> findByNome(String nome);
}
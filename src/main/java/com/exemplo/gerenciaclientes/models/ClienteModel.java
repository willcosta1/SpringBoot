package com.exemplo.gerenciaclientes.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClienteModel {

    private Long id;
    private String nome;
    private String cpf;
    
}
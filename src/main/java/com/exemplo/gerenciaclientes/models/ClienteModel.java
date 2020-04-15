package com.exemplo.gerenciaclientes.models;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClienteModel {

    private Long id;
    @Size (min = 3, max = 30, message = "Nome deve possuir entre 3 e 30 caracteres!")
    private String nome;
    @Size (min = 11, max = 11, message = "CPF deve possuir 11 caracteres!")
    private String cpf;
    
}
package com.exemplo.gerenciaclientes.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.exemplo.gerenciaclientes.models.ClienteModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    List<ClienteModel> clientes;

    public ClienteController(){
        clientes = Stream.of(
        ClienteModel.builder().id(1L).nome("João Maria").cpf("11111111111").build(),
        ClienteModel.builder().id(2L).nome("Maria João").cpf("22222222222").build()
        ).collect(Collectors.toList());
    }

    public void getClientes(Model memoria){
        memoria.addAttribute("clientes", this.clientes);
    }

    @GetMapping ("/clientes")
    public String clientes(Model memoria){
        this.getClientes(memoria);
        return "clientes";
    }
}
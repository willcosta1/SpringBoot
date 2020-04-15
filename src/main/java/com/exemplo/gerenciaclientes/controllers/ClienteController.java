package com.exemplo.gerenciaclientes.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.exemplo.gerenciaclientes.models.ClienteModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping ("/clientes/salvar")
    public String salvar(@Valid ClienteModel cliente, BindingResult resul, Model memoria){
        if(resul.hasErrors()){
            resul.getFieldErrors().forEach(erro -> memoria.addAttribute(erro.getField(), erro.getDefaultMessage()));
            memoria.addAttribute("clienteAtual", cliente);
            this.getClientes(memoria);
            return "clientes";
        }
        cliente.setId(new Long(this.clientes.size()+1));
        clientes.add(cliente);
        return "redirect:/clientes";
    }

    @GetMapping ("/clientes/excluir")
    public String excluir(@RequestParam Long id){
        this.clientes.removeIf(cliente -> cliente.getId().equals(id));
        return "redirect:/clientes";
    }

    @GetMapping ("/clientes/prepararAlterar")
    public String prepararAlterar(@RequestParam Long id, Model memoria){
        var cliente = clientes.stream().filter(clienteAtual -> clienteAtual.getId().equals(id)).findAny().get();
        memoria.addAttribute("clienteAtual", cliente);
        this.getClientes(memoria);
        memoria.addAttribute("alterar", true);
        return "clientes";
    }
    
    @PostMapping ("/clientes/alterar")
    public String alterar(ClienteModel clienteNovo){
        var cliente = clientes.stream().filter(clienteAtual -> clienteAtual.getId().equals(clienteNovo.getId())).findAny().get();
        cliente.setNome(clienteNovo.getNome());
        cliente.setCpf(clienteNovo.getCpf());
        return "redirect:/clientes";
    }
}
package com.exemplo.gerenciaclientes.controllers;

import javax.validation.Valid;

import com.exemplo.gerenciaclientes.models.ClienteModel;
import com.exemplo.gerenciaclientes.negocio.ClienteNegocio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@Log
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteNegocio clienteNegocio;

    public void getClientes(Model memoria) {
        memoria.addAttribute("clientes", clienteNegocio.findAll());
    }

    @GetMapping("/clientes")
    public String clientes(Model memoria) {
        this.getClientes(memoria);
        return "clientes";
    }

    @PostMapping("/clientes/salvar")
    public String salvar(@Valid ClienteModel cliente, BindingResult resul, Model memoria) {
        if (resul.hasErrors()) {
            resul.getFieldErrors().forEach(erro -> memoria.addAttribute(erro.getField(), erro.getDefaultMessage()));
            memoria.addAttribute("clienteAtual", cliente);
            this.getClientes(memoria);
            return "clientes";
        }
        try {
            clienteNegocio.criar(cliente);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/clientes";
    }

    @GetMapping ("/clientes/excluir")
    public String excluir(@RequestParam Long id){
        clienteNegocio.deleteById(id);
        return "redirect:/clientes";
    }

    @GetMapping ("/clientes/prepararAlterar")
    public String prepararAlterar(@RequestParam Long id, Model memoria){
        var cliente = clienteNegocio.findById(id);
        memoria.addAttribute("clienteAtual", cliente);
        this.getClientes(memoria);
        memoria.addAttribute("alterar", true);
        return "clientes";
    }
    
    @PostMapping ("/clientes/alterar")
    public String alterar(ClienteModel clienteNovo){
        var cliente = clienteNegocio.findById(clienteNovo.getId());
        cliente.setNome(clienteNovo.getNome());
        cliente.setCpf(clienteNovo.getCpf());
        clienteNegocio.saveAndFlush(cliente);
        return "redirect:/clientes";
    }
}
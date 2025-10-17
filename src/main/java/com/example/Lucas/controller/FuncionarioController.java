package com.example.Lucas.controller;

import com.example.Lucas.entity.Funcionario;
import com.example.Lucas.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }
    @GetMapping("/listar/id")
    public ResponseEntity<Funcionario> listarFuncionario(@PathVariable("id") Integer id){
        return ResponseEntity.ok(funcionarioService.listarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<Funcionario> criarFuncionario(@PathVariable("id") Integer id){
        return ResponseEntity.ok(funcionarioService.listarPorId(id));
    }
}

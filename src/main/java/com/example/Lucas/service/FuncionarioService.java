package com.example.Lucas.service;

import com.example.Lucas.entity.Funcionario;
import com.example.Lucas.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodos() {

        return funcionarioRepository.findAll();
    }

    public Funcionario listarPorId(Integer id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

}

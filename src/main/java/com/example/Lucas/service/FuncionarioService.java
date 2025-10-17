package com.example.Lucas.service;

import com.example.Lucas.dto.request.FuncionarioRequest;
import com.example.Lucas.dto.response.FuncionarioResponse;
import com.example.Lucas.entity.Funcionario;
import com.example.Lucas.repository.FuncionarioRepository;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, SecurityConfig securityConfig) {
        this.funcionarioRepository = funcionarioRepository;
        this.securityConfig = securityConfig;
    }

    private final SecurityConfig securityConfig;

    public List<Funcionario> listarTodos() {

        return funcionarioRepository.findAll();
    }

    public Funcionario listarPorId(Integer id) {return funcionarioRepository.findById(id).orElse(null);
    }

    public FuncionarioResponse criarFuncionario(FuncionarioRequest funcionarioRequest) {


        return null;
    }


}

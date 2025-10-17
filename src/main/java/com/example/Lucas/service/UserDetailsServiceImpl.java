package com.example.Lucas.service;

import com.example.Lucas.config.UserDetailsImpl;
import com.example.Lucas.entity.Funcionario;
import com.example.Lucas.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByMatricula(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new UserDetailsImpl(funcionario);
    }

}
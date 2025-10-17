package com.example.Lucas.repository;

import com.example.Lucas.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {
    List<Funcionario> findByMatricula(String subject);
}

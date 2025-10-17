package com.example.Lucas.repository;

import com.example.Lucas.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {
    Optional<Funcionario> findByMatricula(String subject);

    @Override
    Optional<Funcionario> findById(Integer integer);

}

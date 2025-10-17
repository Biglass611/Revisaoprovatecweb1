package com.example.Lucas.repository;

import com.example.Lucas.entity.FolhaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolhaPagamentoRepositoy extends JpaRepository<FolhaPagamento,Integer>{
}

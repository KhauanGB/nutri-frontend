package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
}

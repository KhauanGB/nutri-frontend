package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

}

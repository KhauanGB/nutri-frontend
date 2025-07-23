package com.ifpr.nutri.dto.plano;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.Refeicao;

import java.time.LocalDate;
import java.util.List;

public record PlanoAlimentarCreateDto(
        Pessoa pessoa,
        List<Refeicao> refeicoes,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
) {}

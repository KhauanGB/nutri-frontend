package com.ifpr.nutri.dto.plano;

import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;

import java.time.LocalDate;
import java.util.List;

public record PlanoAlimentarResponseDto(
        Long id,
        String pessoaCpf,
        List<RefeicaoResponseDto> refeicoes,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
) {}
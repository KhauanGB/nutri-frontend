package com.ifpr.nutri.dto.plano;

import java.time.LocalDate;
import java.util.List;

public record PlanoAlimentarDto(
        String pessoaCpf,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
) {}
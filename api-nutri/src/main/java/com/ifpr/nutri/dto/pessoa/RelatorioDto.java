package com.ifpr.nutri.dto.pessoa;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;

import java.time.LocalDate;
import java.util.List;

public record RelatorioDto(
        String nome,
        LocalDate inicio,
        LocalDate fim,
        TotaisNutricionaisDto totaisNutricionais,
        List<AlimentoResponseDto> alimentosCaloricos
) {
}

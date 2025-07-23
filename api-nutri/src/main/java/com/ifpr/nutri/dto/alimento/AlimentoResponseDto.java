package com.ifpr.nutri.dto.alimento;

public record AlimentoResponseDto(
        Long id,
        String nome,
        Double calorias,
        Double proteinas,
        Double carboidratos,
        Double gorduras,
        String porcao
) {}

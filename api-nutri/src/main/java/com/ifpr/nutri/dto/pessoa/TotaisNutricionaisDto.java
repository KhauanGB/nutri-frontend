package com.ifpr.nutri.dto.pessoa;

public record TotaisNutricionaisDto(
        Double totalCalorias,
        Double totalProteinas,
        Double totalCarboidratos,
        Double totalGorduras,
        Double IMC
) {
}
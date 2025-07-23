package com.ifpr.nutri.dto.alimento;

import com.ifpr.nutri.dao.Alimento;

public record ItemAlimentoDto(
        Alimento alimento,
        Double quantidade
) {}

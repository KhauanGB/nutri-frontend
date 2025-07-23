package com.ifpr.nutri.dto.refeicao;

import com.ifpr.nutri.dao.ItemAlimento;
import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dao.Refeicao;

import java.time.LocalDateTime;
import java.util.List;

public record RefeicaoCreateDto(
        Pessoa pessoa,
        PlanoAlimentar plano,
        LocalDateTime data,
        Refeicao.Tipo tipo,
        List<ItemAlimento> itens
) {
}

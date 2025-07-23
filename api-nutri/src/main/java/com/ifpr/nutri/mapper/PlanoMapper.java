package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;

import java.time.LocalDate;
import java.util.List;

public class PlanoMapper {
    public static PlanoAlimentarResponseDto toResponseDto(PlanoAlimentar plano) {
        return new PlanoAlimentarResponseDto(
                plano.getId(),
                plano.getPessoa().getCpf(),
                plano.getRefeicoes().stream().map(RefeicaoMapper::toResponseDto).toList(),
                plano.getDataInicio(),
                plano.getDataFim(),
                plano.getObservacoes()
        );
    }

    public static PlanoAlimentar updateFromParams(
        PlanoAlimentar plano,
        List<Refeicao> refeicoes,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
    ) {
        if (dataInicio != null) plano.setDataInicio(dataInicio);
        if (dataFim != null) plano.setDataFim(dataFim);
        if (observacoes != null) plano.setObservacoes(observacoes);
        if (refeicoes != null) {
            plano.getRefeicoes().clear();
            refeicoes.forEach(plano.getRefeicoes()::add);
        }
        return plano;
    }
}

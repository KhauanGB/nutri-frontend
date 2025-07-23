package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dao.ItemAlimento;
import com.ifpr.nutri.dto.alimento.ItemAlimentoCreateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoUpdateDto;

import java.util.List;

public class RefeicaoMapper {

    public static RefeicaoResponseDto toResponseDto(Refeicao refeicao) {
        List<ItemAlimentoCreateDto> itens = refeicao.getItens().stream()
                .map(x -> {
                    return new ItemAlimentoCreateDto(
                            x.getAlimento().getId(),
                            x.getQuantidade());
                }).toList();

        return new RefeicaoResponseDto(
                refeicao.getId(),
                refeicao.getData(),
                refeicao.getTipo().name(),
                itens
        );
    }

    public static Refeicao updateFromDto(Refeicao refeicao, List<ItemAlimento> itensAlimentos, RefeicaoUpdateDto dto) {
        if (dto.data() != null) {
            refeicao.setData(dto.data());
        }
        if (dto.tipo() != null) {
            refeicao.setTipo(Refeicao.Tipo.valueOf(dto.tipo()));
        }
        if (dto.itens() != null) {
            refeicao.getItens().clear();
            itensAlimentos.forEach(x -> refeicao.getItens().add(x));
        }
        return refeicao;
    }
}
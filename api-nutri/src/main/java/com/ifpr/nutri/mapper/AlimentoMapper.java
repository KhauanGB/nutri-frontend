package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;
import com.ifpr.nutri.dao.Alimento.Unidade;  // novo import

public class AlimentoMapper {

    public static AlimentoResponseDto toResponseDto(Alimento alimento) {
        return new AlimentoResponseDto(
                alimento.getId(),
                alimento.getNome(),
                alimento.getCalorias(),
                alimento.getProteinas(),
                alimento.getCarboidratos(),
                alimento.getGorduras(),
                alimento.getPorcao().toString()
        );
    }

    public static Alimento updateFromParams(
        Alimento alimento,
        String nome,
        Double calorias,
        Double proteinas,
        Double carboidratos,
        Double gorduras,
        String porcao
    ) {
        if (nome != null) alimento.setNome(nome);
        if (calorias != null) alimento.setCalorias(calorias);
        if (proteinas != null) alimento.setProteinas(proteinas);
        if (carboidratos != null) alimento.setCarboidratos(carboidratos);
        if (gorduras != null) alimento.setGorduras(gorduras);
        if (porcao != null) alimento.setPorcao(Unidade.valueOf(porcao));
        return alimento;
    }
}
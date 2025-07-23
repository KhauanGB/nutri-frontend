package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.*;
import com.ifpr.nutri.dto.alimento.ItemAlimentoCreateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoUpdateDto;
import com.ifpr.nutri.mapper.RefeicaoMapper;
import com.ifpr.nutri.repository.PlanoAlimentarRepository;
import com.ifpr.nutri.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ifpr.nutri.mapper.RefeicaoMapper.*;

@Service
public class RefeicaoService {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private AlimentoService alimentoService;

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    public RefeicaoResponseDto create(RefeicaoDto dto) {
        PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(dto.planoId()).orElseThrow(() -> new RuntimeException("Não achado"));

        Refeicao refeicao = Refeicao.builder()
                .planoAlimentar(planoAlimentar)
                .data(dto.data())
                .tipo(Refeicao.Tipo.valueOf(dto.tipo()))
                .build();

        List<ItemAlimento> itensAlimentos = dto.itens().stream().map(
                x -> createItemAlimento(x, refeicao)
        ).toList();

        refeicao.setItens(itensAlimentos);

        return toResponseDto(refeicaoRepository.save(refeicao));
    }

    public Refeicao findById(Long id) {
        return refeicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Refeição não encontrada"));
    }

    public List<RefeicaoResponseDto> findAll() {
        return refeicaoRepository.findAll().stream().map(RefeicaoMapper::toResponseDto).toList();
    }

    public void update(Long id, RefeicaoUpdateDto dto) {
        Refeicao refeicao = findById(id);
        List<ItemAlimento> itensAlimentos = dto.itens().stream().map(
                x -> createItemAlimento(x, refeicao)
        ).toList();
        refeicaoRepository.save(updateFromDto(refeicao, itensAlimentos, dto));
    }

    public void delete(Long id) {
        refeicaoRepository.deleteById(id);
    }

    private ItemAlimento createItemAlimento(ItemAlimentoCreateDto dto, Refeicao refeicao) {
        Alimento alimento = alimentoService.findById(dto.alimentoId());
        ItemAlimento item = ItemAlimento.builder()
                .alimento(alimento)
                .quantidade(dto.quantidade())
                .build();
        item.setRefeicao(refeicao);
        return item;
    }
}

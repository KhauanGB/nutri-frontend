package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dto.plano.PlanoAlimentarDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.mapper.PlanoMapper;
import com.ifpr.nutri.repository.PlanoAlimentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoAlimentarService {

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private RefeicaoService refeicaoService;

    public PlanoAlimentarResponseDto create(PlanoAlimentarDto dto) {
        Pessoa pessoa = pessoaService.findByCpf(dto.pessoaCpf());

        PlanoAlimentar plano = PlanoAlimentar.builder()
                .pessoa(pessoa)
                .refeicoes(new ArrayList<>())
                .dataInicio(dto.dataInicio())
                .dataFim(dto.dataFim())
                .observacoes(dto.observacoes())
                .build();

        return PlanoMapper.toResponseDto(planoAlimentarRepository.save(plano));
    }

    public List<PlanoAlimentarResponseDto> findAll() {
        return planoAlimentarRepository.findAll().stream().map(PlanoMapper::toResponseDto).toList();
    }

    public void delete(Long id) {
        planoAlimentarRepository.deleteById(id);
    }

    public void update(
        Long id,
        List<Long> refeicoesIds,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
    ) {
        PlanoAlimentar plano = planoAlimentarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        List<Refeicao> refeicoes = null;
        if (refeicoesIds != null) {
            refeicoes = refeicoesIds.stream()
                .map(refeicaoService::findById)
                .collect(Collectors.toList());
        }

        PlanoAlimentar updated = PlanoMapper.updateFromParams(
            plano, refeicoes, dataInicio, dataFim, observacoes
        );
        planoAlimentarRepository.save(updated);
    }
}

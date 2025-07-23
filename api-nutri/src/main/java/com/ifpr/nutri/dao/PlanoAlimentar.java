package com.ifpr.nutri.dao;

import com.ifpr.nutri.dto.plano.PlanoAlimentarCreateDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.mapper.RefeicaoMapper;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PlanoAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @OneToMany(mappedBy = "planoAlimentar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Refeicao> refeicoes;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(length = 255)
    private String observacoes;

    public PlanoAlimentar() {
    }

    public PlanoAlimentar(Long id, Pessoa pessoa, List<Refeicao> refeicoes, LocalDate dataInicio, LocalDate dataFim, String observacoes) {
        this.id = id;
        this.pessoa = pessoa;
        this.refeicoes = refeicoes;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
    }

    public PlanoAlimentarResponseDto create(PlanoAlimentarCreateDto dto) {
        return new PlanoAlimentarResponseDto(null, dto.pessoa().getCpf(), dto.refeicoes().stream().map(RefeicaoMapper::toResponseDto).toList() , dto.dataInicio(), dto.dataFim(), dto.observacoes());
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Pessoa pessoa;
        private List<Refeicao> refeicoes;
        private LocalDate dataInicio;
        private LocalDate dataFim;
        private String observacoes;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder pessoa(Pessoa pessoa) {
            this.pessoa = pessoa;
            return this;
        }

        public Builder refeicoes(List<Refeicao> refeicoes) {
            this.refeicoes = refeicoes;
            return this;
        }

        public Builder dataInicio(LocalDate dataInicio) {
            this.dataInicio = dataInicio;
            return this;
        }

        public Builder dataFim(LocalDate dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public Builder observacoes(String observacoes) {
            this.observacoes = observacoes;
            return this;
        }

        public PlanoAlimentar build() {
            return new PlanoAlimentar(
                    this.id,
                    this.pessoa,
                    this.refeicoes,
                    this.dataInicio,
                    this.dataFim,
                    this.observacoes
            );
        }
    }
}
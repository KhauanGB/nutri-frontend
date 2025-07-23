package com.ifpr.nutri.dao;

import com.ifpr.nutri.dto.alimento.ItemAlimentoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class ItemAlimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimento alimento;
    
    @ManyToOne
    @JoinColumn(name = "refeicao_id", nullable = false)
    private Refeicao refeicao;
    
    private Double quantidade;

    public ItemAlimento() {
    }

    public ItemAlimento(Long id, Alimento alimento, Refeicao refeicao, Double quantidade) {
        this.id = id;
        this.alimento = alimento;
        this.refeicao = refeicao;
        this.quantidade = quantidade;
    }

    public ItemAlimento(Alimento alimento, Double quantidade) {
        this.alimento = alimento;
        this.quantidade = quantidade;
    }

    public ItemAlimento create(ItemAlimentoDto dto) {
        return new ItemAlimento(dto.alimento(), dto.quantidade());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Alimento alimento;
        private Refeicao refeicao;
        private Double quantidade;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder alimento(Alimento alimento) {
            this.alimento = alimento;
            return this;
        }

        public Builder refeicao(Refeicao refeicao) {
            this.refeicao = refeicao;
            return this;
        }

        public Builder quantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ItemAlimento build() {
            return new ItemAlimento(
                    this.id,
                    this.alimento,
                    this.refeicao,
                    this.quantidade
            );
        }
    }
}
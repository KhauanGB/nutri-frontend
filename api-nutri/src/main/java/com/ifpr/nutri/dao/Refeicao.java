package com.ifpr.nutri.dao;

import com.ifpr.nutri.dto.alimento.ItemAlimentoCreateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoCreateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemAlimento> itens;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoAlimentar planoAlimentar;

    @Column(nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    public enum Tipo {
        CAFE_DA_MANHA,
        ALMOCO,
        JANTAR,
        LANCHE
    }

    public Refeicao() {
    }

    public Refeicao(Long id, List<ItemAlimento> itens, PlanoAlimentar planoAlimentar, LocalDateTime data, Tipo tipo) {
        this.id = id;
        this.itens = itens;
        this.planoAlimentar = planoAlimentar;
        this.data = data;
        this.tipo = tipo;
    }

    public RefeicaoResponseDto create(RefeicaoCreateDto dto) {
        return new RefeicaoResponseDto(null, dto.data(), dto.tipo().toString(), dto.itens().stream().map(x -> new ItemAlimentoCreateDto(x.getId(), x.getQuantidade())).toList());
    }

    public PlanoAlimentar getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(PlanoAlimentar planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemAlimento> getItens() {
        return itens;
    }

    public void setItens(List<ItemAlimento> itens) {
        this.itens = itens;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private List<ItemAlimento> itens;
        private PlanoAlimentar planoAlimentar;
        private LocalDateTime data;
        private Tipo tipo;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder itens(List<ItemAlimento> itens) {
            this.itens = itens;
            return this;
        }

        public Builder planoAlimentar(PlanoAlimentar planoAlimentar) {
            this.planoAlimentar = planoAlimentar;
            return this;
        }

        public Builder data(LocalDateTime data) {
            this.data = data;
            return this;
        }

        public Builder tipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Refeicao build() {
            return new Refeicao(
                    this.id,
                    this.itens,
                    this.planoAlimentar,
                    this.data,
                    this.tipo
            );
        }
    }
}
package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column
    private Integer idade;

    @Column
    private Double peso;

    @Column
    private Double altura;

    @ElementCollection
    @CollectionTable(
            name = "pessoa_objetivos",
            joinColumns = @JoinColumn(name = "pessoa_id")
    )
    @Column(length = 100)
    private List<String> objetivos;

    @ManyToMany
    @JoinTable(
            name = "pessoa_restricoes",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "alimento_id")
    )
    private List<Alimento> restricoesAlimentares;

    @OneToMany(mappedBy = "pessoa")
    private List<PlanoAlimentar> planosAlimentares;

    public Pessoa() {
    }

    public Pessoa(Long id, String username, String nome, String cpf, String senha, Integer idade, Double peso, Double altura, List<String> objetivos, List<Alimento> restricoesAlimentares, List<PlanoAlimentar> planosAlimentares) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivos = objetivos;
        this.restricoesAlimentares = restricoesAlimentares;
        this.planosAlimentares = planosAlimentares;
    }

    public void create(String username, String nome, String cpf, String senha, Integer idade, Double peso, Double altura, List<String> objetivos, List<Alimento> restricoesAlimentares) {
        new Pessoa(null, username, nome, cpf, senha, idade, peso, altura, objetivos, restricoesAlimentares, null);
    }

    public List<PlanoAlimentar> getPlanosAlimentares() {
        return planosAlimentares;
    }

    public void setPlanosAlimentares(List<PlanoAlimentar> planosAlimentares) {
        this.planosAlimentares = planosAlimentares;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public List<String> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<String> objetivos) {
        this.objetivos = objetivos;
    }

    public List<Alimento> getRestricoesAlimentares() {
        return restricoesAlimentares;
    }

    public void setRestricoesAlimentares(List<Alimento> restricoesAlimentares) {
        this.restricoesAlimentares = restricoesAlimentares;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;
        private String nome;
        private String cpf;
        private String senha;
        private Integer idade;
        private Double peso;
        private Double altura;
        private List<String> objetivos;
        private List<Alimento> restricoesAlimentares;
        private List<PlanoAlimentar> planosAlimentares;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder idade(Integer idade) {
            this.idade = idade;
            return this;
        }

        public Builder peso(Double peso) {
            this.peso = peso;
            return this;
        }

        public Builder altura(Double altura) {
            this.altura = altura;
            return this;
        }

        public Builder objetivos(List<String> objetivos) {
            this.objetivos = objetivos;
            return this;
        }

        public Builder restricoesAlimentares(List<Alimento> restricoesAlimentares) {
            this.restricoesAlimentares = restricoesAlimentares;
            return this;
        }

        public Builder planosAlimentares(List<PlanoAlimentar> planosAlimentares) {
            this.planosAlimentares = planosAlimentares;
            return this;
        }

        public Pessoa build() {
            return new Pessoa(
                    this.id,
                    this.username,
                    this.nome,
                    this.cpf,
                    this.senha,
                    this.idade,
                    this.peso,
                    this.altura,
                    this.objetivos,
                    this.restricoesAlimentares,
                    this.planosAlimentares
            );
        }
    }
}
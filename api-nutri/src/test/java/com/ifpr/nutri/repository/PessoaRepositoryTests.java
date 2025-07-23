package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PessoaRepositoryTests {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveSalvarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setUsername("joao123");
        pessoa.setSenha("123456"); // sem criptografia por enquanto
        pessoa.setCpf("12345678900");

        Pessoa salva = pessoaRepository.save(pessoa);

        assertThat(salva.getId()).isNotNull();
        assertThat(salva.getNome()).isEqualTo("João");
    }

    @Test
    void deveBuscarPorCpf() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Maria");
        pessoa.setUsername("maria123");
        pessoa.setSenha("123456");
        pessoa.setCpf("11122233344");

        pessoaRepository.save(pessoa);

        Pessoa encontrada = pessoaRepository.findByCpf("11122233344").orElse(null);

        assertThat(encontrada).isNotNull();
        assertThat(encontrada.getNome()).isEqualTo("Maria");
    }


    @Test
    void deveAtualizarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana");
        pessoa.setUsername("ana123");
        pessoa.setSenha("123");
        pessoa.setCpf("99988877766");

        pessoa = pessoaRepository.save(pessoa);
        pessoa.setNome("Ana Clara");
        Pessoa atualizada = pessoaRepository.save(pessoa);

        assertThat(atualizada.getNome()).isEqualTo("Ana Clara");
    }

    @Test
    void deveExcluirPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos");
        pessoa.setUsername("carlos123");
        pessoa.setSenha("123");
        pessoa.setCpf("33322211100");

        pessoa = pessoaRepository.save(pessoa);
        pessoaRepository.delete(pessoa);

        Pessoa excluida = pessoaRepository.findByCpf("33322211100").orElse(null);
        assertThat(excluida).isNull();

    }
}

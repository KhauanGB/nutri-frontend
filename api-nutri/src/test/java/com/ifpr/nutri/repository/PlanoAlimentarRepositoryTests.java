package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.PlanoAlimentar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PlanoAlimentarRepositoryTests {

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveSalvarPlanoAlimentar() {
        Pessoa pessoa = criarPessoa();
        PlanoAlimentar plano = new PlanoAlimentar();
        plano.setPessoa(pessoa);
        plano.setDataInicio(LocalDate.now());
        plano.setDataFim(LocalDate.now().plusDays(7));
        plano.setObservacoes("Plano semanal para perda de peso");

        PlanoAlimentar salvo = planoAlimentarRepository.save(plano);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getPessoa()).isEqualTo(pessoa);
    }

    @Test
    void deveAtualizarObservacoesPlano() {
        Pessoa pessoa = criarPessoa();
        PlanoAlimentar plano = new PlanoAlimentar();
        plano.setPessoa(pessoa);
        plano.setDataInicio(LocalDate.now());
        plano.setDataFim(LocalDate.now().plusDays(7));
        plano.setObservacoes("Plano inicial");

        plano = planoAlimentarRepository.save(plano);
        plano.setObservacoes("Plano revisado com nutricionista");

        PlanoAlimentar atualizado = planoAlimentarRepository.save(plano);
        assertThat(atualizado.getObservacoes()).isEqualTo("Plano revisado com nutricionista");
    }

    @Test
    void deveExcluirPlanoAlimentar() {
        Pessoa pessoa = criarPessoa();
        PlanoAlimentar plano = new PlanoAlimentar();
        plano.setPessoa(pessoa);
        plano.setDataInicio(LocalDate.now());
        plano.setDataFim(LocalDate.now().plusDays(7));
        plano.setObservacoes("Plano de teste");

        plano = planoAlimentarRepository.save(plano);
        planoAlimentarRepository.delete(plano);

        boolean existe = planoAlimentarRepository.findById(plano.getId()).isPresent();
        assertThat(existe).isFalse();
    }

    private Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Plano Teste");
        pessoa.setUsername("planoteste");
        pessoa.setSenha("123456");
        pessoa.setCpf("12312312399");
        return pessoaRepository.save(pessoa);
    }
}

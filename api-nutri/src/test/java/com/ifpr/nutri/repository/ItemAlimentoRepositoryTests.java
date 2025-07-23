package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.*;
import com.ifpr.nutri.dao.Refeicao.Tipo;
import com.ifpr.nutri.dao.Alimento.Unidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ItemAlimentoRepositoryTests {

    @Autowired
    private ItemAlimentoRepository itemAlimentoRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveSalvarItemAlimento() {
        Pessoa pessoa = criarPessoa();
        Alimento alimento = criarAlimento();
        Refeicao refeicao = criarRefeicao(pessoa);

        ItemAlimento item = new ItemAlimento();
        item.setAlimento(alimento);
        item.setRefeicao(refeicao);
        item.setQuantidade(2.0);

        ItemAlimento salvo = itemAlimentoRepository.save(item);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getQuantidade()).isEqualTo(2.0);
    }

    @Test
    void deveExcluirItemAlimento() {
        Pessoa pessoa = criarPessoa();
        Alimento alimento = criarAlimento();
        Refeicao refeicao = criarRefeicao(pessoa);

        ItemAlimento item = new ItemAlimento();
        item.setAlimento(alimento);
        item.setRefeicao(refeicao);
        item.setQuantidade(1.0);

        item = itemAlimentoRepository.save(item);
        itemAlimentoRepository.delete(item);

        boolean existe = itemAlimentoRepository.findById(item.getId()).isPresent();
        assertThat(existe).isFalse();
    }

    private Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste Item");
        pessoa.setUsername("testeitem");
        pessoa.setSenha("123456");
        pessoa.setCpf("11122233344");
        return pessoaRepository.save(pessoa);
    }

    private Alimento criarAlimento() {
        Alimento a = new Alimento();
        a.setNome("Ovo");
        a.setCalorias(70.0);
        a.setProteinas(6.0);
        a.setCarboidratos(1.0);
        a.setGorduras(5.0);
        a.setPorcao(Unidade.UNIDADE);
        return alimentoRepository.save(a);
    }

    private Refeicao criarRefeicao(Pessoa pessoa) {
        Refeicao r = new Refeicao();
        r.setData(LocalDateTime.now());
        r.setTipo(Tipo.CAFE_DA_MANHA);
        return refeicaoRepository.save(r);
    }
}

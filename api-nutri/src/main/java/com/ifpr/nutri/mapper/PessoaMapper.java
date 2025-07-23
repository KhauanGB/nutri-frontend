package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dao.Pessoa;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class PessoaMapper {

    public static Pessoa updateFromParams(
            Pessoa pessoa,
            String username,
            String nome,
            String cpf,
            String senha,
            Integer idade,
            Double peso,
            Double altura,
            List<String> objetivos,
            List<Alimento> restricoesAlimentares
    ) {
        if (username != null) pessoa.setUsername(username);
        if (nome != null) pessoa.setNome(nome);
        if (cpf != null) pessoa.setCpf(cpf);
        if (senha != null) pessoa.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
        if (idade != null) pessoa.setIdade(idade);
        if (peso != null) pessoa.setPeso(peso);
        if (altura != null) pessoa.setAltura(altura);
        if (objetivos != null) pessoa.setObjetivos(objetivos);
        if (restricoesAlimentares != null) pessoa.setRestricoesAlimentares(restricoesAlimentares);
        return pessoa;
    }
}

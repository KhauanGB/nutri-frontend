import React, { useState } from 'react';
import axios from 'axios';

const Cadastro = () => {
    const [form, setForm] = useState({
        username: '', nome: '', cpf: '', idade: '', altura: '', peso: '', senha: '', confirmarSenha: ''
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleCadastro = async (e) => {
        e.preventDefault();
        if (form.senha !== form.confirmarSenha) return alert('As senhas não coincidem.');

        try {
            const { confirmarSenha, ...dados } = form;
            await axios.post('http://localhost:8080/pessoas', dados);
            alert('Cadastro realizado com sucesso!');
        } catch (error) {
            alert('Erro ao cadastrar usuário');
        }
    };

    return (
        <div className="container">
            <h2>CADASTRO</h2>
            <form onSubmit={handleCadastro}>
                <input type="text" name="username" placeholder="Nome de usuário" onChange={handleChange} required />
                <input type="text" name="nome" placeholder="Nome" onChange={handleChange} required />
                <input type="text" name="cpf" placeholder="CPF" onChange={handleChange} required />
                <input type="number" name="idade" placeholder="Idade" onChange={handleChange} required />
                <input type="text" name="altura" placeholder="Altura" onChange={handleChange} required />
                <input type="text" name="peso" placeholder="Peso" onChange={handleChange} required />
                <input type="password" name="senha" placeholder="Senha" onChange={handleChange} required />
                <input type="password" name="confirmarSenha" placeholder="Confirmar senha" onChange={handleChange} required />
                <button type="submit">Cadastrar</button>
            </form>
        </div>
    );
};

export default Cadastro;

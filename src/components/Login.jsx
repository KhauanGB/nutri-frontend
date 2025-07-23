import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
    const [cpf, setCpf] = useState('');
    const [senha, setSenha] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.patch('http://localhost:8080/pessoas/login', { cpf, senha });
            if (response.data) {
                alert('Login realizado com sucesso!');
                // redirecionar ou guardar token se necessário
            } else {
                alert('CPF ou senha inválidos.');
            }
        } catch (error) {
            alert('Erro ao realizar login');
        }
    };

    return (
        <div className="container">
            <h2>LOGIN</h2>
            <form onSubmit={handleLogin}>
                <input type="text" placeholder="CPF" value={cpf} onChange={(e) => setCpf(e.target.value)} required />
                <input type="password" placeholder="Senha" value={senha} onChange={(e) => setSenha(e.target.value)} required />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;

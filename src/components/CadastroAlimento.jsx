import React, { useState } from 'react';
import axios from 'axios';

const CadastroAlimento = () => {
    const [nome, setNome] = useState('');
    const [quantidade, setQuantidade] = useState('');
    const [calorias, setCalorias] = useState('');
    const [mensagem, setMensagem] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/alimentos', {
                nome,
                quantidade,
                calorias
            });
            setMensagem('Alimento cadastrado com sucesso!');
            setNome('');
            setQuantidade('');
            setCalorias('');
        } catch (error) {
            setMensagem('Erro ao cadastrar alimento.');
        }
    };

    return (
        <div style={estilos.container}>
            <h2>Cadastro de Alimento</h2>
            <form onSubmit={handleSubmit} style={estilos.form}>
                <label>Nome do Alimento:</label>
                <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />

                <label>Quantidade (g):</label>
                <input type="number" value={quantidade} onChange={(e) => setQuantidade(e.target.value)} required />

                <label>Calorias:</label>
                <input type="number" value={calorias} onChange={(e) => setCalorias(e.target.value)} required />

                <button type="submit">Cadastrar</button>
            </form>
            {mensagem && <p>{mensagem}</p>}
        </div>
    );
};

const estilos = {
    container: {
        maxWidth: '500px',
        margin: '0 auto',
        padding: '2rem'
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        gap: '1rem'
    }
};

export default CadastroAlimento;

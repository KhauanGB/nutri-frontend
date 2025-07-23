import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ListaAlimentos = () => {
    const [alimentos, setAlimentos] = useState([]);
    const [erro, setErro] = useState('');

    useEffect(() => {
        const buscarAlimentos = async () => {
            try {
                const resposta = await axios.get('http://localhost:8080/api/alimentos');
                setAlimentos(resposta.data);
            } catch (error) {
                setErro('Erro ao buscar alimentos.');
            }
        };

        buscarAlimentos();
    }, []);

    return (
        <div style={estilos.container}>
            <h2>Alimentos Cadastrados</h2>
            {erro && <p>{erro}</p>}
            {alimentos.length === 0 ? (
                <p>Nenhum alimento encontrado.</p>
            ) : (
                <table style={estilos.tabela}>
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade (g)</th>
                        <th>Calorias</th>
                    </tr>
                    </thead>
                    <tbody>
                    {alimentos.map((alimento) => (
                        <tr key={alimento.id}>
                            <td>{alimento.nome}</td>
                            <td>{alimento.quantidade}</td>
                            <td>{alimento.calorias}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

const estilos = {
    container: {
        maxWidth: '800px',
        margin: '0 auto',
        padding: '2rem'
    },
    tabela: {
        width: '100%',
        borderCollapse: 'collapse'
    },
    th: {
        borderBottom: '1px solid #ccc',
        padding: '8px'
    },
    td: {
        padding: '8px',
        borderBottom: '1px solid #eee'
    }
};

export default ListaAlimentos;

import React, { useState, useEffect } from 'react';
import { cadastrarRefeicao, buscarAlimentos, buscarRefeicoes } from '../services/refeicaoService';

function RefeicaoCadastro() {
    const [nome, setNome] = useState('');
    const [data, setData] = useState('');
    const [alimentoId, setAlimentoId] = useState('');
    const [quantidade, setQuantidade] = useState('');
    const [alimentos, setAlimentos] = useState([]);
    const [refeicoes, setRefeicoes] = useState([]);
    const [mensagem, setMensagem] = useState('');

    useEffect(() => {
        async function carregarDados() {
            setAlimentos(await buscarAlimentos());
            setRefeicoes(await buscarRefeicoes());
        }
        carregarDados();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const novaRefeicao = {
            nome,
            data,
            itens: [{ alimentoId, quantidade }]
        };

        const sucesso = await cadastrarRefeicao(novaRefeicao);
        if (sucesso) {
            setMensagem('Refeição cadastrada com sucesso!');
            setNome('');
            setData('');
            setAlimentoId('');
            setQuantidade('');
            setRefeicoes(await buscarRefeicoes());
        } else {
            setMensagem('Erro ao cadastrar refeição.');
        }
    };

    return (
        <div className="max-w-2xl mx-auto p-4 bg-white rounded shadow">
            <h2 className="text-xl font-bold mb-4">Cadastrar Refeição</h2>
            {mensagem && <p className="mb-4">{mensagem}</p>}

            <form onSubmit={handleSubmit} className="space-y-3">
                <input
                    type="text"
                    placeholder="Nome da refeição"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                />

                <input
                    type="datetime-local"
                    value={data}
                    onChange={(e) => setData(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                />

                <select
                    value={alimentoId}
                    onChange={(e) => setAlimentoId(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                >
                    <option value="">Selecione o alimento</option>
                    {alimentos.map((a) => (
                        <option key={a.id} value={a.id}>
                            {a.nome}
                        </option>
                    ))}
                </select>

                <input
                    type="number"
                    placeholder="Quantidade (g)"
                    value={quantidade}
                    onChange={(e) => setQuantidade(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                />

                <button
                    type="submit"
                    className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
                >
                    Cadastrar
                </button>
            </form>

            <h3 className="text-lg font-semibold mt-6">Refeições Registradas</h3>
            <ul className="mt-2 space-y-1">
                {refeicoes.map((r) => (
                    <li key={r.id} className="border p-2 rounded">
                        <strong>{r.nome}</strong> - {new Date(r.data).toLocaleString()}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default RefeicaoCadastro;

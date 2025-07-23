import React, { useState, useEffect } from 'react';
import { cadastrarPlano, buscarPacientes, buscarRefeicoes } from '../services/planoService';

function PlanoCadastro() {
    const [nome, setNome] = useState('');
    const [dataInicio, setDataInicio] = useState('');
    const [dataFim, setDataFim] = useState('');
    const [pacienteId, setPacienteId] = useState('');
    const [refeicoesIds, setRefeicoesIds] = useState([]);
    const [pacientes, setPacientes] = useState([]);
    const [refeicoes, setRefeicoes] = useState([]);
    const [mensagem, setMensagem] = useState('');

    useEffect(() => {
        async function carregarDados() {
            const pacientesAPI = await buscarPacientes();
            const refeicoesAPI = await buscarRefeicoes();
            setPacientes(pacientesAPI);
            setRefeicoes(refeicoesAPI);
        }
        carregarDados();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const plano = { nome, dataInicio, dataFim, pacienteId, refeicoesIds };
        const sucesso = await cadastrarPlano(plano);
        if (sucesso) {
            setMensagem('Plano cadastrado com sucesso!');
            setNome('');
            setDataInicio('');
            setDataFim('');
            setPacienteId('');
            setRefeicoesIds([]);
        } else {
            setMensagem('Erro ao cadastrar plano.');
        }
    };

    const handleSelecionarRefeicao = (id) => {
        if (refeicoesIds.includes(id)) {
            setRefeicoesIds(refeicoesIds.filter(rid => rid !== id));
        } else {
            setRefeicoesIds([...refeicoesIds, id]);
        }
    };

    return (
        <div className="max-w-xl mx-auto p-4 bg-white rounded shadow">
            <h2 className="text-xl font-bold mb-4">Cadastrar Plano Alimentar</h2>
            {mensagem && <p className="mb-4">{mensagem}</p>}
            <form onSubmit={handleSubmit} className="space-y-3">
                <input
                    type="text"
                    placeholder="Nome do Plano"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    className="w-full border rounded p-2"
                    required
                />

                <input
                    type="date"
                    value={dataInicio}
                    onChange={(e) => setDataInicio(e.target.value)}
                    className="w-full border rounded p-2"
                    required
                />

                <input
                    type="date"
                    value={dataFim}
                    onChange={(e) => setDataFim(e.target.value)}
                    className="w-full border rounded p-2"
                    required
                />

                <select
                    value={pacienteId}
                    onChange={(e) => setPacienteId(e.target.value)}
                    className="w-full border rounded p-2"
                    required
                >
                    <option value="">Selecione o Paciente</option>
                    {pacientes.map((p) => (
                        <option key={p.id} value={p.id}>
                            {p.nome}
                        </option>
                    ))}
                </select>

                <div>
                    <p className="font-medium mb-1">Refeições</p>
                    {refeicoes.map((r) => (
                        <label key={r.id} className="block">
                            <input
                                type="checkbox"
                                checked={refeicoesIds.includes(r.id)}
                                onChange={() => handleSelecionarRefeicao(r.id)}
                            />{' '}
                            {r.nome}
                        </label>
                    ))}
                </div>

                <button
                    type="submit"
                    className="w-full bg-green-600 text-white p-2 rounded hover:bg-green-700"
                >
                    Cadastrar
                </button>
            </form>
        </div>
    );
}

export default PlanoCadastro;

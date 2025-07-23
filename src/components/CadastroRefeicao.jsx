import React, { useEffect, useState } from 'react';
import { criarRefeicao } from '../services/refeicaoService';
import { listarAlimentos } from '../services/alimentoService';

const CadastroRefeicao = () => {
    const [nome, setNome] = useState('');
    const [alimentosDisponiveis, setAlimentosDisponiveis] = useState([]);
    const [itens, setItens] = useState([]);
    const [mensagem, setMensagem] = useState('');

    useEffect(() => {
        listarAlimentos()
            .then((res) => setAlimentosDisponiveis(res.data))
            .catch(() => setMensagem('Erro ao carregar alimentos.'));
    }, []);

    const adicionarItem = () => {
        setItens([...itens, { alimentoId: '', quantidade: '' }]);
    };

    const handleChangeItem = (index, campo, valor) => {
        const novosItens = [...itens];
        novosItens[index][campo] = valor;
        setItens(novosItens);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const novaRefeicao = {
            nome,
            itensDeRefeicao: itens.map((item) => ({
                alimentoId: parseInt(item.alimentoId),
                quantidade: parseFloat(item.quantidade)
            }))
        };

        try {
            await criarRefeicao(novaRefeicao);
            setMensagem('Refeição cadastrada com sucesso!');
            setNome('');
            setItens([]);
        } catch (err) {
            setMensagem('Erro ao cadastrar refeição.');
        }
    };

    return (
        <div style={estilos.container}>
            <h2>Cadastro de Refeição</h2>
            {mensagem && <p>{mensagem}</p>}
            <form onSubmit={handleSubmit}>
                <label>Nome da Refeição:</label>
                <input
                    type="text"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    required
                />

                <h4>Alimentos</h4>
                {itens.map((item, index) => (
                    <div key={index} style={estilos.item}>
                        <select
                            value={item.alimentoId}
                            onChange={(e) =>
                                handleChangeItem(index, 'alimentoId', e.target.value)
                            }
                            required
                        >
                            <option value="">Selecione um alimento</option>
                            {alimentosDisponiveis.map((alimento) => (
                                <option key={alimento.id} value={alimento.id}>
                                    {alimento.nome}
                                </option>
                            ))}
                        </select>

                        <input
                            type="number"
                            placeholder="Quantidade (g)"
                            value={item.quantidade}
                            onChange={(e) =>
                                handleChangeItem(index, 'quantidade', e.target.value)
                            }
                            required
                        />
                    </div>
                ))}

                <button type="button" onClick={adicionarItem}>
                    + Adicionar Alimento
                </button>

                <br />
                <button type="submit">Salvar Refeição</button>
            </form>
        </div>
    );
};

const estilos = {
    container: {
        maxWidth: '600px',
        margin: '0 auto',
        padding: '2rem'
    },
    item: {
        display: 'flex',
        gap: '1rem',
        marginBottom: '1rem'
    }
};

export default CadastroRefeicao;

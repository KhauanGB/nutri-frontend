import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PlanoAlimentarLista = () => {
    const [planos, setPlanos] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/planos')
            .then(response => {
                setPlanos(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar planos:', error);
            });
    }, []);

    return (
        <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-lg">
            <h1 className="text-2xl font-bold mb-6 text-center">Planos Alimentares</h1>

            {planos.length === 0 ? (
                <p className="text-gray-600 text-center">Nenhum plano cadastrado.</p>
            ) : (
                <ul className="space-y-4">
                    {planos.map(plano => (
                        <li key={plano.id} className="border p-4 rounded-md shadow-sm bg-gray-50">
                            <h2 className="text-xl font-semibold">{plano.nome}</h2>
                            <p className="text-sm text-gray-700">{plano.descricao}</p>
                            <p className="text-sm text-gray-500 mt-1">Data: {plano.data}</p>
                            <button
                                className="mt-3 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                                onClick={() => alert(`Detalhes do plano: ${plano.nome}`)}
                            >
                                Ver Detalhes
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default PlanoAlimentarLista;

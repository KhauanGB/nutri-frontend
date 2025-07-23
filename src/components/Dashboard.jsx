import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Dashboard() {
    const [dados, setDados] = useState({ planos: 0, pacientes: 0 });

    useEffect(() => {
        async function fetchResumo() {
            try {
                const res = await axios.get('http://localhost:8080/api/resumo');
                setDados(res.data);
            } catch (err) {
                console.error('Erro ao buscar dados do dashboard:', err);
            }
        }
        fetchResumo();
    }, []);

    return (
        <div className="p-4">
            <h1 className="text-2xl font-bold mb-4">Painel Inicial</h1>
            <div className="grid grid-cols-2 gap-4">
                <div className="bg-blue-100 p-4 rounded shadow">
                    <h2 className="text-xl font-semibold">Planos Alimentares</h2>
                    <p className="text-3xl">{dados.planos}</p>
                </div>
                <div className="bg-green-100 p-4 rounded shadow">
                    <h2 className="text-xl font-semibold">Pacientes</h2>
                    <p className="text-3xl">{dados.pacientes}</p>
                </div>
            </div>
        </div>
    );
}

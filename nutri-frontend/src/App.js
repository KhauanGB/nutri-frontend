import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import Login from './components/Login';
import Cadastro from './components/Cadastro';
import CadastroPlano from './components/PlanoCadastro';
import Refeicoes from './components/RefeicaoCadastro';
import VisualizarPlano from './components/PlanoAlimentarLista';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<Login />} />
                <Route path="/cadastro" element={<Cadastro />} />
                <Route path="/plano/cadastro" element={<CadastroPlano />} />
                <Route path="/refeicoes/:id" element={<Refeicoes />} />
                <Route path="/plano/visualizar/:id" element={<VisualizarPlano />} />
            </Routes>
        </Router>
    );
}

export default App;

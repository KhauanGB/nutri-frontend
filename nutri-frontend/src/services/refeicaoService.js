import axios from 'axios';
const API = 'http://localhost:8080/api/refeicoes';

export const criarRefeicao = (dados) => axios.post(API, dados);
export const listarRefeicoes = () => axios.get(API);
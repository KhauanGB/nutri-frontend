import axios from 'axios';
const API = 'http://localhost:8080/api/usuarios';

export const getUsuario = (id) => axios.get(`${API}/${id}`);
export const atualizarUsuario = (id, dados) => axios.put(`${API}/${id}`, dados);
export const listarUsuarios = () => axios.get(API).then(res => res.data);
package horizon.dao;

import horizon.modelo.*;

import java.util.List;

public class DAOCliente {
	
	public void cadastrar(Cliente cliente) throws DAOException;

	public void atualizar(Cliente cliente) throws DAOException;

	public void remover(Cliente cliente) throws DAOException;

	public List<Cliente> todosClientes() throws DAOException;

	public liente buscarCliente(Long id) throws DAOException;

}

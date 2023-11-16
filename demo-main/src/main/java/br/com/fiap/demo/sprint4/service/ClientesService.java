package br.com.fiap.demo.sprint4.service;

import java.util.List;

import br.com.fiap.demo.sprint4.data.ClientesDao;
import br.com.fiap.demo.sprint4.model.Clientes;


public class ClientesService {
	
	static ClientesDao dao = new ClientesDao();

	public List<Clientes> listarClientes() {
		return dao.listarClientes();
	}
	
	public Clientes consultarClientes(Long id) {
		return dao.consultarClientes(id);
	}
	
	public boolean cadastraClientes(Clientes cliente) {
		if (!validar(cliente)) return false;
		dao.cadastraClientes(cliente);
		return true;
	}
	private boolean validar(Clientes cliente) {
		if (cliente.nome().isEmpty()) return false;
		if (cliente.telefone().isEmpty()) return false;
		if (cliente.email().isEmpty()) return false;
		if (cliente.cpf().isEmpty()) return false;
		
		return true;
	}

	public boolean deletaClientes(Long id) {
		Clientes cliente = dao.consultarClientes(id);

		if (cliente != null) {
			dao.deletaClientes(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean atualizaClientes(Long id, Clientes clienteAtualizado) {
		Clientes clienteExistente = dao.consultarClientes(id);

		if (clienteExistente != null) {
			Clientes clienteAtualizadoComId = new Clientes(
					clienteExistente.id(),
					clienteAtualizado.nome(),
					clienteAtualizado.telefone(),
					clienteAtualizado.email(),
					clienteAtualizado.cpf()
			);

			dao.atualizaClientes(clienteAtualizadoComId);
			
			return true;
		} else {
			return false;
		}
	}
	
	
}

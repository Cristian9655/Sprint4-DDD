package br.com.fiap.demo.sprint4.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.sprint4.model.Clientes;

public class ClientesDao {

	private static final String SELECT_ALL_CLIENTES = "SELECT * FROM clientes";
	private static final String SELECT_CLIENTES_BY_ID = "SELECT * FROM clientes WHERE id = ?";
	private static final String INSERT_CLIENTE = "INSERT INTO clientes (nome, telefone, email, cpf) VALUES (?, ?, ?, ?)";

	public List<Clientes> listarClientes() {
		List<Clientes> lista = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTES);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				lista.add(mapResultSetToClientes(resultSet));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes", e);
		}
		return lista;
	}

	public Clientes consultarClientes(Long id) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTES_BY_ID)) {

			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToClientes(resultSet);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar cliente por ID", e);
		}
		return null;
	}

	public boolean cadastraClientes(Clientes cliente) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE)) {

			preparedStatement.setString(1, cliente.nome());
			preparedStatement.setString(2, cliente.telefone());
			preparedStatement.setString(3, cliente.email());
			preparedStatement.setString(4, cliente.cpf());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar cliente", e);
		}
	}

	private Clientes mapResultSetToClientes(ResultSet resultSet) throws SQLException {
		return new Clientes(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("telefone"),
				resultSet.getString("email"), resultSet.getString("cpf"));
	}

	public void deletaClientes(Long id) {
		String DELETE_CLIENTE_BY_ID = "DELETE FROM clientes WHERE id = ?";
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTE_BY_ID)) {

			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir cliente por ID", e);
		}
	}

	public void atualizaClientes(Clientes clienteAtualizadoComId) {
	    String UPDATE_CLIENTE = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, cpf = ? WHERE id = ?";
	    try (Connection connection = ConnectionFactory.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE)) {

	        preparedStatement.setString(1, clienteAtualizadoComId.nome());
	        preparedStatement.setString(2, clienteAtualizadoComId.telefone());
	        preparedStatement.setString(3, clienteAtualizadoComId.email());
	        preparedStatement.setString(4, clienteAtualizadoComId.cpf());
	        preparedStatement.setLong(5, clienteAtualizadoComId.id());

	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao atualizar cliente", e);
	    }
	}

}

package br.com.fiap.demo.sprint4.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.sprint4.model.Sinistros;

public class SinistrosDao {
	private static final String SELECT_ALL_SINISTROS = "SELECT * FROM sinistros";
	private static final String SELECT_SINISTROS_BY_ID = "SELECT * FROM sinistros WHERE id = ?";
	private static final String INSERT_SINISTROS = "INSERT INTO sinistros (data_sinistro, descricao, valor_prejuizo, id_cliente, id_bike) VALUES (?, ?, ?, ?, ?)";

	public List<Sinistros> listarSinistros() {
		List<Sinistros> lista = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SINISTROS);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				lista.add(mapResultSetToSinistros(resultSet));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar sinistros", e);
		}
		return lista;
	}

	public Sinistros consultarSinistros(Long id) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SINISTROS_BY_ID)) {

			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToSinistros(resultSet);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar sinistro por ID", e);
		}
		return null;
	}

	public boolean cadastraSinistros(Sinistros sinistros) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINISTROS)) {

			preparedStatement.setString(1, sinistros.data_sinistro());
			preparedStatement.setString(2, sinistros.descricao());
			preparedStatement.setLong(3, sinistros.valor_prejuizo());
			preparedStatement.setLong(4, sinistros.id_cliente());
			preparedStatement.setLong(5, sinistros.id_bike());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar sinistro", e);
		}

	}

	private Sinistros mapResultSetToSinistros(ResultSet resultSet) throws SQLException {
		return new Sinistros(resultSet.getLong("id"), resultSet.getString("data_sinistro"),
				resultSet.getString("descricao"), resultSet.getLong("valor_prejuizo"), resultSet.getLong("id_cliente"),
				resultSet.getLong("id_bike"));
	}

	public void deletaSinistros(Long id) {
		String DELETE_SINISTRO_BY_ID = "DELETE FROM sinistros WHERE id = ?";
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINISTRO_BY_ID)) {

			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir sinistro por ID", e);
		}
	}

}

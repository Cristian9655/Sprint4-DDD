package br.com.fiap.demo.sprint4.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.sprint4.model.Vistorias;

public class VistoriasDao {
	private static final String SELECT_ALL_VISTORIAS = "SELECT * FROM vistorias";
	private static final String SELECT_VISTORIAS_BY_ID = "SELECT * FROM vistorias WHERE id = ?";
	private static final String INSERT_VISTORIAS = "INSERT INTO vistorias (fotos_videos, id_cliente, id_bike) VALUES (?, ?, ?)";

	public List<Vistorias> listarVistorias() {
		List<Vistorias> lista = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VISTORIAS);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				lista.add(mapResultSetToVistorias(resultSet));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar vistorias", e);
		}
		return lista;
	}

	public Vistorias consultarVistorias(Long id) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VISTORIAS_BY_ID)) {

			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToVistorias(resultSet);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar vistoria por ID", e);
		}
		return null;
	}

	public boolean cadastraVistorias(Vistorias vistorias) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VISTORIAS)) {

			preparedStatement.setString(1, vistorias.fotos_videos());
			preparedStatement.setLong(2, vistorias.id_cliente());
			preparedStatement.setLong(3, vistorias.id_bike());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar vistoria", e);
		}

	}

	private Vistorias mapResultSetToVistorias(ResultSet resultSet) throws SQLException {
		return new Vistorias(resultSet.getLong("id"), resultSet.getString("fotos_videos"),
				resultSet.getLong("id_cliente"), resultSet.getLong("id_bike"));
	}

	public void deletaVistorias(Long id) {
		String DELETE_VISTORIA_BY_ID = "DELETE FROM vistorias WHERE id = ?";
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VISTORIA_BY_ID)) {

			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir vistorias por ID", e);
		}
	}

}

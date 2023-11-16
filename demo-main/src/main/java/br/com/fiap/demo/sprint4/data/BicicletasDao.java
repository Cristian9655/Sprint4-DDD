package br.com.fiap.demo.sprint4.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.sprint4.model.Bicicletas;

public class BicicletasDao {

	private static final String SELECT_ALL_BICICLETA = "SELECT * FROM bicicletas";
	private static final String SELECT_BICICLETA_BY_ID = "SELECT * FROM bicicletas WHERE id = ?";
	private static final String INSERT_BICICLETA = "INSERT INTO bicicletas (marca, modelo, ano_fabricacao, valor_mercado, num_serie) VALUES (?, ?, ?, ?, ?)";

	public List<Bicicletas> listarBicicletas() {
		List<Bicicletas> lista = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BICICLETA);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				lista.add(mapResultSetToBicicletas(resultSet));
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao listar clientes", e);
		}
		return lista;
	}

	public Bicicletas consultarBicicletas(Long id) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BICICLETA_BY_ID)) {

			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToBicicletas(resultSet);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao consultar Bicicleta por ID", e);
		}
		return null;
	}

	public boolean cadastraBicicletas(Bicicletas bicicleta) {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BICICLETA)) {

			preparedStatement.setString(1, bicicleta.marca());
			preparedStatement.setString(2, bicicleta.modelo());
			preparedStatement.setString(3, bicicleta.ano_fabricacao());
			preparedStatement.setString(4, bicicleta.valor_mercado());
			preparedStatement.setString(5, bicicleta.num_serie());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar cliente", e);
		}
	}

	private Bicicletas mapResultSetToBicicletas(ResultSet resultSet) throws SQLException {
		return new Bicicletas(resultSet.getLong("id"), resultSet.getString("marca"), resultSet.getString("modelo"),
				resultSet.getString("ano_fabricacao"), resultSet.getString("valor_mercado"),
				resultSet.getString("num_serie"));
	}

	public void deletaBicicletas(Long id) {
		String DELETE_CLIENTE_BY_ID = "DELETE FROM bicicletas WHERE id = ?";
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTE_BY_ID)) {

			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir cliente por ID", e);
		}

	}

	public void atualizaBicicletas(Bicicletas bicicletaAtualizadoComId) {
		String UPDATE_BICICLETA = "UPDATE bicicletas SET marca = ?, modelo = ?, ano_fabricacao = ?, valor_mercado = ?, num_serie = ?  WHERE id = ?";
	    try (Connection connection = ConnectionFactory.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BICICLETA)) {

	        preparedStatement.setString(1, bicicletaAtualizadoComId.marca());
	        preparedStatement.setString(2, bicicletaAtualizadoComId.modelo());
	        preparedStatement.setString(3, bicicletaAtualizadoComId.ano_fabricacao());
	        preparedStatement.setString(4, bicicletaAtualizadoComId.valor_mercado());
	        preparedStatement.setString(5, bicicletaAtualizadoComId.num_serie());
	        preparedStatement.setLong(6, bicicletaAtualizadoComId.id());

	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao atualizar bicicleta", e);
	    }
		
	}

}

package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Cliente;
import sistemaClientes.Conexao;
public class ClienteDAO {

	public static void inserir(Cliente cliente) {
		String sql = "INSERT INTO clientes(nome, idade, email) VALUES(?,?,?)";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, cliente.getNome());
			statement.setInt(2, cliente.getIdade());
			statement.setString(3, cliente.getEmail());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
		}
	}

	public static List<Cliente> listarTodos() {
		String sql = "SELECT * FROM clientes";
		List<Cliente> listaCliente = new ArrayList<>();

		try (Connection conexao = Conexao.getConexao();
				PreparedStatement statement = conexao.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id"));
				cliente.setNome(result.getString("nome"));
				cliente.setIdade(result.getInt("idade"));
				cliente.setEmail(result.getString("email"));

				listaCliente.add(cliente);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
		}

		return listaCliente;
	}

	public static Cliente buscarPorId(int id) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE id = ?";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);

			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(result.getInt("id"));
					cliente.setNome(result.getString("nome"));
					cliente.setIdade(result.getInt("idade"));
					cliente.setEmail(result.getString("email"));
					return cliente;
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao encontrar cliente " + e.getMessage());
			}
			return null;
		}

	}

	public static boolean deletar(int id) throws SQLException {
		String sql = "DELETE FROM clientes WHERE id = ?";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);

			int linhasAfetadas = statement.executeUpdate();
			return linhasAfetadas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar cliente " + e.getMessage());
		}
	}
}
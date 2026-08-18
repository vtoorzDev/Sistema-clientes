package sistemaClientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes";
	private static final String USUARIO = "root";
	private static final String SENHA = "Victorconta315@";

	public static Connection getConexao() {
		try {
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
}

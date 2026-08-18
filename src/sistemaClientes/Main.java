package sistemaClientes;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.ClienteDAO;
import entities.Cliente;

public class Main {

	private static ClienteDAO clienteDAO = new ClienteDAO();

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		menuEscolha(entrada);
		entrada.close();
	}

	public static void menuEscolha(Scanner entrada) {
		int opcaoEscolhida = 0;

		do {
			System.out.println("\n MENU");
			System.out.println("Digite (1) para cadastrar cliente");
			System.out.println("Digite (2) para encontrar cliente");
			System.out.println("Digite (3) para listar todos os clientes");
			System.out.println("Digite (4) para excluir cliente");
			System.out.print("Digite (0) para sair do sistema\nOpção: ");

			opcaoEscolhida = entrada.nextInt();
			entrada.nextLine();

			switch (opcaoEscolhida) {
			case 1:
				cadastrarCliente(entrada);
				break;
			case 2:
				encontrarCliente(entrada);
				break;
			case 3:
				listarCliente();
				break;
			case 4:
				deletarCliente(entrada);
				break;
			case 0:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}

		} while (opcaoEscolhida != 0);
	}

	private static void cadastrarCliente(Scanner entrada) {
		System.out.println("\n Cadastrar Cliente ");

		System.out.print("Digite o nome do cliente: ");
		String nomeCliente = entrada.nextLine();

		System.out.print("Digite a idade do cliente: ");
		int idadeCliente = entrada.nextInt();
		entrada.nextLine();

		System.out.print("Digite o email do cliente: ");
		String emailCliente = entrada.nextLine();

		Cliente cliente = new Cliente();
		cliente.setNome(nomeCliente);
		cliente.setIdade(idadeCliente);
		cliente.setEmail(emailCliente);

		try {
			clienteDAO.inserir(cliente);
			System.out.println(" Cliente cadastrado com sucesso!");
		} catch (Exception e) {
			System.out.println(" Erro ao cadastrar cliente: " + e.getMessage());
		}
	}

	private static void listarCliente() {
		System.out.println("\n Lista de todos os clientes ");

		try {
			List<Cliente> listaClientes = ClienteDAO.listarTodos();

			if (listaClientes.isEmpty()) {
				System.out.println("Nenhum cliente cadastrado no banco de dados.");
			} else {
				for (Cliente cliente : listaClientes) {
					System.out.println("ID: " + cliente.getId() + " = Nome: " + cliente.getNome() + " = Idade: "
							+ cliente.getIdade() + " = Email: " + cliente.getEmail());
				}
			}
		} catch (Exception e) {
			System.out.println(" Erro ao buscar lista: " + e.getMessage());
		}
	}

	private static void encontrarCliente(Scanner entrada) {
		System.out.print("\n Digite o id do cliente que você quer encontrar: ");
		int id = entrada.nextInt();
		entrada.nextLine();

		try {
			Cliente cliente = ClienteDAO.buscarPorId(id);
			if (cliente != null) {
				System.out.println("\n Dados do cliente: ");
				System.out.println("Id: " + cliente.getId());
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("Idade: " + cliente.getIdade());
				System.out.println("Email: " + cliente.getEmail());
			} else {
				System.out.println("Nenhum cliente localizado com esse id");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar cliente");
		}
	}
	
	private static void deletarCliente(Scanner entrada) {
		System.out.println("\n Digite o id do cliente que você quer deletar");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		try {
			boolean deletado = ClienteDAO.deletar(id);
			
			if (deletado) {
				System.out.println("Cliente: " + id + "deletado com sucesso!!");
			} else {
				System.out.println("Nenhum cliente encontrado com esse id");
			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar cliente " + e.getMessage() );
		}
	}
}
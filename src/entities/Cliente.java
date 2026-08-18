package entities;

public class Cliente {
	private Integer id;
	private String nome;
	private String email;
	private Integer idade;
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome, String email, Integer idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String i) {
		this.email = i;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", idade=" + idade + "]";
	}
	
	
	
}

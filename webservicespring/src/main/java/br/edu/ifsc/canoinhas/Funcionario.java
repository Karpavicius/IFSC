package br.edu.ifsc.canoinhas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Funcionario {

	private final Integer id;
	private final String nome;
	private final Integer idade;

	public Funcionario(
			@JsonProperty("id") int id, 
			@JsonProperty("nome") String nome,
			@JsonProperty("idade") int idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	public Funcionario(Integer id, String nome, Integer idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}

}

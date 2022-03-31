package br.com.sp.turisticopoint.turisticopoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.sp.turisticopoint.turisticopoint.util.HashUtil;
import lombok.Data;

// Cria os getters and setters
@Data
// Mapeia a entidade para o JPA
@Entity
public class Administrador {
	// Marcando como chave primaria e auto incremento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	// Define a coluna email como índice único
	@Email
	@Column(unique = true)
	private String email;
	@NotEmpty
	private String senha;
	
	// método set que aplica o hash na senha
	public void setSenha(String senha) {
		this.senha = HashUtil.hash(senha);
	}
	
	// metodo que aplica a senha do administrador sem o hash
	public void setSenhaComHash(String hash) {
		this.senha = hash;
	}
	
}

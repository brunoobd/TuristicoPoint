package br.com.sp.turisticopoint.turisticopoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class TipoAmbiente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	@NotEmpty
	private String palavrasChave;
}

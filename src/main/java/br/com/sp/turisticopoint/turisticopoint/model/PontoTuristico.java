package br.com.sp.turisticopoint.turisticopoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class PontoTuristico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	@Column(columnDefinition = "TEXT")
	private String descricao;
	@NotEmpty
	private String cep;
	@NotEmpty
	private String logradouro;
	@NotEmpty
	private String numero;
	@NotEmpty
	private String bairro;
	@NotEmpty
	private String cidade;
	@NotEmpty
	private String estado;
	@NotEmpty
	private String horario;
	@NotNull
	private boolean estacionamento;
	@NotNull
	@ManyToOne
	private TipoAmbiente tipo;
	@Column(columnDefinition = "TEXT")
	private String fotos;
}

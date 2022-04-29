package br.com.sp.turisticopoint.turisticopoint.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private PontoTuristico ponto;
	private double nota;
	private String comentario;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Calendar dataVisita;
	@ManyToOne
	private Usuario usuario;
}

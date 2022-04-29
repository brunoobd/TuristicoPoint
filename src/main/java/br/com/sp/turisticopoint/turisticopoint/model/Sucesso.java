package br.com.sp.turisticopoint.turisticopoint.model;

import lombok.Data;

@Data
public class Sucesso {
	private int status = 200;
	private String mensagem;
	
	public Sucesso(String mensagem) {
		this.mensagem = mensagem;
	}
}

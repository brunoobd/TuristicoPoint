package br.com.sp.turisticopoint.turisticopoint.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Erro {
	private HttpStatus status;
	private String mensagem;
	private String exception;
	
	public Erro(HttpStatus status, String mensagem, String exception) {
		this.status = status;
		this.mensagem = mensagem;
		this.exception = exception;
	}
	
	
}

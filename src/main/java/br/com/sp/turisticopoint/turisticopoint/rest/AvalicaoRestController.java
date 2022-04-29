package br.com.sp.turisticopoint.turisticopoint.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sp.turisticopoint.turisticopoint.annotation.Privado;
import br.com.sp.turisticopoint.turisticopoint.model.Avaliacao;
import br.com.sp.turisticopoint.turisticopoint.repository.AvaliacaoRepository;

@RestController
@RequestMapping("api/avaliacao")
public class AvalicaoRestController {
	@Autowired
	AvaliacaoRepository repository;
	
	@Privado
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Avaliacao> criarAvaliacao (@RequestBody Avaliacao avaliacao) {
		repository.save(avaliacao);
		return ResponseEntity.created(URI.create("api/avaliacao/"+avaliacao.getId())).body(avaliacao);
	}
}

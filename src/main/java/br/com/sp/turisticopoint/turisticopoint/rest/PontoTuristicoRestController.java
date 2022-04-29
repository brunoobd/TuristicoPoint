package br.com.sp.turisticopoint.turisticopoint.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sp.turisticopoint.turisticopoint.annotation.Publico;
import br.com.sp.turisticopoint.turisticopoint.model.PontoTuristico;
import br.com.sp.turisticopoint.turisticopoint.repository.PontoTuristicoRepository;

@RestController
@RequestMapping("/api/PontoTuristico")
public class PontoTuristicoRestController {
	@Autowired
	private PontoTuristicoRepository repository;

	@Publico
	@RequestMapping(value="", method = RequestMethod.GET)
	public Iterable<PontoTuristico> getPontosTuristicos() {
		return repository.findAll();
	}

	@Publico
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<PontoTuristico> getPontoTuristico(@PathVariable("id") Long id) {
		// tenta buscar o ponto turistico
		Optional<PontoTuristico> optional = repository.findById(id);
		// se o ponto turistico existir
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Publico
	@RequestMapping(value="/tipo/{tipo}", method = RequestMethod.GET)
	public Iterable<PontoTuristico> getTipoPontoTuristico(@PathVariable("tipo") Long tipo) {
		return repository.findByTipoId(tipo);
	}
}

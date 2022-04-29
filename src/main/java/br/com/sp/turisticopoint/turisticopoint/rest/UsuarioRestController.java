package br.com.sp.turisticopoint.turisticopoint.rest;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sp.turisticopoint.turisticopoint.annotation.Privado;
import br.com.sp.turisticopoint.turisticopoint.annotation.Publico;
import br.com.sp.turisticopoint.turisticopoint.model.Erro;
import br.com.sp.turisticopoint.turisticopoint.model.Sucesso;
import br.com.sp.turisticopoint.turisticopoint.model.Usuario;
import br.com.sp.turisticopoint.turisticopoint.repository.UsuarioRepository;

@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {
	@Autowired
	private UsuarioRepository repository;
	
	@Publico
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> criarUsuario(@RequestBody Usuario usuario) {
		try {
			// insere o usuario no banco de dados
			repository.save(usuario);
			// retorna um codigo HTTP 201, informa como acessar o recurso inserido
			// e acresenta no corpo da resposta o objeto inserido
			return ResponseEntity.created(URI.create("?api/usuario/"+usuario.getId())).body(usuario);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro(HttpStatus.INTERNAL_SERVER_ERROR, "Registro Duplicado", e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			Erro erro = new Erro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Privado
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
		// tenta buscar o ponto turistico
		Optional<Usuario> optional = repository.findById(id);
		// se o ponto turistico existir
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Privado
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sucesso> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		// validação do ID
		if (id != usuario.getId()) {
			throw new RuntimeException("ID inválido");
		}
		repository.save(usuario);
		Sucesso sucesso = new Sucesso("Usuario alterado com sucesso.");
		return new ResponseEntity<Sucesso>(sucesso, HttpStatus.OK);
	}
	
	@Privado
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

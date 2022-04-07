package br.com.sp.turisticopoint.turisticopoint.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sp.turisticopoint.turisticopoint.model.TipoAmbiente;
import br.com.sp.turisticopoint.turisticopoint.repository.TipoAmbienteRepository;


@Controller
public class TipoAmbienteController {
	
	@Autowired
	TipoAmbienteRepository repository;
	
	@RequestMapping("CadastroTipoAmbiente")
	public String form() {
		return "tipoambiente/form";
	}
	
	@RequestMapping("SalvarTipoAmbiente")
	public String salvarTipoAmbiente(@Valid TipoAmbiente tipo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("mensagemErro", "Verifique os campos, campo vazio...");
			return "redirect:CadastroTipoAmbiente";
		}
		try {
			repository.save(tipo);
			attr.addFlashAttribute("mensagemSucesso", "Sucesso ao cadastrar tipo de ambiente...");
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "Houve um erro ao cadastrar... Erro: "+e.getMessage());
		}
		return "redirect:CadastroTipoAmbiente";
	}
	
	@RequestMapping("ListaTipoAmbiente/{page}")
	public String list(Model model, @PathVariable("page") int page) {
		PageRequest pageble = PageRequest.of(page - 1, 3, Sort.by(Sort.Direction.ASC, "nome"));
		Page<TipoAmbiente> pagina = repository.findAll(pageble);
		model.addAttribute("tipo", pagina.getContent());
		int totalPages = pagina.getTotalPages();
		List<Integer> numPaginas = new ArrayList<Integer>();
		
		for (int i = 1; i <= totalPages; i++) {
			numPaginas.add(i);
		}
		
		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("pageAtual", page);
		
		return "tipoambiente/list";
	}
	
	@RequestMapping("ExcluirTipoAmbiente")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:ListaTipoAmbiente/1";
	}
	
	@RequestMapping("AlterarTipoAmbiente")
	public String atualizar(Model model, Long id) {
		TipoAmbiente tipo = repository.findById(id).get();
		model.addAttribute("tipo", tipo);
		return "forward:CadastroTipoAmbiente";
	}
	
	@RequestMapping("searchAmbiente")
	public String search(Model model, String busca, String escolha) {
		model.addAttribute("escolha", escolha);
		model.addAttribute("busca", busca);
		if (escolha.equals("nome")) {
			model.addAttribute("tipo", repository.buscarPorNome(busca));
			if (repository.buscarPorNome(busca).isEmpty()) {
				model.addAttribute("mensagemErro", "Não existe um tipo de ambiente com esse nome...");
			} else {
				model.addAttribute("mensagemSucesso", "Sucesso ao buscar um tipo de ambiente com o nome: "+busca+".");
			}
		} else if (escolha.equals("descricao")) {
			model.addAttribute("tipo", repository.buscarPorDescricao(busca));
			if (repository.buscarPorDescricao(busca).isEmpty()) {
				model.addAttribute("mensagemErro", "Não existe um tipo de ambiente com essa descrição...");
			} else {
				model.addAttribute("mensagemSucesso", "Sucesso ao buscar um tipo de ambiente com a descrição: "+busca+".");
			}
		} else {
			model.addAttribute("tipo", repository.buscarPorPalavraChave(busca));
			if (repository.buscarPorPalavraChave(busca).isEmpty()) {
				model.addAttribute("mensagemErro", "Não existe um tipo de ambiente com essa(s) palavra(s) chave(s)...");
			} else {
				model.addAttribute("mensagemSucesso", "Sucesso ao buscar um tipo de ambiente com essa(s) palavra(s) chave(s): "+busca+".");
			}
		}
		return "tipoambiente/list";
	}
}

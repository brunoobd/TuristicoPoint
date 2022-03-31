package br.com.sp.turisticopoint.turisticopoint.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sp.turisticopoint.turisticopoint.model.Administrador;
import br.com.sp.turisticopoint.turisticopoint.repository.AdministradorRepository;
import br.com.sp.turisticopoint.turisticopoint.util.HashUtil;

@Controller
public class AdministradorController {
	
	@Autowired
	AdministradorRepository repository;
	
	@RequestMapping("CadastroAdmin")
	public String form() {
		return "administrador/form";
	}
	
	// request mapping para salvar o Administrador, do tipo POST
	@RequestMapping(value="salvarAdmin", method = RequestMethod.POST)
	// @Valid para validar os campos, BindingResult para verificar se tudo foi validado e RedirectAttributes para pendurar variaveis no redirect
	public String salvarAdministrador(@Valid Administrador admin, BindingResult result, RedirectAttributes attr) {
		// verifica se houveram erros na validação
		if (result.hasErrors()) {
			// mensagem de erro caso administrador não seja seja cadastrado
			attr.addFlashAttribute("mensagemErro", "Verifique os campos, email duplicado ou senha faltando algo...");
			return "redirect:CadastroAdmin";
		}
		// variavel para descobrir alteração ou inserção
		boolean alteracao = admin.getId() != null ? true : false;
		// verifica se a senha esta vazia
		if (admin.getSenha().equals(HashUtil.hash(""))) {
			if (!alteracao) {
				// retirar a parte antes do @ no e-mail
				String parte = admin.getEmail().substring(0, admin.getEmail().indexOf("@"));
				// "setar" a parte na senha do main
				admin.setSenha(parte);
			} else {
				String hash = repository.findById(admin.getId()).get().getSenha();
				// colocando a senha que ja esta com hash no banco, sem aplicar hash em cima de hash
				admin.setSenhaComHash(hash); 
			}
		}
		try {
			repository.save(admin);
			// mensagem de sucesso caso administrador seja cadastrado
			attr.addFlashAttribute("mensagemSucesso", "Sucesso, administrador cadastrado!");
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "Houve um erro ao cadastrar... Erro: "+e.getMessage());
		}
		return "redirect:CadastroAdmin";
	}
	
	@RequestMapping("ListaAdministrador/{page}")
	// @PathVariable é usado para associar o page do request mapping com o page do parametro...
	public String list(Model model, @PathVariable("page") int page) {
		// cria um pageable informando os parametros da página
		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "nome"));
		// cria um page de Administrador através dos parâmetros passados ao repository
		Page<Administrador> pagina = repository.findAll(pageable);
		model.addAttribute("admins", pagina.getContent());
		// variavel para o total de paginas
		int totalPages = pagina.getTotalPages();
		// Cria um List de inteiros para armazenar os numeros das paginas
		List<Integer> numPaginas = new ArrayList<Integer>();
		// preencher o list com as páginas
		for (int i = 1; i <= totalPages; i++) {
			// adiciona a página ao list
			numPaginas.add(i);
		}
		
		// adiciona os valores na model
		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("pageAtual", page);
		
		// retorna o arquivo da lista
		return "administrador/list";
	}
	
	@RequestMapping("AlterarAdmin")
	public String alterar(Model model, Long id) {
		Administrador admin = repository.findById(id).get();
		model.addAttribute("admin", admin);
		return "forward:CadastroAdmin";
	}
	
	@RequestMapping("ExcluirAdmin")
	public String apagar(Long id) {
		repository.deleteById(id);
		return "redirect:ListaAdministrador/1";
	}
}

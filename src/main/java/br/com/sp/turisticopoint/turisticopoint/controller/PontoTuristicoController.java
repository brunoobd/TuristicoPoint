package br.com.sp.turisticopoint.turisticopoint.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.sp.turisticopoint.turisticopoint.model.Administrador;
import br.com.sp.turisticopoint.turisticopoint.model.PontoTuristico;
import br.com.sp.turisticopoint.turisticopoint.repository.PontoTuristicoRepository;
import br.com.sp.turisticopoint.turisticopoint.repository.TipoAmbienteRepository;
import br.com.sp.turisticopoint.turisticopoint.util.FirebaseUtil;

@Controller
public class PontoTuristicoController {
	@Autowired
	PontoTuristicoRepository repPontoTuristico;

	@Autowired
	TipoAmbienteRepository repTipoAmbiente;

	@Autowired
	private FirebaseUtil fireUtil;

	@RequestMapping("CadastroPontoTuristico")
	public String form(Model model) {
		model.addAttribute("tipo", repTipoAmbiente.findAllByOrderByNomeAsc());
		return "pontoturistico/form";
	}

	@RequestMapping("SalvarPontoTuristico")
	public String salvar(PontoTuristico ponto, @RequestParam("fileFotos") MultipartFile[] fileFotos) {
		// repPontoTuristico.save(ponto);
		// String para armazenar as URLS
		String fotos = "";
		// percorre cada arquivo no vetor
		for (MultipartFile arquivo : fileFotos) {
			// verifica se o arquivo existe
			if (arquivo.getOriginalFilename().isEmpty()) {
				continue;
			}
			try {
				fotos += fireUtil.upload(arquivo) + ";";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		ponto.setFotos(fotos);
		repPontoTuristico.save(ponto);
		return "redirect:CadastroPontoTuristico";
	}
	
	@RequestMapping("ListaPontoTuristico/{page}")
	public String list(Model model, @PathVariable("page") int page) {
		// cria um pageable informando os parametros da página
				PageRequest pageable = PageRequest.of(page - 1, 3, Sort.by(Sort.Direction.ASC, "nome"));
				// cria um page de Administrador através dos parâmetros passados ao repository
				Page<PontoTuristico> pagina = repPontoTuristico.findAll(pageable);
				model.addAttribute("pontos", pagina.getContent());
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
		return "pontoturistico/list";
	}
	
	@RequestMapping("AlterarPontoTuristico")
	public String alterar(Model model, Long id) {
		PontoTuristico ponto = repPontoTuristico.findById(id).get();
		model.addAttribute("pontoTuristico", ponto);
		return "forward:/CadastroPontoTuristico";
	}
	
	@RequestMapping("ExcluirPontoTuristico")
	public String excluir(Long id) {
		PontoTuristico ponto = repPontoTuristico.findById(id).get();
		if (ponto.getFotos().length() > 0) {
			for (String foto : ponto.verFotos()) {
				fireUtil.deletar(foto);
			}
		}
		repPontoTuristico.delete(ponto);  
		return "redirect:/ListaPontoTuristico/1";
	}
	
	@RequestMapping("ExcluirFotos")
	public String excluirFotos(Long idPonto, int nmrFoto, Model model) {
		// busca o ponto turistico
		PontoTuristico ponto = repPontoTuristico.findById(idPonto).get();
		// busca a URL da foto
		String urlFoto = ponto.verFotos()[nmrFoto];
		// deleta foto
		fireUtil.deletar(urlFoto);
		// remove a url do adtributo fotos
		ponto.setFotos(ponto.getFotos().replace(urlFoto+";", ""));
		// salva no banco
		repPontoTuristico.save(ponto);
		model.addAttribute("pontoTuristico", ponto);
		return "forward:/CadastroPontoTuristico";
	}
}


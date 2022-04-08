package br.com.sp.turisticopoint.turisticopoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.sp.turisticopoint.turisticopoint.model.PontoTuristico;
import br.com.sp.turisticopoint.turisticopoint.repository.PontoTuristicoRepository;
import br.com.sp.turisticopoint.turisticopoint.repository.TipoAmbienteRepository;

@Controller
public class PontoTuristicoController {
	@Autowired
	PontoTuristicoRepository repPontoTuristico;
	
	@Autowired
	TipoAmbienteRepository repTipoAmbiente;
	
	@RequestMapping("CadastroPontoTuristico")
	public String form(Model model) {
		model.addAttribute("tipo", repTipoAmbiente.findAllByOrderByNomeAsc());
		return "pontoturistico/form";
	}
	
	@RequestMapping("SalvarPontoTuristico")
	public String salvar(PontoTuristico ponto, @RequestParam("fileFotos") MultipartFile[] fileFotos) {
		//repPontoTuristico.save(ponto);
		try {
			
		} catch (Exception e) {
			
		}
		System.out.println(fileFotos.length);
		return "redirect:CadastroPontoTuristico";
	}
}

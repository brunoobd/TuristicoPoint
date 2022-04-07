package br.com.sp.turisticopoint.turisticopoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

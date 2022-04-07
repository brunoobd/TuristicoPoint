package br.com.sp.turisticopoint.turisticopoint.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.sp.turisticopoint.turisticopoint.model.PontoTuristico;
import br.com.sp.turisticopoint.turisticopoint.model.TipoAmbiente;

public interface PontoTuristicoRepository extends PagingAndSortingRepository<PontoTuristico, Long> {
	
}

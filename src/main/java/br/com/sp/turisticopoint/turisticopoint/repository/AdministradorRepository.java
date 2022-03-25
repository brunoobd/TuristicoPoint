package br.com.sp.turisticopoint.turisticopoint.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.sp.turisticopoint.turisticopoint.model.Administrador;

public interface AdministradorRepository extends PagingAndSortingRepository<Administrador, Long>{
	
}

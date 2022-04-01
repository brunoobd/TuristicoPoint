package br.com.sp.turisticopoint.turisticopoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.sp.turisticopoint.turisticopoint.model.Administrador;
import br.com.sp.turisticopoint.turisticopoint.model.TipoAmbiente;

public interface AdministradorRepository extends PagingAndSortingRepository<Administrador, Long>{
	@Query("SELECT a FROM Administrador a WHERE a.nome LIKE %:n%")
	public List<Administrador> buscarPorNome(@Param("n") String nome);
	
	@Query("SELECT a FROM Administrador a WHERE a.email LIKE %:e%")
	public List<Administrador> buscarPorEmail(@Param("e") String email);
}

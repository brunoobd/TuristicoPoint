package br.com.sp.turisticopoint.turisticopoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.sp.turisticopoint.turisticopoint.model.TipoAmbiente;

public interface TipoAmbienteRepository extends PagingAndSortingRepository<TipoAmbiente, Long>{

	@Query("SELECT t FROM TipoAmbiente t WHERE t.nome LIKE %:n%")
	public List<TipoAmbiente> buscarPorNome(@Param("n") String nome);
	
	@Query("SELECT t FROM TipoAmbiente t WHERE t.descricao LIKE %:d%")
	public List<TipoAmbiente> buscarPorDescricao(@Param("d") String descricao);
	
	@Query("SELECT t FROM TipoAmbiente t WHERE t.palavrasChave LIKE %:p%")
	public List<TipoAmbiente> buscarPorPalavraChave(@Param("p") String palavraChave);
	
	public List<TipoAmbiente> findAllByOrderByNomeAsc();
}

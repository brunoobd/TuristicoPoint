package br.com.sp.turisticopoint.turisticopoint.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.sp.turisticopoint.turisticopoint.model.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{

}

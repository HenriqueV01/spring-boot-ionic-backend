package com.henriquevenancio.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.henriquevenancio.cursomc.domain.Cliente;
import com.henriquevenancio.cursomc.domain.Pedido;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Query("SELECT DISTINCT obj FROM Cliente obj INNER JOIN obj.pedidos ped WHERE obj.nome LIKE %:nome% AND ped IN :pedidos")
	Page<Cliente> search(@Param("nome") String nome,@Param("pedidos") List<Pedido> pedidos, Pageable pageRequest);

}

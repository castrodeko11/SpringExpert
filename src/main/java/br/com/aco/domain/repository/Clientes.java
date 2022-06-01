package br.com.aco.domain.repository;

import br.com.aco.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeLike(String cli);

    List<Cliente> findByNomeOrId(String nome, Integer id);

    boolean existsByNome(String nome);
}

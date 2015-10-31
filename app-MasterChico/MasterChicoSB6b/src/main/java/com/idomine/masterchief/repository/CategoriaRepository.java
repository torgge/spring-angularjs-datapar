package com.idomine.masterchief.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.masterchief.model.Categoria;

/**
 * Spring Data
 * 
 * == O Spring provê 3 abstrações para repositório de dados: 
 *    Repository, CrudRepository e PageAndSortingRepository
 *     
 * == A interface central do Spring Data é Repository
 * 
 * == A interface CrudRepository habilita funcionalidades 
 *    sofisticadas de CRUD para a classe de Entidade gerenciadas.
 *    
 * == Interface PageAndSortingRepository além de CRUD adiciona funcionalide de paginação de dados.
 * 
 * == Podemos usar o EntityManager injetado. 
 * 
 * == O Spring expôe o repositório por padrão. A opção exported=false não expôe.
 *    As vezes os métodos do repositório não têem o objetivo de expor end-points para consumo,
 *    mas uma maneira de manipular as classe do modelo conforme regras de negócios da aplicação 
 *    
 * == 
 * 
 * @author Lyndon Tavares
 *
 */


@RepositoryRestResource(collectionResourceRel = "categoria", path = "categoria", exported=true)
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,Long> {

	/*
	 * QUERYS 
	 * 
	 * criação de consultas através de definição de métodos
	 * 
	 * O métodos abaixo ira gerar a seguinte consulta:
	 * 
	 * select c from Categoria c where c.descricao = ?1 and id = ?2
	 * 
	 * Observe que os parâmetros do métodos correspondem aos parâmetros da consulta
	 * 
	 * A anotação @Param faz com os parâmetros da requisição direcionados via uri para esse
	 * repositório, sejam passados como parâmtros da consulta.
	 * 
	 * Dessa forma os repositórios funcionam como PROXY. 
	 * 
	 * Os repositórios proxy tem 2 modos para query: por nome de método e pela anotação @Query
	 * 
	 * 
	 * 
	 * 
	 * ALPS
	 * 
	 * Spring Data REST segue o padrão ALPS de metadados cuja finalidade é descrever as 
	 * semânticas dos recursos exportados. 
	 * 
	 * ALPS é um formato de dados para a definição de descrições simples de semântica em nível de aplicativo,
	 * semelhante em complexidade a micro-formatos HTML. Ele também suporta a adição de seus metadados de tipos 
	 * de mídia existentes. A partir da versão 2.2 M1, o Spring Data REST expõe recursos ALPES 
	 * baseados JSON que podem ajudar-nos a navegar seus recursos.
	 * 
	 *  Apóes iniciar a aplicação tente: http://localhost:5000/alps
	 * 
	 */
	
	
	List<Categoria> findByDescricaoAndId( @Param(value = "descricao") String descricao ,@Param(value = "id") long id);
	

	List<Categoria> findByDescricaoIsStartingWithAndId(String descricao,long id);
	

	@Query("select c from Categoria c where c.descricao = ?1")
	List<Categoria> listaDeCategoriasPorDescricao(String descricao );
	
	
	
	
	
}






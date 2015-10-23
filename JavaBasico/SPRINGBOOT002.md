# Métodos Query

Exemplo de utilização dos métodos query no Spring JPA. A anotação @Param é usuda para informar os paraâmetros da URL, que podem ter nomes diferentes dos parâmtros do método. Se omitido, nomes dos parâmetros métodos igual a nomes dos parâmetros da URL.

```java

@RepositoryRestResource(collectionResourceRel = "modulo", path = "modulo", exported=true)
public interface UsuarioModuloRepository extends PagingAndSortingRepository<UsuarioModulo,Long> {

	
	@Query("select m from UsuarioModulo m where m.id.usuario.id = :id and m.nomeModulo = 'NOME_DO_MODULO' ")
	UsuarioModulo findModulo(@Param("id") long id);

	@Query("select m from UsuarioModulo m where m.id.usuario.id = :id ")
	List<UsuarioModulo> findModulos(@Param("id") long id);
	
	List<UsuarioModulo> findByIdUsuarioLoginAndNomeModulo( 
		@Param("login") String login, 
		@Param("modulo") String modulo );
	
	List<UsuarioModulo> findByIdUsuarioLogin( 
		@Param("login") String login );
	
	List<UsuarioModulo> findByNomeModulo( 
		@Param("modulo") String modulo );
	
	
	List<UsuarioModulo> findByIdUsuarioLoginAndIdUsuarioTipoUsuarioAndIdUsuarioSituacaoAndNomeModulo( 
			@Param("login") String login, 
			@Param("tipo") String tipo, 
			@Param("situacao") String situacao, 
			@Param("modulo") String modulo );

	UsuarioModulo findFirst1ByIdUsuarioLoginAndIdUsuarioTipoUsuarioAndIdUsuarioSituacaoAndNomeModulo( 
			@Param("login") String login, 
			@Param("tipo") String tipo, 
			@Param("situacao") String situacao, 
			@Param("modulo") String modulo );

	UsuarioModulo findFirst1ByIdUsuarioIdAndIdUsuarioTipoUsuarioAndIdUsuarioSituacaoAndNomeModulo( 
			@Param("login") long id, 
			@Param("tipo") String tipo, 
			@Param("situacao") String situacao, 
			@Param("modulo") String modulo );
	
	UsuarioModulo findFirst1ByIdUsuarioIdAndNomeModulo( 
			@Param("login") long id, 
			@Param("modulo") String modulo );
	
}

```

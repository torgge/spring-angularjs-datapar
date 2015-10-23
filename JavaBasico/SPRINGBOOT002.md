# Métodos Query

Exemplo de utilização dos métodos query no Spring JPA.

```java

@RepositoryRestResource(collectionResourceRel = "modulo", path = "modulo", exported=true)
public interface UsuarioModuloRepository extends PagingAndSortingRepository<UsuarioModulo,Long> {

	
	@Query("select m from UsuarioModulo m where m.id.usuario.id = :id and m.nomeModulo = 'NOME_DO_MODULO' ")
	UsuarioModulo find*Modulo*(@Param("id") long id);

	@Query("select m from UsuarioModulo m where m.id.usuario.id = :id ")
	List<UsuarioModulo> findModulos(@Param("id") long id);
	
	List<UsuarioModulo> findBy*IdUsuario*Login*And*NomeModulo( 
		@Param("login") String login, 
		@Param("modulo") String modulo );
	
	List<UsuarioModulo> findBy*IdUsuario*Login( 
		@Param("login") String login );
	
	List<UsuarioModulo> findBy*NomeModulo( 
		@Param("modulo") String modulo );
	
	
	List<UsuarioModulo> findBy*IdUsuario*Login*And*IdUsuario*TipoUsuario*And*IdUsuario*Situacao*And*NomeModulo( 
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

/**
 * This class is generated by jOOQ
 */
package jooq.classesgeradas.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Produto extends org.jooq.impl.TableImpl<jooq.classesgeradas.tables.records.ProdutoRecord> {

	private static final long serialVersionUID = 1779748064;

	/**
	 * The singleton instance of <code>masterchico.produto</code>
	 */
	public static final jooq.classesgeradas.tables.Produto PRODUTO = new jooq.classesgeradas.tables.Produto();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.classesgeradas.tables.records.ProdutoRecord> getRecordType() {
		return jooq.classesgeradas.tables.records.ProdutoRecord.class;
	}

	/**
	 * The column <code>masterchico.produto.id</code>. 
	 */
	public final org.jooq.TableField<jooq.classesgeradas.tables.records.ProdutoRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>masterchico.produto.nome</code>. 
	 */
	public final org.jooq.TableField<jooq.classesgeradas.tables.records.ProdutoRecord, java.lang.String> NOME = createField("nome", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>masterchico.produto.preco</code>. 
	 */
	public final org.jooq.TableField<jooq.classesgeradas.tables.records.ProdutoRecord, java.lang.Double> PRECO = createField("preco", org.jooq.impl.SQLDataType.DOUBLE, this);

	/**
	 * Create a <code>masterchico.produto</code> table reference
	 */
	public Produto() {
		super("produto", jooq.classesgeradas.Masterchico.MASTERCHICO);
	}

	/**
	 * Create an aliased <code>masterchico.produto</code> table reference
	 */
	public Produto(java.lang.String alias) {
		super(alias, jooq.classesgeradas.Masterchico.MASTERCHICO, jooq.classesgeradas.tables.Produto.PRODUTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<jooq.classesgeradas.tables.records.ProdutoRecord, java.lang.Long> getIdentity() {
		return jooq.classesgeradas.Keys.IDENTITY_PRODUTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.classesgeradas.tables.records.ProdutoRecord> getPrimaryKey() {
		return jooq.classesgeradas.Keys.KEY_PRODUTO_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.classesgeradas.tables.records.ProdutoRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.classesgeradas.tables.records.ProdutoRecord>>asList(jooq.classesgeradas.Keys.KEY_PRODUTO_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.classesgeradas.tables.Produto as(java.lang.String alias) {
		return new jooq.classesgeradas.tables.Produto(alias);
	}
}
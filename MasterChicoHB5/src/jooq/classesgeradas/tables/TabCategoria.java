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
public class TabCategoria extends org.jooq.impl.TableImpl<jooq.classesgeradas.tables.records.TabCategoriaRecord> {

	private static final long serialVersionUID = -530823734;

	/**
	 * The singleton instance of <code>masterchico.tab_categoria</code>
	 */
	public static final jooq.classesgeradas.tables.TabCategoria TAB_CATEGORIA = new jooq.classesgeradas.tables.TabCategoria();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.classesgeradas.tables.records.TabCategoriaRecord> getRecordType() {
		return jooq.classesgeradas.tables.records.TabCategoriaRecord.class;
	}

	/**
	 * The column <code>masterchico.tab_categoria.id</code>. 
	 */
	public final org.jooq.TableField<jooq.classesgeradas.tables.records.TabCategoriaRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>masterchico.tab_categoria.descricao</code>. 
	 */
	public final org.jooq.TableField<jooq.classesgeradas.tables.records.TabCategoriaRecord, java.lang.String> DESCRICAO = createField("descricao", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this);

	/**
	 * Create a <code>masterchico.tab_categoria</code> table reference
	 */
	public TabCategoria() {
		super("tab_categoria", jooq.classesgeradas.Masterchico.MASTERCHICO);
	}

	/**
	 * Create an aliased <code>masterchico.tab_categoria</code> table reference
	 */
	public TabCategoria(java.lang.String alias) {
		super(alias, jooq.classesgeradas.Masterchico.MASTERCHICO, jooq.classesgeradas.tables.TabCategoria.TAB_CATEGORIA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<jooq.classesgeradas.tables.records.TabCategoriaRecord, java.lang.Long> getIdentity() {
		return jooq.classesgeradas.Keys.IDENTITY_TAB_CATEGORIA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.classesgeradas.tables.records.TabCategoriaRecord> getPrimaryKey() {
		return jooq.classesgeradas.Keys.KEY_TAB_CATEGORIA_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.classesgeradas.tables.records.TabCategoriaRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.classesgeradas.tables.records.TabCategoriaRecord>>asList(jooq.classesgeradas.Keys.KEY_TAB_CATEGORIA_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.classesgeradas.tables.TabCategoria as(java.lang.String alias) {
		return new jooq.classesgeradas.tables.TabCategoria(alias);
	}
}
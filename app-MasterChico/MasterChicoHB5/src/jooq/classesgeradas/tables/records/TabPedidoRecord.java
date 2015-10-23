/**
 * This class is generated by jOOQ
 */
package jooq.classesgeradas.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TabPedidoRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.classesgeradas.tables.records.TabPedidoRecord> implements org.jooq.Record4<java.lang.Long, java.sql.Timestamp, java.lang.String, java.lang.Long> {

	private static final long serialVersionUID = -1069435518;

	/**
	 * Setter for <code>masterchico.tab_pedido.id</code>. 
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido.id</code>. 
	 */
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido.data</code>. 
	 */
	public void setData(java.sql.Timestamp value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido.data</code>. 
	 */
	public java.sql.Timestamp getData() {
		return (java.sql.Timestamp) getValue(1);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido.situacao</code>. 
	 */
	public void setSituacao(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido.situacao</code>. 
	 */
	public java.lang.String getSituacao() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido.fornecedor_id</code>. 
	 */
	public void setFornecedorId(java.lang.Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido.fornecedor_id</code>. 
	 */
	public java.lang.Long getFornecedorId() {
		return (java.lang.Long) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Long, java.sql.Timestamp, java.lang.String, java.lang.Long> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Long, java.sql.Timestamp, java.lang.String, java.lang.Long> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field2() {
		return jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO.SITUACAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field4() {
		return jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO.FORNECEDOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value2() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getSituacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value4() {
		return getFornecedorId();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TabPedidoRecord
	 */
	public TabPedidoRecord() {
		super(jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO);
	}

	/**
	 * Create a detached, initialised TabPedidoRecord
	 */
	public TabPedidoRecord(java.lang.Long id, java.sql.Timestamp data, java.lang.String situacao, java.lang.Long fornecedorId) {
		super(jooq.classesgeradas.tables.TabPedido.TAB_PEDIDO);

		setValue(0, id);
		setValue(1, data);
		setValue(2, situacao);
		setValue(3, fornecedorId);
	}
}
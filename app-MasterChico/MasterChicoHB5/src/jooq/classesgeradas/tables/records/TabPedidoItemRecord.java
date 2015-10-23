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
public class TabPedidoItemRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.classesgeradas.tables.records.TabPedidoItemRecord> implements org.jooq.Record7<java.lang.Long, java.math.BigDecimal, java.math.BigDecimal, java.lang.Long, java.lang.Long, java.math.BigDecimal, java.math.BigDecimal> {

	private static final long serialVersionUID = 199010855;

	/**
	 * Setter for <code>masterchico.tab_pedido_item.id</code>. 
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.id</code>. 
	 */
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.quantidadePedido</code>. 
	 */
	public void setQuantidadepedido(java.math.BigDecimal value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.quantidadePedido</code>. 
	 */
	public java.math.BigDecimal getQuantidadepedido() {
		return (java.math.BigDecimal) getValue(1);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.quantidadeRecebido</code>. 
	 */
	public void setQuantidaderecebido(java.math.BigDecimal value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.quantidadeRecebido</code>. 
	 */
	public java.math.BigDecimal getQuantidaderecebido() {
		return (java.math.BigDecimal) getValue(2);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.mercaderia_id</code>. 
	 */
	public void setMercaderiaId(java.lang.Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.mercaderia_id</code>. 
	 */
	public java.lang.Long getMercaderiaId() {
		return (java.lang.Long) getValue(3);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.pedido_id</code>. 
	 */
	public void setPedidoId(java.lang.Long value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.pedido_id</code>. 
	 */
	public java.lang.Long getPedidoId() {
		return (java.lang.Long) getValue(4);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.quantidade_pedido</code>. 
	 */
	public void setQuantidadePedido(java.math.BigDecimal value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.quantidade_pedido</code>. 
	 */
	public java.math.BigDecimal getQuantidadePedido() {
		return (java.math.BigDecimal) getValue(5);
	}

	/**
	 * Setter for <code>masterchico.tab_pedido_item.quantidade_recebido</code>. 
	 */
	public void setQuantidadeRecebido(java.math.BigDecimal value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>masterchico.tab_pedido_item.quantidade_recebido</code>. 
	 */
	public java.math.BigDecimal getQuantidadeRecebido() {
		return (java.math.BigDecimal) getValue(6);
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
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Long, java.math.BigDecimal, java.math.BigDecimal, java.lang.Long, java.lang.Long, java.math.BigDecimal, java.math.BigDecimal> fieldsRow() {
		return (org.jooq.Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Long, java.math.BigDecimal, java.math.BigDecimal, java.lang.Long, java.lang.Long, java.math.BigDecimal, java.math.BigDecimal> valuesRow() {
		return (org.jooq.Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field2() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.QUANTIDADEPEDIDO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field3() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.QUANTIDADERECEBIDO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field4() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.MERCADERIA_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field5() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.PEDIDO_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field6() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.QUANTIDADE_PEDIDO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field7() {
		return jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM.QUANTIDADE_RECEBIDO;
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
	public java.math.BigDecimal value2() {
		return getQuantidadepedido();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value3() {
		return getQuantidaderecebido();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value4() {
		return getMercaderiaId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value5() {
		return getPedidoId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value6() {
		return getQuantidadePedido();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value7() {
		return getQuantidadeRecebido();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TabPedidoItemRecord
	 */
	public TabPedidoItemRecord() {
		super(jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM);
	}

	/**
	 * Create a detached, initialised TabPedidoItemRecord
	 */
	public TabPedidoItemRecord(java.lang.Long id, java.math.BigDecimal quantidadepedido, java.math.BigDecimal quantidaderecebido, java.lang.Long mercaderiaId, java.lang.Long pedidoId, java.math.BigDecimal quantidadePedido, java.math.BigDecimal quantidadeRecebido) {
		super(jooq.classesgeradas.tables.TabPedidoItem.TAB_PEDIDO_ITEM);

		setValue(0, id);
		setValue(1, quantidadepedido);
		setValue(2, quantidaderecebido);
		setValue(3, mercaderiaId);
		setValue(4, pedidoId);
		setValue(5, quantidadePedido);
		setValue(6, quantidadeRecebido);
	}
}
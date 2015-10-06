package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDaoImpl<T, I extends Serializable> implements
		GenericDao<T, I> {

	private Conexao conexao;
	
	public enum MatchMode { START, END, EXACT, ANYWHERE }

	public T save(T entity) {
		T saved = null;
		getEntityManager().getTransaction().begin();

		saved = getEntityManager().merge(entity);

		getEntityManager().getTransaction().commit();

		return saved;
	}

	public void remove(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(
				getEntityManager().contains(entity) ? entity
						: getEntityManager().merge(entity));
		getEntityManager().getTransaction().commit();

	}

	public T getById(Class<T> classe, I pk) {

		try {
			return getEntityManager().find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> getByField(String tabela, String campo, String valor) {

		try {
			return getEntityManager().createNativeQuery(
					"select * from " + tabela + " where " + valor)
					.getResultList();

		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> classe) {
		try {

			return getEntityManager().createQuery(
					"select o from " + classe.getSimpleName() + " o")
					.getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return null;
	}

	public EntityManager getEntityManager() {

		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> OpenQuery(String sql) {
		try {
			final Query query = getEntityManager().createNativeQuery(sql);
			List<Object[]> resultList = (List<Object[]>) query.getResultList();
			return resultList;

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return null;

	}

	
	/**
	 * Enconta uma entidade por uma de suas propriedades.
	 * 
	 * 
	 * @param clazz a classe entidade.
	 * @param propertyName nome da propriedade.
	 * @param value o valor de procura.
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String propertyName, Object value) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.where(cb.equal(root.get(propertyName), value));
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	
	
	/**
	 * Enconta entidades por uma propriedade string com espefifica combinação. A busca não é case-sensitive. 
	 * 
	 * @param clazz a classe da entidade.
	 * @param propertyName a propriedade string.
	 * @param value o valor de busca.
	 * @param matchMode o modo de combinação: EXACT, START, END, ANYWHERE.
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String propertyName, String value, MatchMode matchMode) {
		//convert the value String to lowercase
		value = value.toLowerCase();
		if (MatchMode.START.equals(matchMode)) {
			value = value + "%";
		} else if (MatchMode.END.equals(matchMode)) {
			value = "%" + value;
		} else if (MatchMode.ANYWHERE.equals(matchMode)) {
			value = "%" + value + "%";
		}
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.where( cb.like( cb.lower( root.<String>get(propertyName) ), value));
		
		return getEntityManager().createQuery(cq).getResultList();
	}
	
}
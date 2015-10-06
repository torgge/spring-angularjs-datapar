package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public abstract class GenericDaoImpl<T, I extends Serializable> implements
		GenericDao<T, I> {

	private Conexao conexao;

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

}
package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
 
public interface GenericDao<T, I extends Serializable> {
 
 public T save(T entity);
  
 public void remove(T entity);
  
 public T getById(Class<T> classe, I pk);

 public List<T> getByField(String tabela, String Campo, String valor);
  
 public List<T> getAll(Class<T> classe);
  
 public EntityManager getEntityManager();
  
 public  List<Object[]> OpenQuery(String sql);
}
package br.com.siscurriculum.DAO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {
	private static final long serialVersionUID = 8422517898877133523L;
	
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	
	public int  gerarId() {
		Integer ret = 0;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			// GERAR CODIGO ORACLE
			 BigDecimal cod = (BigDecimal)em.createNativeQuery("SELECT to_number(gera_id) FROM dual").getSingleResult(); 
			 ret  = cod.intValue();
			// GERAR CODIGO MYSQL
			//StoredProcedureQuery query = em.createStoredProcedureQuery("prox_id").registerStoredProcedureParameter("vid",Integer.class,ParameterMode.OUT);
			//query.execute();
			//ret = (Integer)query.getOutputParameterValue("vid");
			// System.out.println("cod id == " + ret);
			em.close();			
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
			ret = 0; // ocorreu um erro
			if (em.isOpen()) {
				em.close();
			}
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return ret;
	}
	
	
	public int  adicionar(T t) {
		//new JPAUtility();
		int ret = 0;
		//EntityManager em = JPAUtility.getEntityManager();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			em.close();
			ret = 1;
		} catch (Exception e) {
			ret = 5; // ocorreu um erro
			if (em.isOpen()) {
				em.close();
			}
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return ret;	
			
	}
	public int remover(T t) {
		//new JPAUtility();
		int ret = 0;
		//EntityManager em = JPAUtility.getEntityManager();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			em.getTransaction().begin();
			em.remove(em.merge(t));
			em.flush();
			em.getTransaction().commit();
			em.close();

			ret = 1;
		} catch (RollbackException r) {
			if (r.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				// exibe mensagem de insero com sucesso
				ret = 9;
			}
			if (em.isOpen()) {
				em.close();

			}
		} catch (Exception e) {
			ret = 5; // ocorreu um erro
			if (em.isOpen()) {
				em.close();

			}
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return ret;
	}
	
	
	public int atualizar(T t) {
		int ret = 0;
	    //new JPAUtility();
		//EntityManager em = JPAUtility.getEntityManager();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
				em.getTransaction().begin();
				em.merge(t);
				em.getTransaction().commit();
				em.close();
				ret=1;
		} catch (Exception e) {
			ret = 5; // ocorreu um erro
			if (em.isOpen()) {
				em.close();
			}
		} finally {
			if (em.isOpen()) {
				em.close();
	
			}
		}		
		return ret;
	}
	
	
	public List<T> listarTodos() {
		//new JPAUtility();
		//EntityManager em = JPAUtility.getEntityManager();
		List<T> lista = null;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
			query.select(query.from(classe));
			lista = em.createQuery(query).getResultList();
		} finally {
				em.close();	
		}
		return lista;
	}
	
	public List<T> listaTodosPaginada(int inicio, int quantidade) {
		//new JPAUtility();
		//EntityManager em = JPAUtility.getEntityManager();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		List<T> lista = null;
		try {
		   CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		   query.select(query.from(classe));
		   lista = em.createQuery(query).setMaxResults(quantidade).setFirstResult(inicio).getResultList();
		}
		finally {
			em.close();	
	     }
		return lista;
	}
	
	public int contarRegistros() {
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		int result = 0;
		try {
		   CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		   query.select(query.from(classe));
		   result = em.createQuery(query).getResultList().size();
		}finally {
			em.close();	
	     }
		return result;
	}
	
	public T getBean(int id) {
		EntityManager em = EntityManagerProvider.getInstance().createManager();
 	      T ret = (T) em.find(classe, id);
 		   em.close();	
		return ret;		
	}
	
	
	

}
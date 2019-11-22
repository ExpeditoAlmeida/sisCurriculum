package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);

	static UsuarioDAO db;

	public static UsuarioDAO getInstance() {
		if (db == null) {
			db = new UsuarioDAOImpl();
		}
		return db;
	}

	@Override
	public Usuario getBean(String nome) throws Exception{
		Usuario usuario = new Usuario();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {						
			 TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c WHERE c.nome = :nome", Usuario.class);
			 usuario = query.setParameter("nome", nome).getSingleResult();
		} catch (Exception e) {
			// e.printStackTrace();
		}finally {
			em.close();
		}
		return usuario;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarCbx() throws Exception{
		List<Usuario> lista = new ArrayList<Usuario>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
			   // HOUVE A NECESSIDADE DE CRIAR UM CONSTRUTOR (ID,NOME) PARA REALIZAR ESSA OPERAÇÃO  
		      Query query = em.createQuery("SELECT NEW Usuario(c.id, c.nome) from Usuario c");
			  lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;	
	} 
	
	@Override
	public int inserir (Usuario usuario) throws Exception{
		int ret = 0;
		try {
			usuario.setSenha(FuncsUtils.getInstance().encriptar(usuario.getSenha()));
			dao.adicionar(usuario);
			ret =1;
		} catch (Exception e) {
			//System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int alterar (Usuario usuario) throws Exception{
		int ret = 0;
		try {
			dao.atualizar(usuario);
			ret =1;
		} catch (Exception e) {
			//System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public int remover (Usuario usuario) throws Exception{
		int ret = 0;
		try {
			dao.remover(usuario);
			ret =1;
		} catch (Exception e) {
			//System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}

	public static void main(String[] args) throws Exception {

		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		
		Usuario u0 = new Usuario();
		u0.setNome("User");
		u0.setCpf("1231231231");
		u0.setEmail("a@a");
		u0.setSenha("123");		
		dao.inserir(u0);
		
		System.out.println("ok");
//		  c = dao.getBean("012704202224", "abraao.farias@tre-am.jus.br");
//		  System.out.println(c.getNome());

		/*
		 * for (Usuario c : dao.listar()) { System.out.println(c.getNome());
		 * 
		 * } Usuario a = new Usuario(); //a = dao.getBean(1);
		 * System.out.println(a.getNome());
		 * 
		 * System.out.println("DONE!!");
		 */

	}
}

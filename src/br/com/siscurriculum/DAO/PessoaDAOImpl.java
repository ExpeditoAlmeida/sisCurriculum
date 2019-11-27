package br.com.siscurriculum.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.siscurriculum.dominio.Pessoa;

public class PessoaDAOImpl implements PessoaDAO {

	private DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);

	static PessoaDAO db;

	public static PessoaDAO getInstance() {
		if (db == null) {
			db = new PessoaDAOImpl();
		}
		return db;
	}

	@Override
	public List<Pessoa> listarPorIdPessoa(int id) throws Exception {
		List<Pessoa> lista = null;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Pessoa> query = em.createQuery("SELECT c FROM Pessoa c WHERE c.id = ?1", Pessoa.class);
			lista = query.setParameter(1, id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Pessoa getBean(String email) throws Exception {
		Pessoa pessoa = new Pessoa();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Pessoa> query = em.createQuery("SELECT c FROM Pessoa c WHERE c.email = :email", Pessoa.class);
			pessoa = query.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return pessoa;
	}

	@Override
	public Pessoa getBean(int id) throws Exception {
		Pessoa pessoa = new Pessoa();
		try {
			pessoa = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}

	@Override
	public int adicionar(Pessoa pessoa) throws Exception {
		int ret = 0;
		try {
			pessoa.setSenha(FuncsUtils.getInstance().encriptar(pessoa.getSenha()));
			dao.adicionar(pessoa);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Pessoa pessoa) throws Exception {
		int ret = 0;
		try {
			pessoa.setSenha(FuncsUtils.getInstance().encriptar(pessoa.getSenha()));
			dao.atualizar(pessoa);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public int remover(Pessoa pessoa) throws Exception {
		int ret = 0;
		try {
			dao.remover(pessoa);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Pessoa> listarTodos() throws Exception {
		List<Pessoa> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static void main(String[] args) throws Exception {
		PessoaDAO dao = PessoaDAOImpl.getInstance();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Exp");
		int ret = dao.adicionar(pessoa);
		System.out.println("ret" + ret);

	}

}

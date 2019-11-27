package br.com.siscurriculum.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.siscurriculum.dominio.Educacao;

public class EducacaoDAOImpl implements EducacaoDAO {

	private DAO<Educacao> dao = new DAO<Educacao>(Educacao.class);

	static EducacaoDAO db;

	public static EducacaoDAO getInstance() {
		if (db == null) {
			db = new EducacaoDAOImpl();
		}
		return db;
	}

	@Override
	public Educacao getBean(int id) throws Exception {
		Educacao Educacao = new Educacao();
		try {
			Educacao = dao.getBean(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Educacao;
	}

	@Override
	public int adicionar(Educacao Educacao) throws Exception {
		int ret = 0;
		try {
			dao.adicionar(Educacao);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Educacao Educacao) throws Exception {
		int ret = 0;
		try {
			dao.atualizar(Educacao);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public int remover(Educacao Educacao) throws Exception {
		int ret = 0;
		try {
			dao.remover(Educacao);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Educacao> listarTodos() throws Exception {
		List<Educacao> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Educacao> listarPorIdPessoa(int id) throws Exception {
		List<Educacao> lista = null;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Educacao> query = em.createQuery("SELECT c FROM Educacao c WHERE c.pessoa.id = ?1",
					Educacao.class);
			lista = query.setParameter(1, id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static void main(String[] args) throws Exception {
		EducacaoDAO dao = EducacaoDAOImpl.getInstance();
		Educacao Educacao = new Educacao();

	}

}

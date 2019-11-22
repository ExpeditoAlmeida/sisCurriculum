package br.jus.tream.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Experiencias;

public class ExperienciasDAOImpl implements ExperienciasDAO {

	private DAO<Experiencias> dao = new DAO<Experiencias>(Experiencias.class);

	static ExperienciasDAO db;

	public static ExperienciasDAO getInstance() {
		if (db == null) {
			db = new ExperienciasDAOImpl();
		}
		return db;
	}

	@Override
	public Experiencias getBean(int id) throws Exception {
		Experiencias experiencias = new Experiencias();
		try {
			experiencias = dao.getBean(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return experiencias;
	}

	@Override
	public int adicionar(Experiencias experiencias) throws Exception {
		int ret = 0;
		try {
			dao.adicionar(experiencias);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Experiencias experiencias) throws Exception {
		int ret = 0;
		try {
			dao.atualizar(experiencias);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public int remover(Experiencias experiencias) throws Exception {
		int ret = 0;
		try {
			dao.remover(experiencias);
			ret = 1;
		} catch (Exception e) {
			// System.out.println("Ocorreu um ERRO " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Experiencias> listarTodos() throws Exception {
		List<Experiencias> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Experiencias> listarPorIdPessoa(int id) throws Exception {
		List<Experiencias> lista = null;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Experiencias> query = em.createQuery("SELECT c FROM Experiencias c WHERE c.pessoa.id = ?1",
					Experiencias.class);
			lista = query.setParameter(1, id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static void main(String[] args) throws Exception {
		ExperienciasDAO dao = ExperienciasDAOImpl.getInstance();
		Experiencias experiencias = new Experiencias();
		experiencias.setCargo("Estágiario");
//		Date dtEntrada = new SimpleDateFormat("dd/mm/yyyy").parse("01/02/2018");
//		experiencias.setDtEntrada(dtEntrada);
//		Date dtSaida = new SimpleDateFormat("dd/mm/yyyy").parse("31/01/2020");
//		experiencias.setDtSaida(dtSaida);
//		experiencias.setDescricaoAtribuicoes(
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbcccccccccccccccccccccccccccccccccccccccccccccccccc");
//		experiencias.setNomeEmpresa("Tribunal regional Eleitoral do Amazonas");
//		int ret = dao.adicionar(experiencias);
//		System.out.println("ret" + ret);

		for(Experiencias e: dao.listarPorIdPessoa(1)) {
			 System.out.println("teste mes: "+e.getPessoa().getNome()); 
		}
		
	}

}

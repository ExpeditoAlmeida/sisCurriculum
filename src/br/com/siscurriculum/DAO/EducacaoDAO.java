package br.com.siscurriculum.DAO;

import java.util.List;

import br.com.siscurriculum.dominio.Educacao;

public interface EducacaoDAO {

	public List<Educacao> listarTodos() throws Exception;

	public Educacao getBean(int id) throws Exception;

	public List<Educacao> listarPorIdPessoa(int id) throws Exception;

	public int adicionar(Educacao educacao) throws Exception;

	public int atualizar(Educacao educacao) throws Exception;

	public int remover(Educacao educacao) throws Exception;

}
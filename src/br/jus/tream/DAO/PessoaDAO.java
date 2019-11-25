package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Pessoa;

public interface PessoaDAO {

	public List<Pessoa> listarTodos() throws Exception;

	public Pessoa getBean(int id) throws Exception;
	
	public List<Pessoa> listarPorIdPessoa(int id) throws Exception;
	
	public Pessoa getBean(String email) throws Exception;

	public int adicionar(Pessoa pessoa) throws Exception;

	public int atualizar(Pessoa pessoa) throws Exception;

	public int remover(Pessoa pessoa) throws Exception;

}
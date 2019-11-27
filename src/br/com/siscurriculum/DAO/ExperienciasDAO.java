package br.com.siscurriculum.DAO;

import java.util.List;

import br.com.siscurriculum.dominio.Experiencias;

public interface ExperienciasDAO {

	public List<Experiencias> listarTodos() throws Exception;

	public Experiencias getBean(int id) throws Exception;

	public List<Experiencias> listarPorIdPessoa(int id) throws Exception;

	public int adicionar(Experiencias experiencias) throws Exception;

	public int atualizar(Experiencias experiencias) throws Exception;

	public int remover(Experiencias experiencias) throws Exception;

}
package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.ExperienciasDAO;
import br.jus.tream.DAO.ExperienciasDAOImpl;
import br.jus.tream.DAO.PessoaDAO;
import br.jus.tream.DAO.PessoaDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Experiencias;
import br.jus.tream.dominio.Pessoa;

@SuppressWarnings("serial")
@Namespace("/pessoa")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPessoa extends ActionSupport {
	private List<Pessoa> lstPessoa;
	private Pessoa pessoa;
	private List<Experiencias> lstExperiencias;
	private Experiencias experiencias;
	private BeanResult result;
	private final static PessoaDAO dao = PessoaDAOImpl.getInstance();
	private final static ExperienciasDAO daoExperiencias = ExperienciasDAOImpl.getInstance();
	private int id;

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/pessoa.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstPessoa = dao.listarTodos();
			this.lstExperiencias = daoExperiencias.listarTodos();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "printcurriculum", results = { @Result(name = "success", location = "/consultas/curriculum.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarCurriculum() {
		try {
			//this.experiencias = daoExperiencias.getBean(id);
			this.pessoa = dao.getBean(this.pessoa.getId());
			this.lstExperiencias = daoExperiencias.listarPorIdPessoa(pessoa.getId());

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstPessoa" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }
	// , interceptorRefs = @InterceptorRef("authStack")
	)
	public String listarJson() {
		try {
			this.lstPessoa = dao.listarTodos();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmPessoa.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.pessoa = dao.getBean(this.pessoa.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmPessoa.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadEleicao() {
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			beanResult.setRet(dao.adicionar(pessoa));
			if (beanResult.getRet() == 1) {
				beanResult.setMensagem(getText("inserir.sucesso"));
				beanResult.setType("success");
			} else {
				beanResult.setMensagem(getText("inserir.error"));
				beanResult.setType("error");
			}
		} catch (Exception e) {
			addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			// result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}

	@Action(value = "atualizar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtualizar() {
		BeanResult beanResult = new BeanResult();
		try {
			beanResult.setRet(dao.atualizar(this.pessoa));
			if (beanResult.getRet() == 1) {
				beanResult.setMensagem(getText("alterar.sucesso"));
				beanResult.setType("success");
			} else {
				beanResult.setMensagem(getText("alterar.error"));
				beanResult.setType("error");
			}
		} catch (Exception e) {
			addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}

	@Action(value = "remover", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }// , interceptorRefs =
																			// @InterceptorRef("authStack")
	)
	public String doRemover() {
		BeanResult beanResult = new BeanResult();
		beanResult.setRet(0);
		try {
			beanResult.setRet(dao.remover(this.pessoa));
			beanResult.setMensagem(getText("remover.sucesso"));
			beanResult.setType("success");
		} catch (Exception e) {
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
			// r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}

	public List<Pessoa> getLstPessoa() {
		return lstPessoa;
	}

	public void setLstPessoa(List<Pessoa> lstPessoa) {
		this.lstPessoa = lstPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static PessoaDAO getDao() {
		return dao;
	}

	public List<Experiencias> getLstExperiencias() {
		return lstExperiencias;
	}

	public void setLstExperiencias(List<Experiencias> lstExperiencias) {
		this.lstExperiencias = lstExperiencias;
	}

	public Experiencias getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(Experiencias experiencias) {
		this.experiencias = experiencias;
	}

}

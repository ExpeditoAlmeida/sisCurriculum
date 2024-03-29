package br.com.siscurriculum.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.com.siscurriculum.DAO.EducacaoDAO;
import br.com.siscurriculum.DAO.EducacaoDAOImpl;
import br.com.siscurriculum.DAO.ExperienciasDAO;
import br.com.siscurriculum.DAO.ExperienciasDAOImpl;
import br.com.siscurriculum.DAO.PessoaDAO;
import br.com.siscurriculum.DAO.PessoaDAOImpl;
import br.com.siscurriculum.dominio.BeanResult;
import br.com.siscurriculum.dominio.Educacao;
import br.com.siscurriculum.dominio.Experiencias;
import br.com.siscurriculum.dominio.Pessoa;

@SuppressWarnings("serial")
@Namespace("/pessoa")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPessoa extends ActionSupport {
	private List<Pessoa> lstPessoa;
	private List<Experiencias> lstExperiencias;
	private List<Educacao> lstEducacao;
	private Pessoa pessoa;
	private Experiencias experiencias;
	private Educacao educacao;
	private BeanResult result;
	private final static PessoaDAO dao = PessoaDAOImpl.getInstance();
	private final static ExperienciasDAO daoExperiencias = ExperienciasDAOImpl.getInstance();
	private final static EducacaoDAO daoEducacao = EducacaoDAOImpl.getInstance();
	private int id;

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/pessoa.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {

			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			this.lstPessoa = dao.listarPorIdPessoa(b.getId());
			this.lstExperiencias = daoExperiencias.listarPorIdPessoa(b.getId());
			this.lstEducacao = daoEducacao.listarPorIdPessoa(b.getId());

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
			// this.experiencias = daoExperiencias.getBean(id);
			this.pessoa = dao.getBean(this.pessoa.getId());
			this.lstExperiencias = daoExperiencias.listarPorIdPessoa(pessoa.getId());
			this.lstEducacao = daoEducacao.listarPorIdPessoa(pessoa.getId());

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
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			this.pessoa = dao.getBean(b.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCadUser", results = { @Result(name = "success", location = "/forms/frmUsuario.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadUser() {
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
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
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack")
	)
	public String doRemover() {
		BeanResult beanResult = new BeanResult();
		beanResult.setRet(0);
		try {
											
			experiencias = new Experiencias();				
			lstExperiencias = daoExperiencias.listarPorIdPessoa(pessoa.getId());									
			for (Experiencias experiencias : lstExperiencias) {
				int ret = daoExperiencias.remover(experiencias);
			}
			
			educacao = new Educacao();				
			lstEducacao = daoEducacao.listarPorIdPessoa(pessoa.getId());									
			for (Educacao educacao : lstEducacao) {
				int ret1 = daoEducacao.remover(educacao);
			}
			
			beanResult.setRet(dao.remover(this.pessoa));
			beanResult.setMensagem(getText("remover.sucesso"));
			beanResult.setType("success");
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
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

	public List<Experiencias> getLstExperiencias() {
		return lstExperiencias;
	}

	public void setLstExperiencias(List<Experiencias> lstExperiencias) {
		this.lstExperiencias = lstExperiencias;
	}

	public List<Educacao> getLstEducacao() {
		return lstEducacao;
	}

	public void setLstEducacao(List<Educacao> lstEducacao) {
		this.lstEducacao = lstEducacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Experiencias getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(Experiencias experiencias) {
		this.experiencias = experiencias;
	}

	public Educacao getEducacao() {
		return educacao;
	}

	public void setEducacao(Educacao educacao) {
		this.educacao = educacao;
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

}

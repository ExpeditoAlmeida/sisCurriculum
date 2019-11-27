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
import br.com.siscurriculum.dominio.BeanResult;
import br.com.siscurriculum.dominio.Educacao;
import br.com.siscurriculum.dominio.Pessoa;

@SuppressWarnings("serial")
@Namespace("/educacao")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionEducacao extends ActionSupport {
	private List<Educacao> lstEducacao;
	private Educacao educacao;
	private List<Pessoa> lstPessoa;
	private BeanResult result;
	private final static EducacaoDAO dao = EducacaoDAOImpl.getInstance();

	private int id;

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			this.lstEducacao = dao.listarPorIdPessoa(b.getId());

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmEducacao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {

			this.educacao = dao.getBean(this.educacao.getId());

		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmEducacao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadEleicao() {
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			Pessoa pessoa = new Pessoa();
			pessoa.setId(b.getId());
			educacao.setPessoa(pessoa);

			beanResult.setRet(dao.adicionar(educacao));

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
			beanResult.setRet(dao.atualizar(educacao));
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
			beanResult.setRet(dao.remover(educacao));
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

	public List<Educacao> getLstEducacao() {
		return lstEducacao;
	}

	public void setLstEducacao(List<Educacao> lstEducacao) {
		this.lstEducacao = lstEducacao;
	}

	public Educacao getEducacao() {
		return educacao;
	}

	public void setEducacao(Educacao educacao) {
		this.educacao = educacao;
	}

	public List<Pessoa> getLstPessoa() {
		return lstPessoa;
	}

	public void setLstPessoa(List<Pessoa> lstPessoa) {
		this.lstPessoa = lstPessoa;
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

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

import br.com.siscurriculum.DAO.ExperienciasDAO;
import br.com.siscurriculum.DAO.ExperienciasDAOImpl;
import br.com.siscurriculum.dominio.BeanResult;
import br.com.siscurriculum.dominio.Experiencias;
import br.com.siscurriculum.dominio.Pessoa;

@SuppressWarnings("serial")
@Namespace("/experiencias")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionExperiencias extends ActionSupport {
	private List<Experiencias> lstExperiencias;
	private Experiencias experiencias;
	private List<Pessoa> lstPessoa;
	private BeanResult result;
	private final static ExperienciasDAO dao = ExperienciasDAOImpl.getInstance();

	private int id;

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");

			this.lstExperiencias = dao.listarPorIdPessoa(b.getId());

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstExperiencias" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }
	// , interceptorRefs = @InterceptorRef("authStack")
	)
	public String listarJson() {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");

			this.lstExperiencias = dao.listarPorIdPessoa(b.getId());

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmExperiencias.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {

			this.experiencias = dao.getBean(this.experiencias.getId());

		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmExperiencias.jsp"),
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
			experiencias.setPessoa(pessoa);

			beanResult.setRet(dao.adicionar(experiencias));
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
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			Pessoa pessoa = new Pessoa();
			pessoa.setId(b.getId());
			experiencias.setPessoa(pessoa);

			beanResult.setRet(dao.atualizar(this.experiencias));

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
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			Pessoa pessoa = new Pessoa();
			pessoa.setId(b.getId());
			experiencias.setPessoa(pessoa);
			beanResult.setRet(dao.remover(this.experiencias));
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

	public List<Pessoa> getLstPessoa() {
		return lstPessoa;
	}

	public void setLstPessoa(List<Pessoa> lstPessoa) {
		this.lstPessoa = lstPessoa;
	}

}

package br.jus.tream.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.UsuarioDAO;
import br.jus.tream.DAO.UsuarioDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Usuario;

@SuppressWarnings("serial")
@Namespace("/usuario")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionUsuario extends ActionSupport {
	private Usuario usuario;
	private BeanResult result;
	private final static UsuarioDAO dao = UsuarioDAOImpl.getInstance();
	private int id;

//	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmUsuario.jsp"),
//			@Result(name = "error", location = "/pages/error.jsp") })
//	public String doFrmEditar() {
//		try {
//			this.usuario = dao.getBean(this.usuario.getId());
//		} catch (Exception e) {
//			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
//			return "error";
//		}
//		return "success";
//	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmUsuario.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") })
	public String frmCadEleicao() {
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			beanResult.setRet(dao.inserir(usuario));
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

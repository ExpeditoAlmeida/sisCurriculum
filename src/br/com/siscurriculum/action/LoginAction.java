package br.com.siscurriculum.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import br.com.siscurriculum.DAO.FuncsUtils;
import br.com.siscurriculum.DAO.PessoaDAOImpl;
import br.com.siscurriculum.dominio.Pessoa;

@Namespace("/login")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	SessionMap<String, Pessoa> sessionmap;
	private String username;
	private String userpass;
	private Pessoa pessoa;

	@Action(value = "frmSetup", results = { @Result(name = "success", location = "/frmLogin.jsp"),
			@Result(name = "error", location = "/pages/frmLogin.jsp") })
	public String setupLogin() {
		return "success";
	}

	@Action(value = "process", results = { @Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/frmLogin.jsp") })
	public String doProcess() {
		try { 
			Pessoa s = new Pessoa();
			s = PessoaDAOImpl.getInstance().getBean(this.username);
			if (s.getSenha().equals(FuncsUtils.getInstance().encriptar(userpass))) {
				sessionmap.put("login", s);
				addActionMessage(getText("login.sucesso"));
				return SUCCESS;
			} else {
				addActionError(getText("error.login"));
				return ERROR;
			}
		} catch (Exception e) {
			addActionError(getText("error.login"));
			return ERROR;
		}

	}

	@Action(value = "logout", results = { @Result(name = "success", location = "/frmLogin.jsp"),
			@Result(name = "error", location = "/login.jsp") })
	public String logout() {
		sessionmap.invalidate();
		return "success";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setSession(Map map) {
		sessionmap = (SessionMap) map;
		sessionmap.put("login", this.pessoa);
	}

	public SessionMap<String, Pessoa> getSessionmap() {
		return sessionmap;
	}

	public void setSessionmap(SessionMap<String, Pessoa> sessionmap) {
		this.sessionmap = sessionmap;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
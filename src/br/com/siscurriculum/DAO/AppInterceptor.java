package br.com.siscurriculum.DAO;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import br.com.siscurriculum.dominio.Pessoa;

@SuppressWarnings("serial")
public class AppInterceptor implements Interceptor {

	public void destroy() {
	}

	// called during interceptor initialization
	public void init() {
	}

	// put interceptor code here
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "success";

		/* VERIFICANDO A SESSÃO */
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Pessoa b = (Pessoa) session.getAttribute("login");
			if (b.getEmail() == null) {
				 System.out.println("Sessão expirada");
				result = "error";
			} else {
				 System.out.println("Sessão Ativa - titulo : " + b.getEmail());
				result = invocation.invoke();
			}
		} catch (Exception e) { 
			result = "loginNecessario";
			 System.out.println("Sessão expirada");
		}
		// ----------------------- DEPOIS DA ACTION
		// System.out.println("CustomInterceptor, after invocation.invoke()...
		// Result........>> " + result);
		return result;
	}

}
<%@page import="br.jus.tream.dominio.Pessoa"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<!--  <a class="navbar-brand" href="#">AnaJÉ</a> -->
	<a class="navbar-brand" href="#"> <img class="topmnu"
		src="${pageContext.request.contextPath}/images/sis.png" alt=""
		width="70" height="70">
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				>SisCurriculum<span
					class="sr-only">(current)</span></a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Cadastro</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/pessoa/frmCad">Cadastro Dados
						Pessoais</a> 
						<a class="dropdown-item"
						href="${pageContext.request.contextPath}/experiencias/frmCad">Cadastro de Experiências
						</a> 
						<a class="dropdown-item"
						href="${pageContext.request.contextPath}/pessoa/listar">Listar</a>
						
				</div>
			</li>
			
		</ul>

		<ul class="nav navbar-nav navbar-right">
             
             <li class="nav-item dropdown">
                <%
                Pessoa s = (Pessoa)session.getAttribute("login");
				  try{
				      if (!s.getEmail().equals("")){
			    %>
	             <a class="nav-link dropdown-toggle" href="http://example.com" id="sessionmnu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                <i class="fa fa-user-circle-o" aria-hidden="true"></i> <%=s.getEmail()%>
	             </a>
	            <div class="dropdown-menu" aria-labelledby="sessionmnu">
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/login/logout"><i class="fa fa-power-off" aria-hidden="true"></i>Sair</a>
	            </div>
	            
	            <%
				   }
			     }catch (Exception e){
			    	 %>
			    	 <a class="nav-link dropdown-toggle" href="http://example.com" id="sessionmnu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	               				Sessão não iniciada
			             </a>
			            <div class="dropdown-menu" aria-labelledby="sessionmnu">
			              <a class="dropdown-item" href="${pageContext.request.contextPath}/">Login</a>
			            </div> 
			    <%	 
			     }
				%>  
	          </li>
		
				 
			</ul>
	</div>
</nav>

<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<title>Orbit - Bootstrap 4 Resume/CV Template for Developers</title>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Responsive HTML5 Resume/CV Template for Developers">
<meta name="author" content="Xiaoying Riley at 3rd Wave Media">
<link rel="shortcut icon" href="favicon.ico">
<!-- Google Font -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto:400,500,400italic,300italic,300,500italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<!-- FontAwesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
	integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9"
	crossorigin="anonymous"></script>
<!-- Global CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- Theme CSS -->
<link id="theme-style" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div class="wrapper">
		<div class="sidebar-wrapper">
			<div class="contact-container container-block ">
				<ul class="list-unstyled contact-list">
					<li class="email"><i class="fas fa-envelope"></i>${pessoa.email}</li>
					<li class="phone"><i class="fas fa-phone"></i><a
						href="#">${pessoa.telefone1}</a></li>
					<li class="phone"><i class="fas fa-phone"></i><a
						href="#">${pessoa.telefone2}</a></li>
					<li class="website"><i class="fas fa-globe"></i><a
						href="#"
						target="_blank">portfoliosite.com</a></li>
					<li class="linkedin"><i class="fab fa-linkedin-in"></i><a
						href="#" target="_blank">linkedin.com/in/alandoe</a></li>
					<li class="github"><i class="fab fa-github"></i><a href="#"
						target="_blank">github.com/username</a></li>
					<li class="twitter"><i class="fab fa-twitter"></i><a
						href="#" target="_blank">@twittername</a></li>
				</ul>
			</div>
			<!--//contact-container-->
			<div class="education-container container-block">
				<h2 class="container-block-title">Education</h2>
				<div class="item">
					<h4 class="degree">MSc in Computer Science</h4>
					<h5 class="meta">University of London</h5>
					<div class="time">2011 - 2012</div>
				</div>
				<!--//item-->
				<div class="item">
					<h4 class="degree">BSc in Applied Mathematics</h4>
					<h5 class="meta">Bristol University</h5>
					<div class="time">2007 - 2011</div>
				</div>
				<!--//item-->
			</div>
			<!--//education-container-->

			<div class="languages-container container-block">
				<h2 class="container-block-title">Languages</h2>
				<ul class="list-unstyled interests-list">
					<li>English <span class="lang-desc">(Native)</span></li>
					<li>French <span class="lang-desc">(Professional)</span></li>
					<li>Spanish <span class="lang-desc">(Professional)</span></li>
				</ul>
			</div>
			<!--//interests-->


		</div>
		<!--//sidebar-wrapper-->

		<div class="main-wrapper">
			<section class="section summary-section">
				<h2 class="section-title">
					<span class="icon-holder"><i class="fas fa-user"></i></span>Nome :
					${pessoa.nome}
				</h2>

				<p>	<b>Endereço :</b> ${pessoa.logradouro}, <b>n° : </b>${pessoa.numero}
					<b>Bairro : </b>${pessoa.bairro} <b>Cidade : </b>${pessoa.cidade}-${pessoa.uf}</p>
			</section>
			<!--//section-->

			<section class="section experiences-section">
				<h2 class="section-title">
					<span class="icon-holder"><i class="fas fa-briefcase"></i></span>Experiences
				</h2>


				<div class="upper-row">


					<s:iterator value="lstExperiencias">
							
						<b>Nome Empresa :</b> <s:property value="nomeEmpresa" />
						</br>
						<b>Cargo :</b><s:property value="cargo" />
						</br>
						<b>Data Entrada :</b>
						<s:property value="%{getText('format.date',{dtEntrada})}" />
						<b>Data Saída :</b>
						<s:property value="%{getText('format.date',{dtSaida})}" /></br>

						<b>Atribuições :</b><s:property value="descricaoAtribuicoes" />
						</br>
						</br>
				

					</s:iterator>

					<div class="details"></div>
					<!--//details-->
				</div>
				<!--//item-->
			</section>
			<!--//section-->

		</div>
		<!--//main-body-->
	</div>

</body>
</html>

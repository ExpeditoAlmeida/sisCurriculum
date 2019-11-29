<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="card ">
	<div class="card-header text-white bg-dark">Dados Pessoais</div>
	<div class="card-body">

		<table id="tbeleicao" class="table table-sm table-hover">
			<thead>
				<tr>
					<th width="8%">Nome</th>
					<th width="2%">CPF</th>
					<th width="2%">DATA NASCIMENTO</th>
					<th width="2%">ESTADO CIVIL</th>
					<th width="3%">EMAIL</th>
					<th width="2%">TELEFONE</th>
					<th width="2%">TELEFONE</th>


					<th width="3%"><a href="frmCad" class="btn btn-sm btn-primary"
						role="button">Novo</a></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="lstPessoa">
					<tr id="tr${id}">
						<td><s:property value="nome" /></td>
						<td><s:property value="cpf" /></td>
						<td><s:property
								value="%{getText('format.date',{dtNascimento})}" /></td>
						<td><s:property value="estadoCivil" /></td>
						<td><s:property value="email" /></td>
						<td><s:property value="telefone1" /></td>
						<td><s:property value="telefone2" /></td>

						<td><a href="frmEditar?pessoa.id=${id}" id="idedit"
							class="btn btn-sm btn-warning" role="button"> <i
								class="fa fa-pencil-square-o" aria-hidden="true"></i>
						</a> <a href="#" id="excluir${id}" class="btn btn-sm btn-danger"
							role="button" data-record-id="${id}" data-record-nome="${nome}">
								<i class="fa fa-trash-o" aria-hidden="true"></i>
						</a> <a href="printcurriculum?pessoa.id=${id}" id="idedit"
							class="btn btn-sm
								btn-warning" role="button"> <i
								class="fa fa-print" aria-hidden="true"></i>
						</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div class="card-header text-white bg-dark">Endereço</div>
	<div class="card-body">



		<table id="tbeleicao" class="table table-sm table-hover">
			<thead>
				<tr>
					<th width="8%">CEP</th>
					<th width="2%">Cidade</th>
					<th width="2%">UF</th>
					<th width="2%">Logradouro</th>
					<th width="3%">Número</th>
					<th width="2%">Bairro</th>
					<th width="2%">Complemento</th>


					<th width="2%">Menu</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="lstPessoa">
					<tr id="tr${id}">
						<td><s:property value="cep" /></td>
						<td><s:property value="cidade" /></td>
						<td><s:property value="uf" /></td>
						<td><s:property value="logradouro" /></td>
						<td><s:property value="numero" /></td>
						<td><s:property value="bairro" /></td>
						<td><s:property value="complemento" /></td>

						<td><a href="frmEditar?pessoa.id=${id}" id="idedit"
							class="btn btn-sm btn-warning" role="button"> <i
								class="fa fa-pencil-square-o" aria-hidden="true"></i>
						</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>

	<div class="card-header text-white bg-dark">Experiência</div>
	<div class="card-body">

		<table id="tbeleicao" class="table table-sm table-hover">
			<thead>
				<tr>
					<th width="3%">Nome Empresa</th>
					<th width="2%">Cargo</th>
					<th width="2%">Data Entrada</th>
					<th width="2%">Data Saída</th>
					<th width="5%">Atribuições</th>
					<th width="2%">Menu</th>

				</tr>
			</thead>
			<tbody>
				<s:iterator value="lstExperiencias">
					<tr id="tr${id}">
						<td><s:property value="nomeEmpresa" /></td>
						<td><s:property value="cargo" /></td>
						<td><s:property value="%{getText('format.date',{dtEntrada})}" /></td>
						<td><s:property value="%{getText('format.date',{dtSaida})}" /></td>
						<td><s:property value="descricaoAtribuicoes" /></td>
						<td><a
							href="${pageContext.request.contextPath}/experiencias/frmEditar?experiencias.id=${id}"
							id="idedit" class="btn btn-sm btn-warning" role="button"> <i
								class="fa fa-pencil-square-o" aria-hidden="true"></i>
						</a></td>


					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>

	<div class="card-header text-white bg-dark">Formação</div>
	<div class="card-body">

		<table id="tbeleicao" class="table table-sm table-hover">
			<thead>
				<tr>
					<th width="3%">Nome da Instituição</th>
					<th width="2%">Nome do Curso</th>
					<th width="2%">Concluído</th>
					<th width="2%">Data Início</th>
					<th width="5%">Data Término</th>
					<th width="2%">Menu</th>

				</tr>
			</thead>
			<tbody>
				<s:iterator value="lstEducacao">
					<tr id="tr${id}">
						<td><s:property value="nomeInstituicao" /></td>
						<td><s:property value="nomeCurso" /></td>
						<td><s:property value="status" /></td>
						<td><s:property value="%{getText('format.date',{dtInicio})}" /></td>
						<td><s:property value="%{getText('format.date',{dtTermino})}" /></td>

						<td><a
							href="${pageContext.request.contextPath}/educacao/frmEditar?educacao.id=${id}"
							id="idedit" class="btn btn-sm btn-warning" role="button"> <i
								class="fa fa-pencil-square-o" aria-hidden="true"></i>
						</a></td>


					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>





	<jsp:include page="/javascripts.jsp" />
	<script type="text/javascript">

//$(document).ready(function() {
//	if($("#tbeleicao").length){
//		   $('#tbeleicao').dataTable( {
//		        "order": [[ 0, "des" ],[ 1, "des" ]]
//		   });
 //   }
//	
//});	

// CLICK DO BOTÃO EXCLUIR
$( "[id*='excluir']" ).click(function(event) {
    var data = $(event.delegateTarget).data();
	var id = data.recordId; 
	var nome = data.recordNome;
	swal({
		  title: 'Excluir?',
		  text: "Deseja excluir esse registro? (" + nome + ")",
		  icon: 'warning',
		  buttons: [true, "Sim excluir!"]
		}).then((result) => {
		  if (result) {
		       $.getJSON({
				  url: "remover?pessoa.id="+id
			   }).done(function( data ) {
			    	  if (data.ret==1){
			    		  $('#tr'+id).fadeOut(); 
			    		  swal("Remover", data.mensagem, data.type);
			    		  location.reload();
			    	  }
			    	  else
			    	  swal("Remover", "Ocorreu um erro ao remover", data.type);
				}).fail(function() {
					swal("Remover", "Ocorreu um erro ao remover", "error");
				});
		   }
		})
  });
  
  

</script>

	<%-- <jsp:include page="/mainfooter.inc.jsp" /> --%>
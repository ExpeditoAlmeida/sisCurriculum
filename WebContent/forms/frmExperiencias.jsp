<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header text-white bg-dark">Cadastro de
			Experiência</div>
		<div class="card-body">

			<form action="#" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
				<s:if test='experiencias.id != null'>
					<input type="hidden" id="id" name="experiencias.id"
						value="${experiencias.id}">
				</s:if>
					<label for="nome">nome:</label>
				<s:select label="Pessoa" headerKey="" headerValue="---Selecione---"
					list="lstPessoa" listKey="id" listValue="nome"
					name="experiencias.pessoa.id" theme="simple"
					cssClass="form-control" required="true" />
				<div class="invalid-feedback">Por favor, selecione seu nome.</div>

				<div class="form-row">
					<label for="nome">*Nome da Empresa:</label> <input type="text"
						class="form-control" id="nome" name="experiencias.nomeEmpresa"
						placeholder="Informe seu nome" value="${experiencias.nomeEmpresa}"
						maxlength="100" required>
					<div class="invalid-feedback">Por favor, informe o nome da
						empresa.</div>
				</div>

				<div class="form-row">
					<div class="col-md-4 mb-4">
						<label for="cargo">Cargo</label> <input type="text"
							class="form-control" id="cargo" name="experiencias.cargo"
							value="${experiencias.cargo}" placeholder="Informe seu cargo"
							maxlength="100" required>
						<div class="invalid-feedback">Por favor, informe seu cargo.</div>
					</div>


					<div class="col-md-4 mb-4">
						<label for="dtentrada">Data Entrada</label> <input type="date"
							class="form-control" name="experiencias.dtEntrada" id="dtentrada"
							value="<s:property value="%{getText('format.dtUSA',{experiencias.dtEntrada})}"/>"
							required>
						<div class="invalid-feedback">Por favor, informe a data de
							Entrada.</div>
					</div>

					<div class="col-md-4 mb-4">
						<label for="dtSaida">Data Saída</label> <input type="date"
							class="form-control" name="experiencias.dtSaida" id="dtSaida"
							value="<s:property value="%{getText('format.dtUSA',{experiencias.dtSaida})}"/>"
							required>
						<div class="invalid-feedback">Por favor, informe a data de
							saída.</div>
					</div>

				</div>

				<div class="mb-12">
					<label for="atribuicoes">Atribuições</label>
					<textarea class="form-control" id="atribuicoes"
						placeholder="Informe suas Atribuições"
						name="experiencias.descricaoAtribuicoes" maxlength="240" required>${experiencias.descricaoAtribuicoes}</textarea>
					<div class="invalid-feedback">Por favor, informe as suas
						respectivas Atribuicoes.</div>
				</div>

				<br />

				<button class="btn btn-success" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />


<script type="text/javascript">
$(document).ready(function() {
	 
	 $("#btnSave").click(function() {
		var URL = ""; 
		if ( $('#id').length ) { URL = "atualizar"; }
		else{ URL = "adicionar";  }	
		if (verificaDados()){
			 swal({
		         title: "Confirma ?",
		         text: "Confirma " + URL + "?",
		         icon: 'warning',
		         buttons: [true, "Salvar"]
		         }).then((result) => {
					if (result) {
						var frm = $("#form1").serialize();						
						$.getJSON({
							url: URL,
							data: frm
					    }).done(function( data ) {					    	
					    	if(data.ret==1)
					    		swal(URL, data.mensagem, "success").then((result) => {
					    			
					    			$(':input','#form1')
					    			  .not(':button, :submit, :reset, :hidden')
					    			  .val('')
					    			  .removeAttr('checked')
					    			  .removeAttr('selected');
					    		});
					    	else 
					    		swal(URL, data.mensagem, "error");
						}).fail(function() {
							swal("Adicionar", "Ocorreu um erro ao incluir", "error");
						});
				      } 
			   }); // -- FIM SWAL --
		   }else{
			   swal("Dados", "Verifique os campos obrigatórios ", "error");
		   }
	 	}); // -- FIM btnSave --
	 
});

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>

<!-- 
<jsp:include page="/mainfooter.inc.jsp" />
 -->
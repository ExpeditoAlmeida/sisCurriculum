<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header text-white bg-dark">Cadastro de
			experiência</div>
		<div class="card-body">
			<form action="#" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
				<s:if test='educacao.id != null'>
					<input type="hidden" id="id" name="educacao.id"
						value="${educacao.id}">
				</s:if>

				<div class="form-row">
					<label for="nome">Nome da Instituição:</label> <input type="text"
						class="form-control" id="nome" name="educacao.nomeInstituicao"
						placeholder="Informe a instituição"
						value="${educacao.nomeInstituicao}" maxlength="100" required>
					<div class="invalid-feedback">Por favor, informe o nome da
						instituição.</div>
				</div>


				<div class="form-row">

					<div class="col-md-12 mb-12">
						<label for="cargo">Nome do Curso</label> <input type="text"
							class="form-control" id="cargo" name="educacao.nomeCurso"
							value="${educacao.nomeCurso}"
							placeholder="Informe o nome do curso" maxlength="100" required>
						<div class="invalid-feedback">Por favor, informe nome do
							curso.</div>
					</div>

				</div>
				<div class="form-row">
					<div class="col-md-4 mb-4">

						<label for="inputTipo">Concluído</label>
						<s:select label="" class="form-control" name="educacao.status"
							headerKey="-1" headerValue="--Selecione--"
							list="#{'0':'não','1':'sim','2':'trancado','3':'outro'}"
							value="educacao.status" required="true" />
					</div>


					<div class="col-md-4 mb-4">
						<label for="dtInicio">*Data de início </label> <input type="date"
							class="form-control" name="educacao.dtInicio" id="dtInicio"
							value="<s:property value="%{getText('format.dtUSA',{educacao.dtInicio})}"/>"
							required>
						<div class="invalid-feedback">Por favor, informe a data de
							início.</div>
					</div>

					<div class="col-md-4 mb-4">
						<label for="dtTermino">*Data término</label> <input type="date"
							class="form-control" name="educacao.dtTermino" id="dtTermino"
							value="<s:property value="%{getText('format.dtUSA',{educacao.dtTermino})}"/>"
							required>
						<div class="invalid-feedback">Por favor, informe a data de
							término.</div>
					</div>

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
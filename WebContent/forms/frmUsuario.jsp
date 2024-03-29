<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header text-white bg-dark">Cadastre-se</div>
		<div class="card-body">

			<form action="#" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
			
				<div class="form-row">
					<label for="cpf">*Email</label> <input type="text"
						class="form-control" id="cpf" name="pessoa.email"
						placeholder="Digite aqui seu nome de usuário." value="${pessoa.email}"
						maxlength="100" required>
					<div class="invalid-feedback">Por favor, informe seu email.</div>
				</div>

				<div class="form-row">
					<label for="cpf">*Senha</label> <input type="password"
						class="form-control" id="cpf" name="pessoa.senha"
						placeholder="Digite aqui uma senha" value="${pessoa.senha}"
						maxlength="30" required>
					<div class="invalid-feedback">Por favor,informe sua senha.</div>
				</div>
				</br>
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
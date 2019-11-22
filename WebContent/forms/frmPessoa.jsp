<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header text-white bg-dark">Cadastro</div>
		<div class="card-body">

			<form action="#" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
				<s:if test='pessoa.id != null'>
					<input type="hidden" id="id" name="pessoa.id" value="${pessoa.id}" >
				</s:if>

				<div class="form-row">
					<label for="nome">*Nome:</label> <input type="text"
						class="form-control" id="nome" name="pessoa.nome"
						placeholder="Informe seu nome" value="${pessoa.nome}" maxlength="150" required>
					<div class="invalid-feedback">Por favor, informe seu nome.</div>
				</div>

				<div class="form-row">
					<div class="col-md-4 mb-4">
						<label for="cpf">CPF</label> <input type="text"
							class="form-control" id="cpf" name="pessoa.cpf"
							value="${pessoa.cpf}" placeholder="Informe seu CPF" maxlength="11" 
							onkeypress='return event.charCode >= 48 && event.charCode <= 57' 
							required>
						<div class="invalid-feedback">Por favor, informe seu CPF.</div>
					</div>


					<div class="col-md-4 mb-4">
						<label for="dtNascimento">Dsta Nascimento</label> <input
							type="date" class="form-control" name="pessoa.dtNascimento" 
							id="dtNascimento"
							value="<s:property value="%{getText('format.dtUSA',{pessoa.dtNascimento})}"/>"
							required>
						<div class="invalid-feedback">Por favor, informe a data de
							data de nascimento.</div>
					</div>

					<div class="col-md-4 mb-4">

						<label for="inputTipo">Estado Civil</label>
						<s:select label="" class="form-control" name="pessoa.estadoCivil"
							headerKey="-1" headerValue="--Selecione--"
							list="#{'0':'Solteiro','1':'Casado','2':'Viúvo','3':'Outro'}" value="pessoa.estadoCivil"
							required="true" />
					</div>

				</div>

				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="email">Email:</label> <input type="text"
							class="form-control" id="email" name="pessoa.email"
							placeholder="Informe seu nome" value="${pessoa.email}" maxlength="100" required>
						<div class="invalid-feedback">Por favor, informe seu email.</div>
					</div>

					<div class="col-md-3 mb-3">
						<label for="telefone1">Telefone:</label> <input type="text"
							class="form-control" id="telefone1" name="pessoa.telefone1"
							placeholder="Informe seu telefone" value="${pessoa.telefone1}" maxlength="15"
							required>
						<div class="invalid-feedback">Por favor, informe seu
							telefone.</div>
					</div>
					<div class="col-md-3 mb-3">
						<label for="telefone2">Telefone:</label> <input type="text"
							class="form-control" id="telefone2" name="pessoa.telefone2"
							placeholder="Informe outro telefone" value="${pessoa.telefone2}" maxlength="15"
							required>
						<div class="invalid-feedback">Por favor, informe seu
							telefone.</div>
					</div>
				</div>
 	 <hr/>
				<div class="form-row">
					<div class="col-md-4 mb-4">
						<label for="cep">CEP*:</label> <input type="text"
							class="form-control" id="cep" name="pessoa.cep"
							placeholder="Ex: 69070-000" value="${pessoa.cep}" maxlength="10" required>
						<div class="invalid-feedback">Por favor, informe um cep.</div>
					</div>

					<div class="col-md-4 mb-4">
						<label for="cidade">Cidade*:</label> <input type="text"
							class="form-control" id="cidade" name="pessoa.cidade"
							placeholder="Informe a cidade" value="${pessoa.cidade}" maxlength="50" required>
						<div class="invalid-feedback">Por favor, informe a cidade.</div>
					</div>
					<div class="col-md-4 mb-4">
						<label for="telefone2">UF:</label> <input type="text"
							class="form-control" id="telefone2" name="pessoa.uf"
							placeholder="Ex: AM" value="${pessoa.uf}" maxlength="2" required>
						<div class="invalid-feedback">Por favor, informe um estado.</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-4 mb-4">
						<label for="logradouro">Logradouro*:</label> <input type="text"
							class="form-control" id="logradouro" name="pessoa.logradouro"
							placeholder="Ex: Rua Floresta colorida"
							value="${pessoa.logradouro}" maxlength="150" required>
						<div class="invalid-feedback">Por favor, informe sua rua.</div>
					</div>

					<div class="col-md-4 mb-4">
						<label for="cidade">Número*:</label> <input type="text"
							class="form-control" id="cidade" name="pessoa.numero"
							placeholder="Informe a cidade" value="${pessoa.numero}" maxlength="20" required>
						<div class="invalid-feedback">Por favor, informe o número da
							casa.</div>
					</div>
					<div class="col-md-4 mb-4">
						<label for="bairro">Bairro*</label> <input type="text"
							class="form-control" id="bairro" name="pessoa.bairro"
							placeholder="Ex: AM" value="${pessoa.bairro}" maxlength="50" required>
						<div class="invalid-feedback">Por favor, informe seu bairro.</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-12 mb-12">
						<label for="complemento">Complemento (Opcional):</label> <input
							type="text" class="form-control" id="complemento"
							name="pessoa.complemento"
							placeholder="Ex: Bloco 77, Apartamento 34 , Próximo a feira Brasil"
							value="${pessoa.complemento}" maxlength="100">
						
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
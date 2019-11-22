<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header text-white bg-dark">Cadastrar Unidade de Medida:</div>
		<div class="card-body">

			<form action="#" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				
				
				<div class="form-row">
					<label for="sigla">*Sigla:</label> 
					<input type="text" class="form-control" id="sigla" name=""	placeholder="Informe uma Sigla" value="" required>
					<div class="invalid-feedback">Por favor, informe uma sigla.</div>
				</div>
				<br />

				<div class="form-row">
					<label for="descricao">*Descrição:</label> 
					<input type="text" class="form-control" id="descricao" name=""	placeholder="Informe uma descrição" value="" required>
					<div class="invalid-feedback">Por favor, informe uma descricao.</div>
				</div>
				<br>
				
				
				<button class="btn btn-success" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>
<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">

</script>
<jsp:include page="/mainfooter.inc.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="ISO-8859-1">
<title>Sistema JSP</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
.danger {
	border: 1px solid red;
}
</style>
</head>
<body>

	<header>
		<nav class="navbar navbar-dark  bg-dark flex-md-nowrap p-2">
			<a class="navbar-brand col-sm-3 col-md-2 mr-0"
				href="acessopermitido.jsp">Sistema JSP</a>
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a class="nav-link"
					href="acessopermitido.jsp"><i class="fa fa-home"
						aria-hidden="true">Home</i></a></li>
			</ul>
			<input class="form-control form-control-dark w-100" type="text"
				placeholder="Search" aria-label="Search">
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a class="nav-link"
					href="/curso-jsp-projeto">Sair</a></li>
			</ul>
		</nav>
	</header>

	<section class="p-3 mb-2 bg-secondary text-white">
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<p class="d-flex justify-content-center">Cadastro de Telefones</p>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<c:forEach items="${msg}" var="msgLoop">
				<c:if test="${podeInserir}">
					<div class="alert alert-success mt-3 text-center" role="alert">
						<p>${msgLoop}</p>
					</div>
				</c:if>
				<c:if test="${ podeInserir == false}">
					<div class="alert alert-danger mt-3 text-center" role="alert">
						<p>${msgLoop}</p>
					</div>
				</c:if>
				
			</c:forEach>
			<div class=" row justify-content-center mt-4" align="center">

				<form action="CadastroTelefones" method="post" class="w-100 p-3"
					id="formL">

					<input type="hidden" readonly="readonly"
						placeholder="Código do Usuário" id="id" name="id"
						value="${userEscolhido.id}">
					<label for="nome"> Usuário </label>
					<input type="text" readonly="readonly" class="form-control text-center bg-dark text-white "
						placeholder="Usuário*" id="nome" name="nome"
						value="${userEscolhido.nome}"> 
					
					<input type="text"
						class="form-control text-center bg-dark text-white  mt-3" placeholder="Número*" id="numero" name="numero"
						value="${fone.numero}"><span id="erro"></span> 
			
					
					<select style="text-align-last: center" class="text-center form-control bg-dark text-white mx-auto mt-3" name="tipo" id="tipo">
						<option disabled selected >Marque um tipo*</option>
						<option>Celular</option>
						<option>Telefone</option>
						<option>Casa</option>
						<option>Recado</option>
					</select> 
					<span id="erro1"></span>
					
					<br>
					<button style="margin-top: 10px; width: 48%;" type="submit"
						class="btn btn-success">Salvar</button>
					<input type="submit" id="reset"
						style="margin-top: 10px; width: 48%;" class="btn btn-danger"
						value="Cancelar" />

				</form>
			</div>

		</div>

	</section>
	<section class="mt-5">
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${telefones}" var="fone">
					<tr>
						<td scope="row"><c:out value="${fone.id}"></c:out></td>
						<td scope="row"><c:out value="${fone.numero}"></c:out></td>
						<td scope="row"><c:out value="${fone.tipo}"></c:out></td>
						<td><a title="Delete"
							class="text-decoration-none text-danger text-justify "
							href="CadastroTelefones?acao=delete&fone=${fone.id}"><i
								class="fa fa-trash" aria-hidden="true"></i></a></td>
					</tr>

				</c:forEach>
			</tbody>

		</table>

	</section>
	<script type="text/javascript">
		document.getElementById("reset").addEventListener("click", reset);
		function reset(e) {

			let form = document.getElementById('formL')
			form.action = "CadastroUsuario?acao=resetar";
			form.submit();
		}

		document.getElementById("formL").addEventListener("submit",
				validarCampos);
		function validarCampos(e) {

			let elTelefone = document.getElementById("numero");
			let elTipo = document.getElementById("tipo");

			if (elTelefone.value != "" && elTipo.value != "") {
				return true;
			} else {
				e.preventDefault();
				if (elTelefone.value == "") {
					elTelefone.classList.add("danger");
					document.getElementById("erro").innerHTML = "Campo obrigatório";

				}else if(elTipo.value == "") {
					elTipo.classList.add("danger");
					document.getElementById("erro1").innerHTML = "Campo obrigatório";
				}
			}

		}
	</script>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>



</body>
</html>
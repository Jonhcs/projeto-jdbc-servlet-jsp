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
					<p class="d-flex justify-content-center">Cadastro de Usuário</p>
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

				<form enctype="multipart/form-data" action="CadastroUsuario" method="post" class="w-100 p-3" id="formL">

					<input type="hidden" placeholder="Código" id="id" name="id"
						value="${user.id}"> <input type="text"
						class="form-control" placeholder="Nome" id="nome" name="nome"
						value="${user.nome}"><span id="erro"></span> <input
						style="margin-top: 10px" type="text" class="form-control"
						placeholder="Login" id="login" name="login" value="${user.login}"><span
						id="erro1"></span> <input style="margin-top: 10px" type="password"
						class="form-control" placeholder="Senha" id="senha" name="senha"
						value="${user.password}"><span id="erro2"></span> <input
						type="text" class="form-control mt-2" placeholder="Telefone"
						id="telefone" name="telefone" value="${user.telefone}"><span
						id="erro3"></span><input type="text" class="form-control mt-2"
						placeholder="Cep" id="cep" name="cep" value="${user.cep}"><span
						id="erro4"></span> <input type="text" class="form-control mt-2"
						placeholder="Rua" id="rua" name="rua" value="${user.rua}">
					<input type="text" class="form-control mt-2" placeholder="Bairro"
						id="bairro" name="bairro" value="${user.bairro}"> <input
						type="text" class="form-control mt-2" placeholder="Cidade"
						id="cidade" name="cidade" value="${user.cidade}"> <input
						type="text" class="form-control mt-2" placeholder="UF" id="uf"
						name="uf" value="${user.uf}"> <input type="text"
						class="form-control mt-2" placeholder="Ibge" id="ibge" name="ibge"
						value="${user.ibge}"> 
						<input type="file"
						class="form-control mt-2" id="foto" name="foto"
						value="Foto">
						<input type="file"
						class="form-control mt-2" id="pdf" name="pdf"
						value="Pdf">
						
						<br>
					<button style="margin-top: 10px; width: 48%;" type="submit"
						class="btn btn-success">Salvar</button>
					<input type="submit"
						id="reset"
						style="margin-top: 10px; width: 48%;"
						class="btn btn-danger" value="Cancelar" />

				</form>
			</div>

		</div>

	</section>
	<section class="mt-5">
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Login</th>
					<th scope="col">Nome</th>
					<th scope="col">Foto</th>
					<th scope="col">Pdf</th>
					<th scope="col">Telefone</th>
					<th scope="col">Cidade</th>
					<th scope="col">Deletar</th>
					<th scope="col">Editar</th>
					<th scope="col">Telefones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td scope="row"><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.login}"></c:out></td>
						<td><c:out value="${user.nome}"></c:out></td>
						
						<td><a href="CadastroUsuario?acao=download&tipo=imagem&user=${user.id}"> <img alt="" src='<c:out value="${user.tempFoto}"></c:out>'  width="50px" height="50px"> </a>  </td>
						<td><a href="CadastroUsuario?acao=download&tipo=pdf&user=${user.id}"> PDF </a>  </td>
						
						
						<td><c:out value="${user.telefone}"></c:out></td>
						<td><c:out value="${user.cidade}"></c:out></td>
						<td><a title="Delete"
							class="text-decoration-none text-danger text-justify "
							href="CadastroUsuario?acao=delete&user=${user.id}"><i
								class="fa fa-trash" aria-hidden="true"></i></a></td>
						<td><a title="Editar"
							class="text-decoration-none text-secundário"
							href="CadastroUsuario?acao=editar&user=${user.id}"><i
								class="fa fa-pencil-square" aria-hidden="true"></i></a></td>
								
						<td><a title="Editar"
							class="text-decoration-none text-secundário text-white"
							href="CadastroTelefones?acao=addfones&user=${user.id}"><i class="fa fa-phone-square" aria-hidden="true"></i></a></td>
						<td></td>
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

			let elNome = document.getElementById("nome");
			let elLogin = document.getElementById("login");
			let elSenha = document.getElementById("senha");
			let elTelefone = document.getElementById("telefone");
			if (elNome.value != "" && elLogin.value != ""
					&& elSenha.value != "" && elTelefone.value != "") {
				return true;
			} else {
				e.preventDefault();
				if (elNome.value == "") {
					elNome.classList.add("danger");
					document.getElementById("erro").innerHTML = "Campo obrigatório";

				} else if (elLogin.value == "") {
					elLogin.classList.add("danger");
					document.getElementById("erro1").innerHTML = "Campo obrigatório";

				} else if (elSenha.value == "") {
					elSenha.classList.add("danger");
					document.getElementById("erro2").innerHTML = "Campo obrigatório";

				} else if (elTelefone.value == "") {
					elTelefone.classList.add("danger");
					document.getElementById("erro3").innerHTML = "Campo obrigatório";

				}
			}

			return true;

		}

		document.getElementById("cep").addEventListener("blur", inserirCep);
		function inserirCep() {
			let cep = $("#cep").val();
			
			$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
				
                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#uf").val(dados.uf);
                    $("#ibge").val(dados.ibge);
                } //end if.
                else {
                	$("#cep").val('');
                	$("#rua").val('');
                    $("#bairro").val('');
                    $("#cidade").val('');
                    $("#uf").val('');
                    $("#ibge").val('');
                    
                 
                    alert("CEP não encontrado.");
                }
            });
			
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
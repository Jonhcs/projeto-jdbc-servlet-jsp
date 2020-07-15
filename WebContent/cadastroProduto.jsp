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
</head>
<body>

	<header>

		<nav class="navbar navbar-dark  bg-dark flex-md-nowrap p-2">
			<a class="navbar-brand col-sm-3 col-md-2 mr-0"
				href="acessopermitido.jsp">Sistema JSP</a> 
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a class="nav-link" href="acessopermitido.jsp"><i class="fa fa-home"
						aria-hidden="true">Home</i></a>
				</li>
			</ul>
			<input
				class="form-control form-control-dark w-100" type="text"
				placeholder="Search" aria-label="Search">
				
			
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a class="nav-link" href="/curso-jsp-projeto">Sair</a>
				</li>
			</ul>
		</nav>
	</header>
	<section class="p-3 mb-2 bg-secondary text-white" > 
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<p class="d-flex justify-content-center">Cadastro de Produto</p>
				</div>
				
			</div>
		</div>

	</section>
	<section>
		<div class="container">


			<c:forEach items="${msg}" var="msgLoop">
				<div class="alert alert-danger mt-3 text-center" role="alert">
					<p>${msgLoop}</p>
				</div>
			</c:forEach>


			<div class=" row justify-content-center mt-4" align="center">


				<form action="CadastroProduto" method="post" id="form">

					<input type="hidden" placeholder="Código" id="pid" name="pid"
						value="${produto.pid}"> 
					
					<input type="text"
						class="form-control" placeholder="Nome" id="nome" name="nome"
						value="${produto.nome}"> 
					
					<input style="margin-top: 10px"
						type="text" class="form-control" placeholder="Quantidade" id="quantidade"
						name="quantidade" value="${produto.quantidade}"> 
					
					<input
						style="margin-top: 10px" type="text" class="form-control"
						placeholder="Valor" id="valor" name="valor"
						value="${produto.valor}">

					<button style="margin-top: 10px; width: 48%;" type="submit"
						class="btn btn-success">Salvar</button>
					<button
						onclick="document.getElementById('form').action = 'CadastroProduto?acao=reset';"
						style="margin-top: 10px; width: 48%;" type="submit"
						class="btn btn-danger">Cancelar</button>

				</form>
			</div>

		</div>

	</section>
	<section class="mt-5">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nome</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor</th>
					<th scope="col">Deletar</th>
					<th scope="col">Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td scope="row"><c:out value="${produto.pid}"></c:out></td>
						<td><c:out value="${produto.nome}"></c:out></td>
						<td><c:out value="${produto.quantidade}"></c:out></td>
						<td><c:out value="${produto.valor}"></c:out></td>
						<td><a title="Delete"
							class="text-decoration-none text-danger ml-5"
							href="CadastroProduto?acao=delete&produto=${produto.pid}"><i
								class="fa fa-trash" aria-hidden="true"></i></a></td>
						<td><a title="Editar"
							class=" text-decoration-none text-secundário"
							href="CadastroProduto?acao=editar&produto=${produto.pid}"><i
								class="fa fa-pencil-square" aria-hidden="true"></i></a></td>
					</tr>

				</c:forEach>
			</tbody>

		</table>

	</section>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
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
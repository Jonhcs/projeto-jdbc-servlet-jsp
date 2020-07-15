<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
#usuarioadd {
	width: 150px;
	height: 150px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-dark  bg-dark flex-md-nowrap p-2">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0"
			href="acessopermitido.jsp">Sistema JSP</a> <input
			class="form-control form-control-dark w-100" type="text"
			placeholder="Search" aria-label="Search">
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="/curso-jsp-projeto">Sair</a>
			</li>
		</ul>
	</nav>
	<section>
		<div class="container">
			<div class="text-center">
				<p>Seja Bem Vindo ao Sistema JSP com Servlet</p>
			</div>
			<div class="row d-flex justify-content-center">

				<div>
					<a href="CadastroUsuario?acao=listarTodos"><img id="usuarioadd"
						title="Adicionar Usuário" alt="" src="resources/image/usuario.png">
					</a>
				</div>
				
			</div>
			<div class="container border-top border-info m-4"></div>
			<div class="row d-flex justify-content-center">

				<div>
					<a href="CadastroProduto?acao=listarTodos"><img id="usuarioadd"
						title="Adicionar Produto" alt="" src="resources/image/adicionar-produto.png">
					</a>
				</div>
				
			</div>
		</div>
		


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
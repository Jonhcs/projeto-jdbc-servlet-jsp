<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	background-image: url("resources/image/linear-gradient.png");
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

#padding-20 {
	padding: 30px;
}

#shadow {
	-webkit-box-shadow: 4px -1px 90px 10px rgba(0, 0, 0, 1);
	-moz-box-shadow: 4px -1px 90px 10px rgba(0, 0, 0, 1);
	box-shadow: 4px -1px 90px 10px rgba(0, 0, 0, 1);
}
</style>
</head>
<body>
	<div class="container " style="margin-top: 15%;">
		<div class="row justify-content-center">
			<div id="shadow" class=" w-50 p-3  mb-2 bg-secondary text-white rounded-bottom">
				<div class="text-center">
					<h1>
						<i class="fa fa-address-book-o" aria-hidden="true"></i> Sistema
						JSP & Servlet
					</h1>
					<div class="w-100 p-3 border-top border-warning"></div>
				</div>
				<form method="post" action="LoginSevlet" id="padding-20">
					<div class="form-group">
						<label for="login">Login: </label> <input type="text"
							class="form-control" id="login" name="login"
							aria-describedby="login" placeholder="Digite seu login">
					</div>
					<div class="form-group">
						<label for="senha">Senha: </label> <input type="password"
							class="form-control" id="senha" name="senha" placeholder="Senha">
					</div>
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
						<label class="form-check-label" for="exampleCheck1">Check
							me out</label>
					</div>
					<div>
						<button type="submit" class="btn btn-dark w-100 mt-2">Acessar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

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
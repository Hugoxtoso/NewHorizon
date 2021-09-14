<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Classes.Clientes"%>
<%@ page import="Classes.UploadFile"%>


<%
String nome = upload.getParameter(request, "nome");
String sobreNome = upload.getParameter(request, "nomesobre");
String email = upload.getParameter(request, "email");
String tel = upload.getParameter(request, "tel");
String dtn = upload.getParameter(request, "dtn");
String cpf = upload.getParameter(request, "cpf");
String senha = upload.getParameter(request, "senha");
String imagem = upload.getParameter(request, "imagem");

Clientes cliente = new Clientes(nome, sobreNome, email, tel, cpf, dtn, senha, imagem);

UploadFile arquivoImagem = new UploadFile("arquivo");

%>
<%if(arquivoImagem.doUpload(request, application)){

%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="wid  th=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<title>Descrição Cliente</title>
</head>
<body>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>


	<div
		Style="background-color: #69d1ce; padding: 2%; height: 5%; box-shadow: 0px 2px 2px #488a88;">
		<h2 Style="text-align: center;">Detalhes do cliente</h2>
	</div>
	<table class="table table-striped"
		style="width: 50%; margin-left: 25%; margin-right: 25%; margin-top: 3%;">
		<tr>
			<th><label for="nome">Nome:</label></th>
			<td><%=cliente.getNome()%></td>
		</tr>
		<tr>
			<th><label for="sobreNome">Sobrenome::</label></th>
			<td><%=cliente.getSobreNome()%></td>
		</tr>
		<tr>
			<th><label for="email">Email:</label></th>
			<td><%=cliente.getEmail()%></td>
		</tr>
		<tr>
			<th><label for="telefone">Telefone:</label></th>
			<td><%=cliente.getTel()%></td>
		</tr>
		<tr>
			<th><label for="dtn">Data de nascimento:</label></th>
			<td><%=cliente.getDtn()%></td>
		</tr>
		<tr>
			<th><label for="cpf">CPF:</label></th>
			<td><%=cliente.getCpf()%></td>
		</tr>
		<tr>
			<th><label for="imagem">Imagem:</label></th>
			<td><img src="<%=arquivoImagem.getNomeArquivo()%>"></td>
		</tr>
		<tr>
			<th><label for="senha">Senha:</label></th>
			<td><%=cliente.getSenha()%></td>
		</tr>
	</table>
	<div style="text-align: center; padding-bottom: 3%">
		<a href="cadastrocliente.jsp"><button type="button"
				class="btn btn-outline-primary">Voltar</button></a>
	</div>
</body>
</html>
<%
}
else{%>
	<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">

	<title>Remover Produto</title>
	<link rel="stylesheet" href="css.css" />
	</head>

	<body style="background-color: #3d3d3d;">
		<div
			Style="margin-top: 15%; background-color: #dc3545; padding:; height: 20%; box-shadow: 0px 2px 2px #dc3545; text-align: center; font-size: 20px; color: black;">
			<b style="font-size: 40px;">Esta imagem já está vinculada a uma conta, por favor insira uma nova imagem!<font size="2"> (sabe nem cadastrar direito pqp)</font></b><br>
			
			</div>
		<div style="text-align: center; padding-bottom: 3%; margin-top: 20%;">
			<a href="cadastrocliente.jsp"><button type="button"
					class="btn btn-danger" style="color: black;">Pagina inicial</button></a>
		</div>
	</body>
<%	
}
%>

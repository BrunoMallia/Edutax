<%@page import="br.com.edutex.logic.Empresa"%>
<%@page import="br.com.edutex.logic.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.edutex.form.UsuarioForm"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="/header/header.jsp" />
<script src="js/relatorio.js"></script>

</head>
<body>
	<header class="header_home">
	<div class="header_width">
		<jsp:include page="/header/cabecalho.jsp" />
		<jsp:include page="/header/aba_menu.jsp" />
	</div>
	</header>
	<div class="main_container_default">
		<div class="container_default">
			<fieldset>
				<legend>Gerar Relat&oacute;rios</legend>
				<div class="">
					<label>Tipo de Relat&oacute;rio</label> 
					<select id="nota_fiscal">
						<option value="nada">Selecione uma op&ccedil;&atilde;o</option>
						<option value="metas">Total de metas</option>
						<option value="metas_falhas">N&uacute;meros de metas com
							falhas</option>
						<option value="atacadista">Base de c&aacute;lculo
							atacadista</option>
						<option value="aliquita">Diferen&ccedil;a de
							al&iacute;quota</option>
					</select> 
					
				</div>
				<div id="exemplo" class="">
					<label>Tipo</label> <select>
						<option>Selecione uma op&ccedil;&atilde;o</option>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select><br />
				</div>
				<div id="empresas" class=""> 
					<span> <label>Empresas</label> <select>
							<% 
							UsuarioForm usuForm = new UsuarioForm();
				ArrayList<Empresa> e = (ArrayList<Empresa>)usuForm.buscaListaEmpresa();
				int count = 0;
				for (int i = 0; i < e.size(); i++) {
					if (e.get(i) != null
						&& e.get(i).getTpStatus().getCdStatus() != 2
						&& e.get(i).getNmEmpresa() != null && !e.get(i).getNmEmpresa().isEmpty()) {
						%>
							<option value="<%=e.get(i).getCdcnpj()%>"><%=e.get(i).getNmEmpresa() %>
							</option>
							<%
						count++;
					}
				}
			%>
					</select>
					</span>
				</div>
				<form id="validateForm" onsubmit="return false;" action="#"
					novalidate="novalidate">
					<div id="validateForm">
						<label>De</label> 
						<input type="text" id="txtFromDate" />
						<label>At&eacute;</label>
						<input type="text" id="txtToDate" />
					</div>
				</form>
				<div>
					<input id="btCancelar" class="bt_cancel space" type="reset" value="cancelar" /> 
					<input id="btGerar" class="bt_generate space" type="submit" value="gerar" />
				</div>
			</fieldset>
		</div>
	</div>

	<jsp:include page="/footer/rodape.jsp" />
</body>
</html>
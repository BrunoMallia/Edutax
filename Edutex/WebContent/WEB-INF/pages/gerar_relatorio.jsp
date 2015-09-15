<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<html>

<head>
<jsp:include page="/header/header.jsp" />
<script src="<%=request.getContextPath()%>/js/util.js"></script>
<script src="<%=request.getContextPath()%>/js/relatorio.js"></script>
<style type="text/css">
table tr td {
    border: 1px solid #CCC;
    text-align: center;
    background: #FFF none repeat scroll 0% 0%;
    padding: 5px;
    min-width: 40px !important;
}
</style>
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
		<form id="validateForm" method="get" target="_blank" action="gerarRelatorio.do">	
			<fieldset>
				<legend>Gerar Relat&oacute;rios</legend>
				<div class="">
					<label>Tipo de Relat&oacute;rio</label> 
					<select id="tipoRelatorio" name="tipoRelatorio">
						<option value="0">Selecione uma op&ccedil;&atilde;o</option>
						<!-- <option value="metas">Total de metas</option> -->
						<!-- <option value="metas_falhas">N&uacute;meros de metas com
							falhas</option>
						<option value="atacadista">Base de c&aacute;lculo
							atacadista</option> -->
						<!-- <option value="aliquita">Diferen&ccedil;a de
							al&iacute;quota</option> -->
							<option value="1" >Notas Complementares</option>
					</select> 
					
				</div>
			
				<div id="empresas" class=""> 
					<span> <label>Empresas</label> <select name="empresaSelect">
									<option value="0">Todas</option>
							<c:forEach items="${empresas}" var="empresa" >
									<option  value="${empresa.cdcnpj}"><c:out value="${empresa.nmEmpresa}"></c:out></option>
								</c:forEach>		
	
					</select>
					</span>
				</div>
				
					<div id="validateForm">
						<label>De</label> 
						<input type="text" id="txtFromDate" />
						<label>At&eacute;</label>
						<input type="text" id="txtToDate" />
					</div>
				
				<div>
					<input id="btCancelar" class="bt_cancel space" type="reset" value="cancelar" /> 
					<input id="btGerar" class="bt_generate space" type="submit" value="gerar" />
				</div>
				<div>
				<span class="messageError">
					
				</span>
				</div>
			</fieldset>
			</form>
		</div>
	</div>

	<jsp:include page="/footer/rodape.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/imgs/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		
	</head>
    
    <body class="bg_login">
      <header class="header_login">
      
      
        <h1>Edut Consultoria SGCC</h1>
        <h2>Sistema Gerenciamento <br />
        de Consultoria & Contabilidade</h2>
      </header>
      <div class="login_container">
        <div class="login_formbox">
          <h3 class="login_title">
            bem Vindo ao sistema SGCC 
          </h3>
          

          <form method="post" action="<%=request.getContextPath()%>/j_security_check">
          	<div class="lgn">
	            <span class="input_style"> 
	            <input type="text" class="input_style" value="" id="login"  name="j_username" />
	            </span>
	            <input type="password" class="input_style" value="" id="password" name="j_password" />
	            <!-- <p>Esqueceu sua senha? <a href="#">clique aqui</a></p> -->
	            
	            <c:if test="${falha != '' || empty falha}">
	            	<h2 style="color:#EE0000 ; display:block">
	            		<c:out value="${falha}"></c:out>
	            	</h2>	
	            </c:if>
				<br/><br/>	            
	               <!--<a class="btn_login" class="header_logo_system" href="autentica.do"></a>-->
            		<input type="submit" value="Entrar"/>
	        </div>
	        </form>
	        
            <c:if test="${!empty listaEmpresas}">
             	<form method="post" action="autenticaEmpresa.do">
		            <div class="lgn" id="divEmpresas">
		            	<br/><br/>
						<h6>Selecione uma empresa:</h6>
						<select class="options_empresa" name="opcoesEmpresa">
								<c:forEach items="${listaEmpresas}" var="empresa" >
									<option  value="${empresa.cdcnpj}"><c:out value="${empresa.nmEmpresa}"></c:out></option>
								</c:forEach>			
						</select>
						<input type="submit" value="Entrar"/>
					</div>
				
            	</form>
            </c:if>
            
            <br>
          
        </div>
      </div>
      	<jsp:include page="/footer/rodape.jsp" />
    </body>
</html>
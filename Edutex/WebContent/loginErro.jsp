<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="imgs/favicon.ico" rel="shortcut icon" type="image/x-icon" />
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
          
          <c:if test="${empty listaEmpresas}">
          <form method="post" >
          	<div class="lgn">
	
	            	<h2 style="color:#EE0000 ; display:block;margin-top:20px;">
	            		falha na autenticação, tente novamente.
	            	</h2>	
	            	
				<br/><br/>	            
	               <a class="btn_voltar" class="header_logo_system"  onclick="window.history.back();"></a>
            		
	        </div>
	        </form>
	        </c:if>
 
            
            <br>
          
        </div>
      </div>
      	<jsp:include page="/footer/rodape.jsp" />
    </body>
</html>
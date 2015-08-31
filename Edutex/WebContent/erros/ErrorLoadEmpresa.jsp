<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/header/header.jsp" />

    <script type="text/javascript">

    </script>
    
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
       
             	
		            <div class="lgn" id="divEmpresas">
			            <h2 style="color:#EE0000 ; display:block">
		            		<c:out value="${falha}"></c:out>
		            	</h2>
				        	
				        <div style="margin-top:10px;" >
				      	<input type="button" value="voltar" onclick="window.history.back();" >
				      </div>
				      
					</div>
            
            <br>
          
        </div>
      </div>
      	<jsp:include page="/footer/rodape.jsp" />
    </body>
</html>
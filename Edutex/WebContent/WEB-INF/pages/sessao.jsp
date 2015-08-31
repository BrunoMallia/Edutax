<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header/header.jsp" />
<script src="js/usuario.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	contextPath='<%=request.getContextPath()%>';
})
 
</script>

</head>

 <body bgcolor="#f5f5f5">
 <jsp:include page="/header/modal.jsp"></jsp:include>
<header class="header_home">
        <div class="header_width">
        	     <jsp:include page="/header/cabecalho.jsp" />
	    		<jsp:include page="/header/aba_menu.jsp" />
         
          </div>
      </header>
      
      
     <form action="/login.do"> 
	      <div class="main_container_default">
	
	                <span><h2>A página expirou,clique no botão login para ir para a página de login novamente</h2></span>   
				
				
			<input type="submit" value="Login"/>		
				
	                
	
	      </div>
      </form>
      

        

      <footer class="footer_login">Sistema Gerenciamento de Cosultoria e Contabilidade  -  Versão 1.0  -  Desenvolvido por: Grupo Academico. </footer>

    </body>
</html>
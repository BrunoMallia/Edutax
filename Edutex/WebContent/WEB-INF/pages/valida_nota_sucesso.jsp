<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<jsp:include page="/header/header.jsp" />
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
          <legend>Tipo de Nota Fiscal</legend>
          <div class="">
            <form action="menu.do" method="get">
            	<div class="">
						<span>${sucesso}</span>
							
				</div>
            <div class="">
            		<input class="bt_ok space" type="submit" value="ok" />
            </div>
            </form>
          </div>
        </fieldset>
      </div>
      </div>
        
      <jsp:include page="/footer/rodape.jsp" />
</html>
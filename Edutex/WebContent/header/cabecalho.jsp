<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<a class="header_logo_system" href="menu.do">Edut Consultoria - SGCC</a>
 <div class="header_blackbar">
   <img class="header_pieceblack" src="../imgs/pieceblack.png" alt="" />
   
<!--    <a id="logout" style="display:none" href="login.do"></a> -->
 
   <p>Ol&aacute;, <span><c:out value="${sessionScope.user.nmUsuario}"/></span><br/>
   Empresa Selecionada: <span><c:out value="${sessionScope.empresa.nmEmpresa}"/></span></p> 
   <a  class="bt_exit" href="../logout.do">sair</a>
   <!--<input class="bt_exit" type="button" value="" />-->
 </div>

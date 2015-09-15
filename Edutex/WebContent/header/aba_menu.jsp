<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <div class="header_menu">
  <ul>
    <li><a href="<%=request.getContextPath()%>/gerencial/escolherRelatorio.do" >Gerar Relat&oacute;rios</a></li>
  </ul>
  <ul id="submenu_main">
    <li><a href="javascript:" >Gerenciar &#9660;</a>
      <ul class="submenu">
        <li class="submenu_first_lihover"><a class="submenu_first_ahover" href="<%=request.getContextPath()%>/usuario/gerenciaUsuario.do" >Usu&aacute;rio</a></li>
        <li><a href="<%=request.getContextPath()%>/empresa/gerenciaEmpresa.do" >Empresa</a></li> 
        <li class="submenu_removeborder"><a href="<%=request.getContextPath()%>/ncm/gerenciaNcm.do" >Ncm</a></li>
      </ul>
    </li>
  </ul>
  <ul>
    <li><a href="<%=request.getContextPath()%>/notafiscal/validarNota.do" >Validar Nota Fiscal</a></li>
  </ul>
</div>
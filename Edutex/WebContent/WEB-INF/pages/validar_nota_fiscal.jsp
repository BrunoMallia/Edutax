<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="multipart/form-data"/>
	<jsp:include page="/header/header.jsp" />
</head>
    <body bgcolor="#f5f5f5">
	<form id="updateNotaForm" name="updateNotaForm" action="updateNotaFiscal.do" enctype="multipart/form-data"  method="POST">
	<jsp:include page="/header/modal.jsp"></jsp:include>
	<header class="header_home">
        <div class="header_width">
          <jsp:include page="/header/cabecalho.jsp" />
          <jsp:include page="/header/aba_menu.jsp" /> 
        </div>
      </header>
      
      <c:if test="${!empty erro}">
      
      	<script type="text/javascript">
      	
    	function fecharModal(){
  			$('#mask').hide();
  			$('.window').hide();
  		}
      	
      	$(document).ready(function(){
      		
      	
      		$("#btValidaNota").attr('alt', '#dialog');
      		var resposta = "<br> ${erro}  <br/><br/><br/><input class='bt_ok space,close' type='reset' value='ok' onclick='fecharModal();' />";
      		$("#resultBox").html(resposta);
      		
      		
      		
      		mostrarBox();
      	});
      	</script>
       
       
       
      
      </c:if>
      
      <div class="main_container_default"> 
      <div class="container_default">
       <script type="text/javascript">
     	$(document).ready(function(){
     	 
     	$("#btValidaNota").click(function(){
     		var inputRadio = $('form[name="updateNotaForm"] input[type=radio]'); 
 			var checkRadio = 0;
 			var string = '';
 			
 		    $(inputRadio).each(function(){
 					if ($(this).get(0).checked){
 						checkRadio++;
 					}
 			})
 			
 			if(checkRadio == 0){
 				 string  += '<br/>' +  "Obrigatório selecionar uma finalidade da nota";
 			}else{
 				if(checkRadio > 1){
 					string  += '<br/>' +  "Erro na checagem da finalidade da nota";
 				}
 			}	
 		    
 		    if ($("#arquivo").val().length == 0) {
 		    	string += "<br/> Campo de upload de nota fiscal é obrigatório";
 		    }
     		
     			
 			 if(string.length > 1){
			 	 $('#textContainer span').html(string);
				 $('#textContainer').show();
				 return false;
			 }
 			 
 			// $( "#updateNotaForm" ).submit();
     		
     	})	
     		
     			
     	});
       
       </script>
        <fieldset>
          <legend>Validar Nota Fiscal</legend>
          <div>
           <h2>Por favor selecione que tipo de Nota Fiscal deseja validar abaixo:</h2>
           	<c:forEach items="${sessionScope.finalidadeNota}" var="finalidade" >
           		<input type="radio" value="${finalidade.cdFinalidadeNfe}" name="finalidadeNfe"><label>${finalidade.nmFinalidade}</label>
           	</c:forEach>
            
           
          </div>
          
          <div class="">
            <h2>Por favor selecione uma nota fiscal v&aacute;lida.</h2>
            <label>Nota Fiscal</label>
            <input type="file" name="arquivo" id="arquivo"  accept="xml" value="" />
          </div>
          <div class="">
            <input class="bt_cancel space"  type="reset" value="cancelar" onclick="location.href='<%=request.getContextPath()%>/principal/home.do'"/>
            <a href="tipoNotaFiscal.do" name="link so pra demonstrar a navegação!!">
            <input class="bt_generate space" name="btValidaNota" id="btValidaNota" type="submit" value="gerar" /></a>
          </div>
           <div id="textContainer" style="display:none">
               <span class="messageError">
               </span>
            </div>
        </fieldset>
      </div>
      </div>
      </form>  
      <jsp:include page="/footer/rodape.jsp" />
      </body>
</html>
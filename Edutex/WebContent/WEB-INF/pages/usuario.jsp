<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<jsp:include page="/header/header.jsp" />	
<meta Http-Equiv="Cache-Control" Content="no-cache">  
<meta http-equiv="pragma" content="no-cache">
<meta Http-Equiv="Expires" Content="now">  

</head>

 <body bgcolor="#f5f5f5">
 <script src="../js/usuario.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	contextPath='<%=request.getContextPath()%>';
	$("#addUsu")[0].reset();
})
 
</script>
 <jsp:include page="/header/modal.jsp"></jsp:include>
<header class="header_home">
        <div class="header_width">
        	     <jsp:include page="/header/cabecalho.jsp" />
	    		<jsp:include page="/header/aba_menu.jsp" />
         
          </div>
      </header>
      
      <div class="main_container_default">

      <div class="container_default">

        <div class="TabControl">

          <div id="header">

            <ul class="abas">

              <li>

                <div class="aba">

                  <span>Adicionar Usuário</span>

                </div>

              </li>

              <li>

                <div class="aba">

                  <span>Alterar Usuário</span>

                </div>

              </li>

              <li>

                <div class="aba">

                  <span>Desabilitar/Habilitar Usuários</span>

                </div>

              </li>

            </ul>

          </div>

          <div id="content">

            <div class="conteudo">

              <fieldset class="remove_status">

                <p class="">

                  <form name="addUsu" id="addUsu" action="" method="post">

                  <span>
					<input type="text" placeholder="Email" id="emailAddId" name="emailAddId">	
                  <input type="text" placeholder="Nome do Usuário" id="nomeUsuarioAdd" name="nomeAddUsu">  

                  </span>

                  <span>

                  <input type="password" id="senUsuario" placeholder="Senha">

                  <input type="password" id="senRepUsuario" placeholder="Repetir Senha">

                  </span>
				
                  <span>

                  <select id="selectTpUsuario">
                    <option value="0" >Selecionar tipo de usuario</option>
					<c:forEach items="${listaTpUsuario}"  var="item"  >
					<option  value="${item.cdTipoUsuario}">	<c:out value="${item.nmTipoUsuario}"></c:out>
					</option>
				</c:forEach>
                  </select>

                  </span><br />

                  <span>

                    <h2>Selecione as Empresas abaixo:</h2><br />
                  <select size="${fn:length(listaEmpresa)}" id="opcoesEmpresa" name="opcoesEmpresa" multiple>
                 <option value="0"> </option>   
                 <c:forEach items="${listaEmpresa}" var="itemEmp" >
						<option  value="${itemEmp.cdcnpj}" ><c:out  value="${itemEmp.nmEmpresa}" />
						</option>
						                 
                 </c:forEach>   
								</select>


                  </span><br /> 

                  <span><br/>

                  <input class="bt_cancel space" type="reset" id="btCancelarAdd" value="cancelar" />

                  <input class="bt_save space" type="button" value="gerar" id="salvarUsuario" name="salvarUsuario" />

                  </span>
					
				<div id="textContainer1" style="display:none">
                    <span class="messageError">
                    </span>
                  </div>
					
                  </form>

                </p>

              </fieldset>

            </div>

            <div class="conteudo" style="display: none;">

              <fieldset class="remove_status">

                <p class="">

                  <form action="" name="AlterarUsuarioForm" id="AlterarUsuarioForm" method="post">

                <span>
                
                  <input type="text" placeholder="Email" id="emailAlterarId" name="emailAltName" class="greatmail">	
					 <input type="text" placeholder="Nome do Usuário" id="nomeUsuarioAlterar" name="nomeAltUsu" style="display:none">  
				</span>	
				  	
	              <div id="containerAlterarUsuario" style="display:none;margin:0;padding:0;"  >
	                  <span>
	                    <input type="hidden" id="tpStatusId" name="tpStatusId" >
	                    <input type="hidden" id="cdUsuarioHidden">
	                    <input type="password" id="senUsuarioAlt" placeholder="Senha">
	                 	 <input type="password" id="senRepUsuarioAlt" placeholder="Repetir Senha">
	                 	 </span>
	
	            
	               
					<span>	
	                  <select id="selectTpUsuarioAlterar">
	                   	 <option value="0" >Selecionar tipo de usuario</option>
							<c:forEach items="${listaTpUsuario}"  var="item"  >
							<option  value="${item.cdTipoUsuario}">	<c:out value="${item.nmTipoUsuario}"></c:out>
							</option>
							</c:forEach>
	                  </select>
					</span>
                  </span><br />

                  <span>

                 <h2>Selecione as Empresas abaixo:</h2><br />
                  <select size="${fn:length(listaEmpresa)}" id="opcoesEmpresaAlterar" name="opcoesEmpresaAlterar" multiple>
                 <option value="0"> </option>   
                 <c:forEach items="${listaEmpresa}" var="itemEmp" >
						<option  value="${itemEmp.cdcnpj}" ><c:out  value="${itemEmp.nmEmpresa}" />
						</option>
						                 
                 </c:forEach>   
				</select>

                  </span><br/>
			
                </div> 
                
                
                	<span>
                	<p class="">
                   		 <input class="bt_cancel space" type="reset" value="cancelar" id="btCancelarAlt" onclick="cancelarAlterar();"/>
                   		  <input class="bt_pesquisar space" type="button" id="btVerificarAlterar" value="gerar"/>
                   		  <input class="bt_save space" id="btAlterar" type="button" value="gerar" style="display:none"/>
                   	</p>	  
                	</span>
                
                
                  <div id="textContainer2" style="display:none">
                    <span class="messageError">
                    </span>
                  </div>
                  
                
                  </form>

               

              </fieldset>

            </div>

            <div class="conteudo" style="display: none;">

              <fieldset class="remove_status">

                <p class="">

                  <form action="" method="post">

                 <span>

                    <input type="text" placeholder="Email" id="emailCheckAtv" class="greatmail">

                  </span>

                  <p class="">

                  <input class="bt_cancel space" type="reset" value="cancelar" />

                  <input class="bt_pesquisar space" type="button" value="gerar" id="btPesquisarAtv"/>
				  <input type="hidden" id="cdUsuario2">	
						
						
                  </p>
                  
                  
                  
                  <div id="textContainer3" style="display:none">
                    <span class="messageError">
                    </span>
                  </div>
                    
                  </form>

                </p>

              </fieldset>

            </div>

          </div>

        </div>

      </div>

      </div>


      <footer class="footer_login">Sistema Gerenciamento de Cosultoria e Contabilidade  -  Versão 1.0  -  Desenvolvido por: Grupo Academico. </footer>

    </body>
</html>
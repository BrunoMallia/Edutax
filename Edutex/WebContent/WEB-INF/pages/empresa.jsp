<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/header/header.jsp" />
</head>
<body>
   <jsp:include page="/header/modal.jsp"></jsp:include>
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';

$.ajaxSetup({
	  global: false,
	  type: "POST"
	});

function fecharModal(){
	$('#mask').hide();
	$('.window').hide();
}

  $(document).ready(function(){
	$("#containerAlterarEmpresa").css({'display': 'none', 'margin': '0', 'padding': '0'});
	    
	 $("#cnpj").mask("99.999.999/9999-99");
	 $("#cnpj2").mask("99.999.999/9999-99");
     $("#cnpjAba2").mask("99.999.999/9999-99");
 
   $('ul#submenu_main>li').mouseover(function(){
        $(this).children('ul.submenu').css({'display':'block'});
    });
    
    $('ul#submenu_main>li').mouseout(function(){
        $(this).children('ul.submenu').css({'display':'none'});
    });
    
    $("#content div:nth-child(1)").show();
    $(".abas li:first div").addClass("selected2");
    $(".aba").click(function(){
      $(".aba").removeClass("selected2");
      $(this).addClass("selected2");
      var indice = $(this).parent().index();
      indice++;
      $("#content div").hide();
      $("#content div:nth-child("+indice+")").show();
      $("#containerAlterarEmpresa2").hide();
      });
    $(".aba").hover(
      function(){$(this).addClass("ativa")},
      function(){$(this).removeClass("ativa")}
    );
    
	 $(this).on("click","#btAtivarEmpresa",function(){

			$.ajax({
					type: 'POST',
					dataType : "json",
					url:  contextPath + '/empresa/gerenciaEmpresaAjax.do',
					beforeSend: function(){
						 $('html,body').css({'cursor':'wait'});
					},
					data: {
						cnpj: $('#cnpjAba2').val(),
						idEmpresa: $("#cdEmpresa2").val(),
						ativar:$("#btAtivarEmpresa").attr("value"),
						acao: 'atualizarStatus'
					},
					success: function(resposta){
						$("#resultBox").attr('style','padding: 4px;');
						$("#btPesquisarAba2").attr('alt', '#dialog');
						
						if(resposta.erro == null) {
							
							var responseStruct = resposta.sucess + "<br/><br/><br/><input class='bt_save space,close' type='reset' value='ok' onclick='fecharModal();' />"
							$("#resultBox").html('<br>' + responseStruct);
						}else {
							$("#resultBox").html('<br>' + response.erro);
						}
					
						mostrarBox();
						
					},
				}).fail(function(jqXHR, textStatus, errorThrown){
					$("#resultBox").attr('style','padding: 4px;');
					$("#resultBox").html('<br>' + 'erro' + textStatus);
					$("#btPesquisarAba2").attr('alt', '#dialog');
					mostrarBox();
					
				}).done(function(){
					 $('html,body').css({'cursor':'default'});	
				})		
		})
 
});
  

	 $(function(){


		 $('#cnpj').focus(function(){
			 $('#textContainer span').html('<br/>');
		 })
		 
		 $('#nmEmpresa').focus(function(){
			 $('#textContainer span').html('<br/>');
		 })
		 
		 $('#btSalvar').click(function(){
			 var string = '';
				
			  if ($("#cnpj").val().length == 0){
				  string = "CNPJ é obrigatório.";
			  } else {
				  if (!validarCNPJ($("#cnpj").val())){
					  string = "CNPJ inválido.";
				  }	  
			  }
		    
			  
			  if ($("#nmEmpresa").val().trim().length == 0) {
				   string  += '<br/>' +  "Nome da empresa é obrigatório.";
			  }
		    var inputRadio = $('form[name="adEmpForm"] input[type=radio]'); 
			var checkRadio = 0;
		    $(inputRadio).each(function(){
					if ($(this).get(0).checked){
						checkRadio++;
					}
			})
			
			if(checkRadio == 0){
				 string  += '<br/>' +  "Regime de tributação obrigatório";
			}else{
				if(checkRadio > 1){
					string  += '<br/>' +  "Erro na checagem do Regime Tributario";
				}
			}
				 
				 if(string.length > 1){
				 	 $('#textContainer span').html(string);
					 $('#textContainer').show();
					 return false;
				 }
			 
				
			 
			 $.ajax({
					type: 'POST',
					url:  contextPath + '/empresa/gerenciaEmpresaAjax.do',
					beforeSend: function(){
						 $('html,body').css({'cursor':'wait'});
					},
					data: {
						cnpj: $('#cnpj').val(),
						nmEmpresa: $('#nmEmpresa').val(),
						regimeTributario: $('input[name=radioImputReg]:checked', '#adEmpForm').val(),
						acao: 'inserir'
					},
					success: function(response) {
						$("#resultBox").attr('style','padding: 4px;');
						$("#resultBox").html('<br>' + response + "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />");
						$("#btSalvar").attr('alt', '#dialog');
						
						var numeroIndice = response.indexOf("<OK>"); 
						
						if(numeroIndice != -1) {
							$('#cnpj').val("");
							$('#nmEmpresa').val("");
							$('input[name=radioImputReg]:checked', '#adEmpForm').val(0);
							response.replace("<OK>", "");	
						}
						
						
						mostrarBox();
						
						//alert(response);
					},
				}).fail(function(jqXHR, textStatus, errorThrown){
					$("#resultBox").attr('style','padding: 4px;');
					 var resp = response + "<br/><br/><br/><input class='bt_ok' type='reset' value='ok' onclick='fecharModal();' />";
					$("#resultBox").html('<br>' + response);
					$("#btSalvar").attr('alt', '#dialog');
					mostrarBox();
				}).done(function(){
					 $('html,body').css({'cursor':'default'});	
				})
		 })
		 
		 $("#btPesquisarAba2").click(function(){
			  var string2 = '';
			  if ($("#cnpj2").val().length == 0){
				  string2 = "CNPJ é obrigatório.";
			  } else {
				  if (!validarCNPJ($("#cnpj2").val())){
					  string2 = "CNPJ inválido.";
				  }
						  
				  }	
				  
					 if(string2.length > 1){
						 $('#textContainer2 span').html(string2);
						 $('#textContainer2').show();
						 return false;
					 }
				  
					 
					 $.ajax({
							type: 'GET',
							url:  contextPath + '/empresa/gerenciaEmpresaAjax.do',
							dataType: "json",
							beforeSend: function(){
								 $('html,body').css({'cursor':'wait'});
							},
							data: {
								cnpj: $('#cnpj2').val(),
								acao: 'pesquisar'
							},
							success: function(response){
								if(response.erro == null) {
									//$( "#cnpj2" ).prop( "disabled", true );
									 $("#containerAlterarEmpresa2").show();
									 $("#cdEmpresa").val(response.cdEmpresa);
									 $("#nmEmpresaAlt").val(response.nmEmpresa);
									 $('input[name=radioImputRegAlt][value =' + response.regimeTributario + ']' , '#altEmpForm').prop('checked', true);	
									
								}else {
									//alert(response.erro);
									$("#resultBox").attr('style','padding: 4px;');
									$("#resultBox").html('<br>' + response.erro);
									$("#btPesquisarAba2").attr('alt', '#dialog');
									mostrarBox();
								}
								
								 
								 									
							},
						}).fail(function(jqXHR, textStatus, errorThrown){
							$("#resultBox").attr('style','padding: 4px;');
							var resp = resp + "<br/><br/><br/><input class='bt_ok' type='reset' value='OK' onclick='fecharModal();' />";
							$("#resultBox").html('<br>' + resp);
							$("#btPesquisarAba2").attr('alt', '#dialog');
							mostrarBox();
						}).done(function(){
							 $('html,body').css({'cursor':'default'});	
						})
				 
			 	
		 })
		 
		 
		 $("#btAlterar").click(function(){
	 			var string = '';
				
				  if ($("#cnpj2").val().length == 0){
					  string = "CNPJ é obrigatório.";
				  } else {
					  if (!validarCNPJ($("#cnpj2").val())){
						  string = "CNPJ inválido.";
					  }	  
				  }
			    
				  
				  if ($("#nmEmpresaAlt").val().trim().length == 0) {
					   string  += '<br/>' +  "Nome da empresa é obrigatório.";
				  }
			    var inputRadio = $('form[name="altEmpForm"] input[type=radio]'); 
				var checkRadio = 0;
			    $(inputRadio).each(function(){
						if ($(this).get(0).checked){
							checkRadio++;
						}
				})
				
				if(checkRadio == 0){
					 string  += '<br/>' +  "Regime de tributação obrigatório";
				}else{
					if(checkRadio > 1){
						string  += '<br/>' +  "Erro na checagem do Regime Tributario";
					}
				}
					 
					 if(string.length > 1){
					 	 $('#textContainer2 span').html(string);
						 $('#textContainer2').show();
						 return false;
					 }
					 
					 $.ajax({
							type: 'POST',
							url:  contextPath + '/empresa/gerenciaEmpresaAjax.do',
							beforeSend: function(){
								 $('html,body').css({'cursor':'wait'});
							},
							data: {
								idEmpresa: $('#cdEmpresa').val(),	
								cnpj: $('#cnpj2').val(),
								nmEmpresa: $('#nmEmpresaAlt').val(),
								regimeTributario: $('input[name=radioImputRegAlt]:checked', '#altEmpForm').val(),							
								acao: 'atualizar'
							},
							success: function(response){
								$("#resultBox").attr('style','padding: 4px;');
								$("#resultBox").html('<br>' + response);
								$("#btPesquisarAba2").attr('alt', '#dialog');
								mostrarBox();
								//alert(response);
							},
						}).fail(function(jqXHR, textStatus, errorThrown){
							$("#resultBox").attr('style','padding: 4px;');
							var resp = textStatus +  "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />";
							$("#resultBox").html(resp);
							$("#btPesquisarAba2").attr('alt', '#dialog');
							mostrarBox();
							
						}).done(function(){
							 $('html,body').css({'cursor':'default'});	
						})
				 
					
	 		})
			
	 		$("#cnpj2").keypress(function(event){
	 			if(event.which == 8)
	 		     $("#containerAlterarEmpresa2").hide();
	 		     $("#cnpj2").val();
	 		})
	 		
	 		$("#btAtivaEmp").click(function(){
	 			var string = '';
				
				  if ($("#cnpjAba2").val().length == 0){
					  string = "CNPJ é obrigatório.";
				  } else {
					  if (!validarCNPJ($("#cnpjAba2").val())){
						  string = "CNPJ inválido.";
					  }	  
				  }
				  
					 if(string.length > 1){
					 	 $('#textContainer3 span').html(string);
						 $('#textContainer3').show();
						 return false;
					 }
					 
					 $.ajax({
							type: 'POST',
							url:  contextPath + '/empresa/gerenciaEmpresaAjax.do',
							beforeSend: function(){
								 $('html,body').css({'cursor':'wait'});
							},
							data: {
								cnpj: $('#cnpjAba2').val(),
								acao: 'pesquisarStatus'
							},
							success: function(response){
								$("#resultBox").attr('style','padding: 4px;');
								$("#btPesquisarAba2").attr('alt', '#dialog');
								
								if(response.erro == null) {
									$("#cdEmpresa2").val(response.idEmpresa);
									var responseStruct = response.sucess + "<br/><br/><br/><input class='bt_cancel space,close' type='reset' value='cancelar' onclick='fecharModal();' />"+
					                  "<input class='bt_ok space' type='button' id='btAtivarEmpresa' name='btAtivarEmpresa'/>";
				                  	 
									$("#resultBox").html('<br>' + responseStruct);
									$("#btAtivarEmpresa").attr('value', response.ativo);
									
									
								}else {
									$("#resultBox").html('<br>Contacte com o Administrador do sistema' + response.erro);
								}
							
								mostrarBox();
								//alert(response);
							},
						}).fail(function(jqXHR, textStatus, errorThrown){
							$("#resultBox").attr('style','padding: 4px;');
							$("#resultBox").html('<br>' + 'erro' + textStatus);
							$("#btPesquisarAba2").attr('alt', '#dialog');
							mostrarBox();
							
						}).done(function(){
							 $('html,body').css({'cursor':'default'});	
						})
			    
	 		})
	 		
	 })
	 
  
  
</script>
 
    <body bgcolor="#f5f5f5">
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
                  <span>Adicionar Empresa</span>
                </div>
              </li>
              <li>
                <div class="aba">
                  <span>Alterar Empresa</span>
                </div>
              </li>
              <li>
                <div class="aba">
                  <span>Desabilitar/Habilitar Empresa</span>
                </div>
              </li>
            </ul>
          </div>
          <div id="content">
            <div class="conteudo">
              <fieldset class="remove_status">
                <p class="">
                  <form id="adEmpForm" name="adEmpForm" action="" >
                  <span>
                  <input type="text" id="cnpj" name="cnpj" placeholder="CNPJ da Empresa" class="greatmail"/>
               		
                  </span>
                  <span>
                  <input type="text" id="nmEmpresa" name="nmEmpresa" placeholder="Nome da Empresa" class="greatmail"/>  
                  </span>
                  <span>
                    <h2>Regime Tributário</h2><br />
                    <c:forEach items="${regimeTibutario}"  var="item"  >
                    	<input value="${item.codTipoImposto}" name="radioImputReg" type="radio" id="radioImputReg" /><label for="radioRegimeTributario" >
                    		<c:out value="${item.nmTipoImposto}"></c:out></label>&nbsp;
                    </c:forEach>
                  </span><br />  
                  <span>
                  <input class="bt_cancel space" type="reset" value="cancelar" />
                  <input class="bt_save space" id="btSalvar" type="button" value="salvar"  />
                  </span>
                  <div id="textContainer" style="display:none">
                    <span class="messageError">
                    </span>
                  </div>
                  </form>
                </p>
              </fieldset>
            </div>
            <div class="conteudo" style="display:none;">
              <fieldset class="remove_status">
                <p class="">
                  <form id="altEmpForm" name="altEmpForm">
                  <span>
                  <input type="text" placeholder="73.565.253/0001-86" class="greatmail" name="cnpj2" id="cnpj2">
                  <input class="bt_pesquisar"  id="btPesquisarAba2" type="button" value="pesquisar"/>
                  </span>
                  <div id="containerAlterarEmpresa2" style="display:none;margin:0;padding:0" >
	                   <span>
	                    <input type="hidden" id="cdEmpresa" name="cdEmpresa" />
	                  	<input type="text" placeholder="Nome da Empresa" class="greatmail" id="nmEmpresaAlt" name="nmEmpresaAlt"/>  
	                  </span>
	                  <span>
	                    <h2>Regime Tributário</h2><br />
	                      <c:forEach items="${regimeTibutario}"  var="item"  >
	                    	<input value="${item.codTipoImposto}" name="radioImputRegAlt" type="radio" id="radioImputRegAlt" /><label for="radioRegimeTributarioAlt" >
	                    		<c:out value="${item.nmTipoImposto}"></c:out></label>&nbsp;
	                    </c:forEach>
	                  </span><br /> 
                  <span>
	                  <br />
	                  <input class="bt_cancel space" type="reset" value="cancelar" />
	                  <input class="bt_save space" type="button" value="gerar" id="btAlterar" name="btAlterar"/>
	              </span>  
	                  </div>
                  <div id="textContainer2" style="display:none">
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
                  <form name="ativaEmpForm" id="ativaEmpForm" action="">
                  <span>
                    <input type="hidden" id="cdEmpresa2" name="cdEmpresa2" />
                  <input type="text" placeholder="CNPJ da Empresa" name="cnpjAba2" id="cnpjAba2" class="greatmail">
                  <input class="bt_pesquisar" type="button" value="Ativar" id="btAtivaEmp" />
                  <input type="hidden" id="fieldAtivar" name="fieldAtivar" />
                  
                  </span>
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
<jsp:include page="/footer/rodape.jsp" />
</body>
</html>
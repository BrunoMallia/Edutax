var contextPath;;

$.ajaxSetup({
	  global: false,
	  type: "POST"
	});


function fecharModal(){
	$('#mask').hide();
	$('.window').hide();
}


/**
 * metodos especificos para a tela usuario.jsp
 */

$(document).ready(function() {

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
      $("#containerAlterarUsuario").css({'display':'none'});
      $("#emailAlterarId").addClass("greatmail");
      $("#nomeUsuarioAlterar").hide();
  	  $('#btVerificarAlterar').show();
  	  $("#emailAlterarId").prop("disabled",false);
	  $('#btAlterar').hide();
	  $("#AlterarUsuarioForm")[0].reset()
	  $("#senUsuario").val("");
	  $("#senRepUsuario").val("");
      });

    $(".aba").hover(

      function(){$(this).addClass("ativa")},

      function(){$(this).removeClass("ativa")}

    );

    $(this).on("click","#btAtivarUsuario",function(){

    	$.ajax({
    			type: 'POST',
    			dataType : "json",
    			url:  contextPath + '/usuario/gerenciaUsuarioAjax.do',
    			beforeSend: function(){
    				 $('html,body').css({'cursor':'wait'});
    			},
    			data: {
    				email: $("#emailCheckAtv").val(),
    				idUsuario: $("#cdUsuario2").val(),
    				ativar:$("#btAtivarUsuario").attr("value"),
    				acao: 'atualizarStatus'
    			},
    			success: function(resposta){
    				$("#resultBox").attr('style','padding: 4px;');
    				$("#btPesquisarAba2").attr('alt', '#dialog');
    				
    				if(resposta.erro == null) {
    					
    					var responseStruct = resposta.sucess + "<br/><br/><br/><input class='bt_ok space,close' type='reset' value='ok' onclick='fecharModal();' />"
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
	var emailRegex = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$/);
	
	$("#salvarUsuario").click(function(){

			
			var result = "";
			var email = $("#emailAddId").val();
			var opcoesEmpresa = [];
			var campoSenha = $("#senUsuario");
			var campoRepeteSenha = $("#senRepUsuario")
			var empresas = ""; 
			var icount = 0;
			
			 $("#textContainer1 span").html("");
			
			if(email == "" || email == null) {
				result += "Campo email obrigatório <br/>";
				
			} else {
				if (!emailRegex.test(email)) {
					result += "Email inválido. <br/>";
				} 
			}
			
			
			if ($("#nomeUsuarioAdd").val().length == 0) {
				result += "Nome de Usuário inválido. <br/>";
		}
			
			if($("#selectTpUsuario option:selected").val() == '0') {
					result += "Nenhuma opção Tipo de usuário foi selecionada. <br/>";
			}
			
			$('#opcoesEmpresa').each(function(i, selected){
				 if ($(selected).val() !=  null && $(selected).val() != '0') {
					opcoesEmpresa[i] = $(selected).val();
					  empresas += $(selected).val();
					  empresas += ",";
				}
				  
				  
			});
			
			
			
		
			
			if (empresas == "") {
				result += "Nenhuma opção de Empresa foi selecionada para o usuário <br/>";
			}
			
			
			
			if (campoSenha.val().length == 0) {
				result += "A senha é um campo obrigatório <br/>";
			}
			
			if (campoSenha.val().length < 5) {
				result += "A senha precisa ter no mínimo 5 dígitos <br/>";
			}
			
			if (campoSenha.val().length > 10) {
				result += "A senha precisa ter no máximo 10 dígitos <br/>";
			}
			
			
			if (campoRepeteSenha.val().length == 0) {
				result += "A repetição da senha é um campo obrigatório <br/>";
			}
			
			
			if (campoSenha.val() != campoRepeteSenha.val()) {
				 result += "A confirmação da senha não está de acordo com a senha inserida <br/>";
			}
			
			
			if(result.trim().length >= 1) {
				 $("#textContainer1 span").html(result);
				 $("#textContainer1").show();
				 return false;
			}

			
			
			$.ajax({
				type: 'POST',
				url:  contextPath + '/usuario/gerenciaUsuarioAjax.do',
				beforeSend: function(){
					 $('html,body').css({'cursor':'wait'});
				},
				data: {
					nomeUsuario: $("#nomeUsuarioAdd").val(),
					email: email,
					senha: campoSenha.val() ,
					tipoUsuarioId: $("#selectTpUsuario option:selected").val(),
					opcoesEmpresa: empresas ,
					acao: 'inserir'
				},
				success: function(response){
					$("#resultBox").attr('style','padding: 4px;');
					$("#resultBox").html('<br>' + response + "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />");
					$("#salvarUsuario").attr('alt', '#dialog');
					
					var numeroIndice = response.indexOf("<OK>"); 
					
					if(numeroIndice != -1) {
				
						$("#addUsu")[0].reset()
					}
					
					 
					mostrarBox();
				},
			}).fail(function(jqXHR, textStatus, errorThrown){
				$("#resultBox").attr('style','padding: 4px;');
				$("#resultBox").html('<br>' + textStatus  + "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />")
				$("#salvarUsuario").attr('alt', '#dialog');
				mostrarBox();
			}).done(function(){
				 $('html,body').css({'cursor':'default'});	
			})
	 })
			
	 

	 $("#btVerificarAlterar").click(function(){

			
			var result = "";
			var email = $("#emailAlterarId").val();
			var opcoesEmpresa;
			var empresas = "";
			 $("#textContainer1 span").html("");
			
			if(email == "" || email == null) {
				result += "Campo email obrigatório <br/>";
				
			} else {
				if (!emailRegex.test(email)) {
					result += "Email inválido. <br/>";
				} 
			}
			
			
			if(result.trim().length >= 1) {
				 $("#textContainer2 span").html(result);
				 $("#textContainer2").show();
				 return false;
			}
			
			$.ajax({
				type: 'POST',
				url:  contextPath + '//usuario/gerenciaUsuarioAjax.do',
				beforeSend: function(){
					 $('html,body').css({'cursor':'wait'});
				},
				data: {
					email: email ,
					acao: 'pesquisar'
				},
				success: function(resposta){
					$("#resultBox").attr('style','padding: 4px;');
			
					if (resposta.erro == null) {
						$("#cdUsuarioHidden").val(resposta.cdUsuario);
						$("#nomeUsuarioAlterar").val(resposta.nmUsuario);
						$("#selectTpUsuarioAlterar").val(resposta.tpUsuario);
						$("#tpStatusId").val(resposta.tpStatus);
						$.each(resposta.usuariosEmpresas.split(";"), function(i,e){
							if (e != "") {
								$("#opcoesEmpresaAlterar option[value='" + e + "']").prop("selected", true);
							}
						    
						});
					
						
						$("#containerAlterarUsuario").show();
					    $("#emailAlterarId").removeClass("greatmail");
					    $("#nomeUsuarioAlterar").show();
					    $("#btAlterar").show();
					    $("#btVerificarAlterar").hide();
					    $("#textContainer2 span").html("");
					    $("#emailAlterarId").prop("disabled",true);
				
					} else {
						$("#resultBox").attr('style','padding: 4px;');
						$("#resultBox").html('<br>' + resposta.erro  + "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />")
						$("#btVerificarAlterar").attr('alt', '#dialog');
						mostrarBox();
					}
					
					
				},
			}).fail(function(jqXHR, textStatus, errorThrown){
				$("#resultBox").attr('style','padding: 4px;');
				$("#resultBox").html("<br>Erro por favor, entre em contado com o administrador do sistema <br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />")
				$("#btVerificarAlterar").attr('alt', '#dialog');
				mostrarBox();
			}).done(function(){
				 $('html,body').css({'cursor':'default'});	
			})
			
	 });
	
	$("#btAlterar").click(function(){
		
		var result = '';
		var email = $("#emailAlterarId").val();
		var opcoesEmpresa = [];
		var campoSenha = $("#senUsuarioAlt");
		var campoRepeteSenha = $("#senRepUsuarioAlt")
		var empresas = ""; 
		var icount = 0;
		
		 $("#textContainer2 span").html("");
		
		if(email == "" || email == null) {
			result += "Campo email obrigatório <br/>";
			
		} else {
			if (!emailRegex.test(email)) {
				result += "Email inválido. <br/>";
			} 
		}
		
		
		if ($("#nomeUsuarioAlterar").val().length == 0) {
			result += "Nome de Usuário inválido. <br/>";
	}
		
		if($("#selectTpUsuarioAlterar option:selected").val() == '0') {
				result += "Nenhuma opção Tipo de usuário foi selecionada. <br/>";
		}
		
		
		$('#opcoesEmpresaAlterar').each(function(i, selected){
			 if ($(selected).val() !=  null && $(selected).val() != '0') {
				opcoesEmpresa[i] = $(selected).val();
				  empresas += $(selected).val();
				  empresas += ",";
			}
			  
			  
		});
		
		
		
	
		
		if (empresas == "") {
			result += "Nenhuma opção de Empresa foi selecionada para o usuário <br/>";
		}
		
		if (campoSenha.val().length != 0 && campoSenha.val().length < 5) {
			result += "A senha deve ter no mínimo 5 caracteres.<br/>";
		}
		
		if (campoSenha.val().length != 0 && campoSenha.val().length > 10) {
			result += "A senha deve ter no máximo 10 caracteres.<br/>"
			
		}
	
		
		if (((campoSenha.val().length != 0 || campoSenha.val() != "") ||
					(campoRepeteSenha.val().length != 0 || campoRepeteSenha.val() != "")) && (campoSenha.val() != campoRepeteSenha.val())) {
			 result += "A confirmação da senha não está de acordo com a senha inserida <br/>";
		}
		
		
		if(result.length >= 1) {
			 $("#textContainer2 span").html(result);
			 $("#textContainer2").show();
			 return false;
		}
		
		$.ajax({
			type: 'POST',
			url:  contextPath + '/usuario/gerenciaUsuarioAjax.do',
			beforeSend: function(){
				 $('html,body').css({'cursor':'wait'});
			},
			data: {
				email: email ,
				acao: 'atualizar',
				cdUsuario: $('#cdUsuarioHidden').val(),
				nomeUsuario: $('#nomeUsuarioAlterar').val(),
				tipoUsuario: $('#selectTpUsuarioAlterar option:selected').val(),
				tpStatus: $('#tpStatusId').val(),
				cdSenha: campoSenha.val(),
				usuariosEmpresas: empresas
			},
			success: function(resposta){
			    $("#resultBox").attr('style','padding: 4px;');
				$("#resultBox").html('<br>' + resposta  + "<br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />")
				$("#btAlterar").attr('alt', '#dialog');
				mostrarBox();
		
				
				
			},
		}).fail(function(jqXHR, textStatus, errorThrown){
			$("#resultBox").attr('style','padding: 4px;');
			$("#resultBox").html("<br>Erro por favor, entre em contado com o administrador do sistema <br/><br/><br/><input class='bt_ok' type='reset' value='cancelar' onclick='fecharModal();' />")
			$("#btAlterar").attr('alt', '#dialog');
			mostrarBox();
		}).done(function(){
			 $('html,body').css({'cursor':'default'});	
		})
		

	})
	
	$("#btPesquisarAtv").click(function(){
		
		var result = "";
		var email = $("#emailCheckAtv").val();
		
		
		 $("#textContainer3 span").html("");
			
			if(email == "" || email == null) {
				result += "Campo email obrigatório <br/>";
				
			} else {
				if (!emailRegex.test(email)) {
					result += "Email inválido. <br/>";
				} 
			}
		
		
		if(result.trim().length >= 1) {
			 $("#textContainer3 span").html(result);
			 $("#textContainer3").show();
			 return false;
		}
		
		 $.ajax({
				type: 'POST',
				url:  contextPath + '/usuario/gerenciaUsuarioAjax.do',
				beforeSend: function(){
					 $('html,body').css({'cursor':'wait'});
				},
				data: {
					email: email,
					acao: 'pesquisarStatus'
				},
				success: function(response){
					$("#resultBox").attr('style','padding: 4px;');
					$("#btPesquisarAtv").attr('alt', '#dialog');
					
					if(response.erro == null) {
						$("#cdUsuario2").val(response.idUsuario);
						var responseStruct = response.sucess + "<br/><br/><br/><input class='bt_cancel space,close' type='reset' value='cancelar' onclick='fecharModal();' />"+
		                  "<input class='bt_ok space' type='button' id='btAtivarUsuario' name='btAtivarUsuario'/>";
	                  	 
						$("#resultBox").html('<br>' + responseStruct);
						$("#btAtivarUsuario").attr('value', response.ativo);
						
						
					}else {
						$("#resultBox").html('<br>Contacte com o Administrador do sistema' + response.erro);
					}
				
					mostrarBox();
					
				},
			}).fail(function(jqXHR, textStatus, errorThrown){
				$("#resultBox").attr('style','padding: 4px;');
				$("#resultBox").html('<br>' + 'erro' + textStatus);
				$("#btPesquisarAtv").attr('alt', '#dialog');
				mostrarBox();
				
			}).done(function(){
				 $('html,body').css({'cursor':'default'});	
			})
 
	});
	
	
	 
});


function cancelarAlterar(){
	
	$('#nomeUsuarioAlterar').hide();
	$('#containerAlterarUsuario').hide(); 
	$('#emailAlterarId').addClass('greatmail');
	$('#btVerificarAlterar').show();
	$('#btAlterar').hide();
	$("#textContainer2 span").html("");
	$("#emailAlterarId").prop("disabled",false);
}

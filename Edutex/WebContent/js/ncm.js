
function fecharModalNCM(){
	$('#maskNCM').hide();
	$('.window').hide();
	$('#boxesNCM').hide();
	$('html, body').css({
	    'overflow': 'auto',
	    'height': '100%'
	})
}

function fecharModal(){
	$('#mask').hide();
	$('.window').hide();
}

function habilitarNovoNCM() {
	fecharModal();
	$('#containerCamposNCM').show();
	$('#pesquisarContainer').hide();
	$('#containerCampoNCM').show();
	$('#btPesquisar').hide();
	
}

function desabilitarNovoNCM() {
	$('#containerCamposNCM').hide();
	$('#pesquisarContainer').show();
	$('#containerCampoNCM').show();
	$('#btPesquisar').show();
	$("#NCMForm")[0].reset();
	
}
function mostrarBoxNCM(){
	
	var id = $('icmsModal').attr('alt');
	$('#boxesNCM').show();
	var maskHeight = $(window).height();
	var maskWidth = $(window).width();

	$('#maskNCM').css({'width':maskWidth + 20,'height':maskHeight});

	$('#maskNCM').fadeIn(1000);	
	$('#maskNCM').fadeTo("slow",0.7);	

	//Get the window height and width
	var winH = $(window).height();
	var winW = $(window).width();
          
	$(id).css('top',  winH/4);
	$(id).css('left', winW/4);

	$(id).fadeIn(2000); 
	$('html, body').css({
	    'overflow': 'hidden',
	    'height': '100%'
	});

}

var tableData;
var pesquisou = false;
$(document).ready(function(){
   $(".icms_pop").hide();
   
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
  	  $('#btPesquisar').show();
      });
    $(".aba").hover(
      function(){$(this).addClass("ativa")},
      function(){$(this).removeClass("ativa")}
    );
	
	$(".bt_icms").click(function(){
		$(".icms_pop").fadeIn(200);
		 $(this).attr('alt','#dialog');
		mostrarBoxNCM();

	});
	
	$(".bt_cancel").click(function(){
		$(".icms_pop").fadeOut(200);
		$("#maskNCM").hide();
		
		$('html, body').css({
		    'overflow': 'auto',
		    'height': '100%'
		});
	});
	
	
	$( ":text" ).blur(function(){
			if (!$.isNumeric($(this).val()) || $(this).val() < 0){
				$(this).val("");
			}
		
	});
	
	$("form[name='NCMForm'] input[type=radio]").change(function(){
		$('#containerCamposNCM').hide();
		$('#pesquisarContainer').show();
		$('#btPesquisar').show();
		
		
	});

	
   $(this).on("dblclick","#tabelaICMSEditavel tbody tr td",function(){
	   tableData = $('#tabelaICMSEditavel').DataTable();
		 
	   
	   if ($('#tabelaICMSEditavel td > input').length > 0) {
			 return;
		 }
		 
		 
		 if ($(this).index() == 1) {
			 return;
		 }
		 
		 var conteudoOriginal = $(this).text();
		 var novoConteudo =  $("<input id='inputEstado'>", {type: 'text', value:conteudoOriginal, width: '40%'});
		 
		 $(this).html(novoConteudo.blur(this,function(event){
			 var conteudoNovo = $(this).val();
			 
			 
			 
			 if ($.isNumeric(conteudoNovo) && conteudoNovo > 0) {
				 $(this).parent().html(conteudoNovo);
				 tableData.cell(event.data).data(conteudoNovo).draw(); 
			     
				 
			 }else {
				 $(this).parent().html(0);
			 }
			 
		 }));
		 $(this).children().select();
    	
    	
    });
	
	


	$('#maskNCM').click(function () {
		$(this).hide();
		$('.icms_pop').hide();
		$('html, body').css({
		    'overflow': 'auto',
		    'height': 'auto'
		});
	});
	
	$(".bt_ok").click(function() {
		
		alert("Uhu, só fazer o metodo que você quiser..");
	});
	
	$("#btPesquisar").click(function() {
		var ncm = $("#ncmCampo").val().trim();
		var resultado = "";
		
		$("#textContainer1").hide();
		
		var inputRadio = $('form[name="NCMForm"] input[type=radio]'); 
		var checkRadio = 0;
	    $(inputRadio).each(function(){
				if ($(this).get(0).checked){
					checkRadio++;
				}
		})
		
		if(checkRadio == 0){
			 resultado  +=  "Finalidade da Nota fiscal obrigatório<br/>";
		}else{
			if(checkRadio > 1){
				resultado  +=  "Erro na checagem na finalidade da nota fisccal<br/>";
			}
		}
	
		
		if (ncm.length == 0) {
			resultado += "Por favor preencha o NCM<br/>";
		}
		
		if (resultado.length != 0) {
			$("#textContainer1 span").html(resultado);
			$("#textContainer1").show();
				 return false;
			
			
		}
		
		var finalidade = $('input[name=escolhaRadioFinalidade]:checked').val();
		
		$.ajax({
			type: 'POST',
			dataType : "json",
			url:  contextPath + '/ncm/gerenciaNcmAjax.do',
			beforeSend: function(){
				 $('html,body').css({'cursor':'wait'});
			},
			data: {
				ncm: ncm,
				finalidade:finalidade,
				acao: 'pesquisar'
			},
			success: function(resposta) {
				$("#resultBox").attr('style','padding: 4px;');
				$("#btPesquisar").attr('alt', '#dialog');
				
				var tabelaEstrutura = "<table id='tabelaICMSEditavel' class='tbicms'><thead>" +
				"<tr><td style='display:none' ></td><td>Estado</td><td>mva(%)</td><td>mva ajustado(%)</td>" +
				"</tr></thead><tbody></tbody></table><div class='bt_ok' style='float: left;margin-bottom:10px; position: relative;margin-left:10px;'" +
					"type='button' onclick='fecharModalNCM();'>Confirmar</div>";
				
				var responseStruct = resposta.sucess + "<br/><br/><br/><input class='bt_cancel space,close' type='button' value='cancelar' onclick='fecharModal();' />" +
				"<input class='bt_ok space,close' type='button' value='ok' onclick='habilitarNovoNCM();' />";
				
				if(resposta.novoNCM) {
					var ncm = $("#ncmCampo").val();
					$("#NCMForm")[0].reset();
					
					$('input[name=escolhaRadioFinalidade]').each(function(){
						if ($(this).val() == finalidade){
								$(this).prop('checked',true);
						}
					})
					$("#ncmCampo").val(ncm);
					
					$("#tpOperacao").val(1);
					
					$("#divTabela").html(tabelaEstrutura);
					
					$("#tabelaICMSEditavel tbody").html(resposta.tabelaImpostoNCMEstado);
					
					
					tableData = $('#tabelaICMSEditavel').dataTable( {
				        "language": {
				            "lengthMenu": "Mostrando _MENU_ registros por página",
				            "zeroRecords": "Nenhum registro encontrado",
				            "info": "Mostrando _PAGE_ de _PAGES_",
				            "infoEmpty": "Sem registros disponíveis",
				            "infoFiltered": "(filtrado de _MAX_ total de registros)"
				        },
				    } );   
					
					$("#resultBox").html('<br>' + responseStruct);
					mostrarBox();
					pesquisou = true;
				} else {
					
					$("#tpOperacao").val(0);
					
					if(resposta.erro != null) {
						var responseStructErro = " <br/><br/><input class='bt_ok space,close' type='button' value='cancelar' onclick='fecharModal();' />";
						$("#resultBox").html('<br>' + resposta.erro + responseStructErro);
						mostrarBox();
						return;
					}
					
					
					$("#cdNmc").val(resposta.ncmCod);
					
					if (resposta.icms != null) {
						$("#icmsCampo").val(resposta.icms);
					}
				
					if (resposta.icmsInterestadual != null) {
						$("#icmsInterestadualCampo").val(resposta.icmsInterestadual);
					}
					
					if (resposta.ipi != null) {	
						$("#ipiCampo").val(resposta.ipi);
					}
					
					if (resposta.pis != null) {
						$("#pisCampo").val(resposta.pis);
							
					}	
					
					if (resposta.cofins != null) {
						$("#cofinsCampo").val(resposta.cofins);
					}		
					
					if (resposta.cst != null) {
						$("#cst").val(resposta.cst);
					}
					
					if (resposta.finalidade != null) {
						$('input[name=escolhaRadioFinalidade]').each(function(){
								if ($(this).val() == resposta.finalidade){
										$(this).prop('checked',true);
								}
						})
					}
					
					if (resposta.percentualReducao != null) {
						$("#percReducao").val(resposta.percentualReducao);
					}
					
					$("#divTabela").html(tabelaEstrutura);
					
					$("#tabelaICMSEditavel tbody").html(resposta.tabelaImpostoNCMEstado);
					
					tableData =	$('#tabelaICMSEditavel').dataTable( {
				        "language": {
				            "lengthMenu": "Mostrando _MENU_ registros por página",
				            "zeroRecords": "Nenhum registro encontrado",
				            "info": "Mostrando _PAGE_ de _PAGES_",
				            "infoEmpty": "Sem registros disponíveis",
				            "infoFiltered": "(filtrado de _MAX_ total de registros)"
				        },
				    } ); 
					
					
					$('#containerCamposNCM').show();
					$('#pesquisarContainer').hide();
					$('#containerCampoNCM').show();
					$('#btPesquisar').hide();
				}
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
  


	
	$("#btSalvarEditarNCM").click(function(){
		var resultado= "";
		
		$("#textContainer2 span").html("");
		
		if($("#icmsCampo").val().length == 0) {
			resultado += "O valor do imposto para ICMS é inválido <br/>";
		}
		
		
		if ($("#icmsInterestadualCampo").val().length == 0) {
			resultado += "O valor do imposto ICMS interestadual é obrigatório <br/>";
		}
		
		if ($("#ipiCampo").val().length == 0) {
			resultado += "O valor do imposto IPI é obrigatório <br/>";
		}
		
		if ($("#pisCampo").val().length == 0) {
			resultado += "O valor do imposto PIS é obrigatório <br/>";
		}
		
		if ($("#cofinsCampo").val().length == 0) {
			resultado += "O valor do imposto COFINS é obrigatório <br/>";
		}
		
		if ($("#percReducao").val().length == 0) {
			resultado += "O valor do percentual do redução é obrigatório <br/>";
		}
		
		if ($("#cst").val().length == 0 ) {
			resultado += "O valor do CST é obrigatório";
		}
		
		
		var inputRadio = $('form[name="NCMForm"] input[type=radio]'); 
		var checkRadio = 0;
	    $(inputRadio).each(function(){
				if ($(this).get(0).checked){
					checkRadio++;
				}
		})
		
		if(checkRadio == 0){
			 resultado  +=  "Finalidade da Nota fiscal obrigatório<br/>";
		}else{
			if(checkRadio > 1){
				resultado  +=  "Erro na checagem na finalidade da nota fisccal<br/>";
			}
		}
	
	    
		if(resultado.trim().length >= 1) {
			 $("#textContainer2 span").html(resultado);
			 $("#textContainer2").show();
			 return false;
		}
		
		
		
	tableData = $('#tabelaICMSEditavel').DataTable();
	  
		
	    
	    var i = 0, j = 0;
	    var impostoEstadoArray = new Array();
	    
	    
	    for (i = 0; i < tableData.rows().data().length;i++) {
	    	 var impostoEstado  = new Object();
	    	 
	    	for (j = 0; j < 4; j++) {
		    	 
		    		if (j == 0) {
		        		impostoEstado.idEstado = tableData.cell(i,0).data();
		        	}
		        	
		        	if (j == 2) {
		        		impostoEstado.mva = tableData.cell(i,2).data();
		        	}
		        	
		        	if (j == 3) {
		        		impostoEstado.mvaAjustado = tableData.cell(i,3).data();
		        	}
		        	
	    		
	    	}
	    	
	    	impostoEstadoArray.push(impostoEstado);
	    	
	    }
	    
	    var operacao;
	    
	    if ($('#tpOperacao').val() == 1) {
	    	operacao = "inserir";
	    } else {
	    	operacao = "atualizar";
	    }
	    

		$.ajax({
			type: 'POST',
			dataType : "json",
			url:  contextPath + '/ncm/gerenciaNcmAjax.do',
			beforeSend: function(){
				 $('html,body').css({'cursor':'wait'});
			},
			data: {
				ncm: $("#ncmCampo").val(),
				finalidade: $('input[name=escolhaRadioFinalidade]:checked').val(),
				icms: $("#icmsCampo").val(),
				icmsInterestadual: $("#icmsInterestadualCampo").val(),
				ipi: $("#ipiCampo").val(),
				pis: $("#pisCampo").val(),
				cofins: $("#cofinsCampo").val(),
				percReducao: $("#percReducao").val(),
				cst: $("#cst").val(),
				impostosEstado: "{listaImpostoEstado:" + JSON.stringify(impostoEstadoArray) + "}",
				acao: operacao
			},
			success: function(resposta) {
				$("#resultBox").attr('style','padding: 4px;');
				$("#btSalvarEditarNCM").attr('alt', '#dialog');
				
				var responseStruct = " <br/><br/><input class='bt_ok space,close' type='button' value='cancelar' onclick='fecharModal();desabilitarNovoNCM();' />";
				
				if (resposta.erro != null) {
					$("#resultBox").html('<br>' + resposta.erro + responseStruct);
				} else {
					$("#resultBox").html('<br>' + resposta.sucesso  + responseStruct);
				}
				
				
				
				mostrarBox();
			
			},
		}).fail(function(jqXHR, textStatus, errorThrown){
			$("#resultBox").attr('style','padding: 4px;');
			var responseStruct = "erro. Por favor contacte com o administrador do sistema erro. Por favor contacte com o administrador do sistema  <br/><br/><br/><input class='bt_ok space,close' type='button' value='cancelar' onclick='fecharModal();' />";
			$("#resultBox").html(responseStruct);
			$("#btPesquisarAba2").attr('alt', '#dialog');
			mostrarBox();
			
		}).done(function(){
			 $('html,body').css({'cursor':'default'});	
		})
	
		
	})
	
  	$('#ncmCampo').on('keyup', function(e) {
		    if (e.which == 8 || $('#ncmCampo').val().length == 0) {
		    	$('#btPesquisar').show();
		    $('#containerCamposNCM').hide();
			$('#containerCampoNCM').show();
		    	e.preventDefault();
		    }
		});
	
		
});
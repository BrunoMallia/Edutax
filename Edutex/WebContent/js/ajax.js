$(function(){
	$('form#adEmpForm').submit(function(){
		$.blockUI(); 
		$.ajax({
			type: 'POST',
			url: 'gerenciaEmpresa.do', 
			data: {
				cnpj: $('imput [name=cnpj]'),
				acao: 'inserir'
			}	
		}).done(function(e){
			alert(e);
		});
	})
	
})
/**
 * Metodos jsavascript para a tela de Relatorio
 */



$(document).ready(function(){
    
    $("#nota_fiscal").change(function () {
    	
    	relatorio = $(this).val();
    	if(relatorio == "nada"){
    		
    	}else if(relatorio == "metas"){
    		
    	}else if(relatorio == "metas_falhas"){
    		
    	}else if(relatorio == "atacadista"){
    		
    	}else if(relatorio == "aliquita"){
    		
    	};
    	
    });
    
    $("#btCancelar").click(function () {
    	$(window.document.location).attr('href','menu.do');
    });
    
    $("#btGerar").click(function () {
    	
    	var error = "";
    	
    	
    	if ($("#tipoRelatorio").val() == '0') {
    		error = error + "- Escolha um tipo de relatório <br/>"
    		
    	}
    	
    	try {
    		
    		
    	
    	var dataInicio = $.datepicker.parseDate('dd/mm/yy', $("#txtFromDate").val());
    	var dataFim = $.datepicker.parseDate('dd/mm/yy', $("#txtToDate").val()) ;
    	
   
    	if (dataFim != null) {
    		if ( dataInicio > dataFim ) {
        		error = error + "- A data de Início deve ser menor que a data de fim <br/>" 
        		
        	}
    	} 
    	
    	
    	
    	if(dataInicio != null && !validarData($.datepicker.formatDate('dd/mm/yy', dataInicio))) {
    		error = error + "- Formato da data de Início está inválida";
    	}
    	
    	if (dataFim != null && !validarData($.datepicker.formatDate('dd/mm/yy',dataFim))) {
    		error = error + "Formato da data de Fim está inválida";
    	}
    	
    	
    	 if (error.length >= 1) {
    		 $(".messageError").html(error);
    		 return false;
    	 }
    	 
    		return true;
    		//$(window.document.location).attr('href','gerarRelatorio.do');
    	} catch (err) {
    		$(".messageError").html("Data inválida");
    		return false;
    	}
    });
    
    $("#txtFromDate").datepicker({
    	dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
        nextText: 'Próximo',
        prevText: 'Anterior',
        onSelect: function(selected) {
         // $("#txtToDate").datepicker("option","minDate", selected)
        }
    });
    $("#txtToDate").datepicker({ 
    	dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
        nextText: 'Próximo',
        prevText: 'Anterior',
        onSelect: function(selected) {
           //$("#txtFromDate").datepicker("option","maxDate", selected)
        }
    });  
    
});



/**
 * Metodos jsavascript para a tela de Relatorio
 */

$(document).ready(function(){
    
    $("#nota_fiscal").change(function () {
    	alert ($(this).val());
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
    	$(window.document.location).attr('href','gerarRelatorio.do');
    });
    
    $("#txtFromDate").datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
          $("#txtToDate").datepicker("option","minDate", selected)
        }
    });
    $("#txtToDate").datepicker({ 
        numberOfMonths: 2,
        onSelect: function(selected) {
           $("#txtFromDate").datepicker("option","maxDate", selected)
        }
    });  
    
});



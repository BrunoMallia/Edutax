$(document).ready(function() {
	
	$("#btloginAut").click(function(){
		var result = "";
		
		 $("#textContainer1 span").html("");
		
		 if ($("#login").val().length == 0) {
			 result += "Digite o nome do seu usuário<br>";
			 $("#textContainer1 span").html(result);
			 $("#textContainer1").show();
			 return false;
		 }
		 
		 
		 if ($("#password").val(),length < 5) {
			 result += "A sua senha precisa ter no minimo 5 dígitos<br>";
			 $("#textContainer1 span").html(result);
			 $("#textContainer1").show();
			 return false;
		 }
		 	
		 $("#logaForm").submit();
	})
	
	
});
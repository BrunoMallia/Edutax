<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/header/header.jsp" />
</head>

	  
<script type="text/javascript">
  $(document).ready(function(){
 
 
   $('ul#submenu_main>li').mouseover(function(){
        $(this).children('ul.submenu').css({'display':'block'});
    });
    
    $('ul#submenu_main>li').mouseout(function(){
        $(this).children('ul.submenu').css({'display':'none'});
    });
 
 
});
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
        
      </div>
      </div>
        
      <jsp:include page="/footer/rodape.jsp" />
    </body>
</html>
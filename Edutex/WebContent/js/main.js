$(document).ready(function(){
 
	$(".emprs").hide();
   $('ul#submenu_main>li').mouseover(function(){
        $(this).children('ul.submenu').css({'display':'block'});
    });
    
    $('ul#submenu_main>li').mouseout(function(){
        $(this).children('ul.submenu').css({'display':'none'});
    });
    
    //TODO ajustar para responder ao codigo
    $(".user_continue").click(function(){
		$(".lgn").hide();
		$(".emprs").show();
	});
});
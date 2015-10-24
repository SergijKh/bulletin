$(document).ready(function(){
		
//При нажатии на ссылку с классом poplight и href атрибутом тега <a> с # 'a.poplight[href^=#]'
	$('a.poplight[href^=#]').click(function() {
		var popID = $(this).attr('rel'); 
		var popURL = $(this).attr('href'); 
 
		//запрос и переменные из href url
		var query= popURL.split('?');
		var dim= query[1].split('&');
		var popWidth = dim[0].split('=')[1]; 
 

		$('#' + popID).fadeIn().css({ 'width': Number( popWidth ) }).prepend('<a href="#" title="Закрыть" class="close"></a>');
 
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
 
	
		$('#' + popID).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
 
	
		$('body').append('<div id="fade"></div>'); 
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); 
 
		return false;
	});
	$(document).on('click', 'a.close, #fade', function() { 
    $('#fade , .popup_block').fadeOut(function() {
        $('#fade, a.close').remove();  
    });
    return false;
   });
});



"harset=utf-8"
(function($) {
	// block search text field name user
	$(function() {
		$('#testcheckbox').change(function() {
			$('#testtextfield').val('');
			 if($('#testcheckbox').is(':checked')) {
				 $('#testcheckbox').val('1');
			 } else{
				 $('#testcheckbox').val('0');
			 }
			
			$('#testtextfield').attr('disabled', this.checked)

		});
	});
	// delete advertisement by id
	$(function() {
		
		$(".deleteAdvertism").click(function(event) {
			var deleteLink = $(".deleteAdvertism");
			
				

			
			$.ajax({
				cache:false,
				url : "./delete/" + $(deleteLink).val(),
				type : "DELETE",
				
				success : function(result) {
					 
					 $(".id" + $(deleteLink).val()).remove();
					
				 
					$("#body_center").html(respContent);
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText + '|\n' + status + '|\n' + error);
				}

			});

			event.preventDefault();
		});

	});
	
	

})(jQuery);


//search list advertisement
function showValues() {
	
	//var str = $("#formx").serialize();
	if(!$('#newsletterCheckbox').is(':checked')) {
	var select_rubric = $("#selectRubric").val();
	 var nameUser = $("#testtextfield").val();
	 var myAds = $("#testcheckbox").val();
	 if(!$('#testcheckbox').is(':checked')) {
	 }
	 }
	 if (nameUser == ''){
		 nameUser = "empty";
	 }
	 if (myAds == ''){
		 myAds = "empty";
	 }
	  var data = 'select_rubric='+encodeURIComponent(select_rubric)+'&nameUser='+encodeURIComponent(nameUser)+'&myAds='+myAds;
	$.ajax({
		 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json", 
		type : 'POST',
		url : './search',
		data : data,
		 
		success : function(data) {
			var _len = data.length;
			 var post, i;

			  for (i = 0; i < _len; i++) {
			    //debugger
			    post = data[i];
			    alert("m_positionName is "+ post.login.nameUser);
			  }
			alert(data);
		},
		/*error : function(xhr, status, error) {
			alert(xhr.responseText + '|\n' + status + '|\n' + error);
		}*/
	});
 
    }
	



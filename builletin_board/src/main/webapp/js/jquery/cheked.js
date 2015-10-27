(function($) {
	// block search text field name user

	

	$(function() {
		
		$('body').on('click','.deleteAdvertism',function(event){

			var deleteLink = $(".deleteAdvertism");
		
			$(this).prop("disabled", true);
			$.ajax({
				cache : false,
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

		
		
		$('#testcheckbox').change(function() {
			$('#testtextfield').val('');
			if ($('#testcheckbox').is(':checked')) {
				$('#testcheckbox').val('1');

			} else {
				$('#testcheckbox').val('0');

			}

			$('#testtextfield').attr('disabled', this.checked)

		});
	});
	// delete advertisement by id
	
	

})(jQuery);

// search list advertisement
function showValues(event) {

	var select_rubric = $("#selectRubric").val();
	var nameUser = $("#testtextfield").val();
	var myAds = $("#testcheckbox").val();

	if (nameUser == '') {
		nameUser = null;
	}
	
	var data = 'select_rubric=' + encodeURIComponent(select_rubric)
			+ '&nameUser=' + encodeURIComponent(nameUser) + '&myAds=' + myAds;
	
	      $.ajax({
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				dataType : "json",
				type : 'POST',
				url : './search',
				data : data,

				success : function(dataj) {
					if (dataj != null){
					var _len = dataj.length;
					var post, i, newElems;
					$("#body_center").empty();
				    if (_len > 0){
					for (i = 0; i < _len; i++) {
						  post = dataj[i];
						var newElemsDiv = $("<div class ='name_content id"
								+ post.idAdvertisement + "'></div>");
						var newElemstabl = $("<table class='tabl'> </table>");
						 var newElemsTRNmaeRubric = $("<tr><td>" + post.rubric
									+ "</td>  </tr>");
						$(newElemstabl).append(newElemsTRNmaeRubric);
						var newElemsTRNmaeUser = $("<tr></tr>");
						var newElemstdNmaeUser = $("<td width='90%'>"
								+ post.login.nameUser + " </td>");
				
						$(newElemsTRNmaeUser).append(newElemstdNmaeUser);
						if ($('#testcheckbox').is(':checked')) {
							var newElemstButtonDelete = $("<td> <button class='deleteAdvertism' value= "+post.idAdvertisement +" type='button'"
									+ "> удалить</button></td>");
							$(newElemsTRNmaeUser).append(newElemstButtonDelete);
						}
						$(newElemstabl).append(newElemsTRNmaeUser);
						var dateT  = new Date(post.modifiedDate);
						newdate = dateT.getMonth() + '.' + dateT.getDate() + '.' + dateT.getFullYear();
			            var newElemsTRNmaeDate = $("<tr><td>" + newdate
								+ "</td>  </tr>");
						var newElemsTRNmaeTitle = $("<tr><td>" + post.title
								+ "</td>  </tr>");
						var newElemsTRNmaeText = ("<tr><td>" + post.text + "</td>  </tr>");
			            $(newElemstabl).append(newElemsTRNmaeDate);
						 $(newElemstabl).append(newElemsTRNmaeTitle)
						 $(newElemstabl).append(newElemsTRNmaeText);
			            
						$(newElemsDiv).append(newElemstabl);
						
						$("#body_center").append(newElemsDiv);
					
					}
				    }else{
				    	 var newElemsEmpty = $("<p> по вашему запросу нет данных </p>");
				    	 $("#body_center").append(newElemsEmpty);
				    }
				
					
				}else{
			    	 var newElemsEmpty = $("<p> пройдите авторизацию </p>");
			    	 $("#body_center").append(newElemsEmpty);
			    }
					 ("#body_center").html(respContent);		
				},
			
			 error : function(xhr, status, error) { alert(xhr.responseText +
			 '|\n' + status + '|\n' + error); }
			 
			});
	event.preventDefault();

}


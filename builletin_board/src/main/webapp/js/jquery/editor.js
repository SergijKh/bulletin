(function($) {
	 $(function(){ 
		 $(document).on('change', '#formCreateTitle', jVal.title);
		 $(document).on('change', '#formCreateArea', jVal.area);
	 });
	$(function() {
		// editor advertisement form
		var ARRAY_RUBRIC = ["все","продажа","покупка","аренда","услуги","знакомства"];
		$('body')
				.on('click','.redactAdvertism',
						function(event) {
							var deleteLink = $(".redactAdvertism");
							$(this).prop("disabled", true);
							var newElemsDiv = $("<div class ='name_content id"
									+ $(this).val() + "'></div>");
							var newElemsForm = $("<form id='formAdver'> </form>");
							var newElemstabl = $("<table class='tabl'> </table>");
							var newElemsTRNmaeRubric = $("<tr></tr>");
							var newElemsSelTd =$("<td>  </td>");
							 var  ElementP = $("<p>Рубрика</p>");
							var newElemsSelect=$("<select id='formCreateRubric' size='1' name='select_rubric' value ="+ $("#rubricEd").text()+"></select>");
							for (var i =0; i < ARRAY_RUBRIC.length ;i++){
								 var newElemsList= $(" <option value="+ARRAY_RUBRIC[i]+">"+ARRAY_RUBRIC[i]+"</option>");
								 $(newElemsSelect).append(newElemsList);
							 }
							 $(newElemsSelTd).append(ElementP);
							 $(newElemsSelTd).append(newElemsSelect);
							 $(newElemsTRNmaeRubric).append(newElemsSelTd);
							var newElemsTRNmaeTitle = $("<tr><td> <p>Заголовок<Br> "
									+ "<input id='formCreateTitle' type='text'name='title' size='40'value ="+$("#titleEd").text() +"></p> </td> </tr>");
							var newElemsTRNmaeText = ("<tr><td > <p>Текст<Br> "
									 +"<textarea id='formCreateArea' name='tekst' cols='60' rows='6'>"+$("#textrEd").text()+"</textarea></p> </td>  </tr>")
							var newElemstButtonEditor = $("<tr><td> <p> <button class='buttonChenge' id='marge'value='"+$("#hidden").val()+"' " +
									"onclick ='editAdvertis()'>отправить</button> <input type =reset onclick ='clearform()' Очистить </></p></td></tr>");
							$(newElemstabl).append(newElemsTRNmaeRubric);
							$(newElemstabl).append(newElemsTRNmaeTitle);
							$(newElemstabl).append(newElemsTRNmaeText);
							$(newElemstabl).append(newElemstButtonEditor);
							$(newElemsForm).append(newElemstabl);
							$(newElemsDiv).append(newElemsForm);
							$("#body_center").empty();
							$("#body_center").append(newElemsDiv);
							$("#body_center").html(respContent);
							//alert($(this).val());
							("#body_center").html(respContent);
						});
	});
	
	
	//creaate adverisement form 
	$(function() {
		$("#creatAd")
				.click(
						function(event) {

					var ARRAY_RUBRIC = ["продажа","покупка","аренда","услуги","знакомства"];
							var deleteLink = $(".redactAdvertism");

							var newElemsDiv = $("<div class ='name_content id"
									+ $(this).val() + "'></div>");
							var newElemsForm = $("<form id='formAdver'> </form>");
							var newElemstabl = $("<table class='tabl'> </table>");
							
							var newElemsTRNmaeRubric = $("<tr></tr>");
							var newElemsSelTd =$("<td>  </td>");
							 var  ElementP = $("<p>Рубрика</p>");
							var newElemsSelect=$("<select id='formCreateRubric' size='1' name='select_rubric' ></select>");
							
							for (var i =0; i < ARRAY_RUBRIC.length ;i++){
								 var newElemsList= $(" <option value="+ARRAY_RUBRIC[i]+">"+ARRAY_RUBRIC[i]+"</option>");
								 $(newElemsSelect).append(newElemsList);
							 }
							 $(newElemsSelTd).append(ElementP);
							 $(newElemsSelTd).append(newElemsSelect);
							 $(newElemsTRNmaeRubric).append(newElemsSelTd);
							var newElemsTRNmaeTitle = $("<tr><td id = 'tdTitle'> <p>Заголовок<Br> "
									+ "<input id='formCreateTitle' type='text' size='40'></p> </td> </tr>");
							
							var newElemsTRNmaeText = ("<tr><td > <p>Текст<Br> "
									 +"<textarea id='formCreateArea' name='tekst' cols='60' rows='6'></textarea></p> </td>  </tr>");
						
							var newElemstButtonEditor = $("<tr><td> <p> <button class='buttonChenge'  id='save' onclick ='saveAdvertisment()'>сохранить</button> <input type = reset  onclick ='clearform()' value =Очистить /></p></td></tr>");
							
							
							$(newElemstabl).append(newElemsTRNmaeRubric);
							$(newElemstabl).append(newElemsTRNmaeTitle);
							$(newElemstabl).append(newElemsTRNmaeText);
							$(newElemstabl).append(newElemstButtonEditor);
							$(newElemsForm ).append(newElemstabl);
							$(newElemsDiv).append(newElemsForm );
							$("#body_center").empty();
							$("#body_center").append(newElemsDiv);
							$("#body_center").html(respContent);
							//alert($(this).val());
							("#body_center").html(respContent);
						});
	});
//function return list  advertisement single user 
	$(function() {
		$("#listAd")
				.click(
						function(event) {

							$
									.ajax({
										contentType : "application/x-www-form-urlencoded; charset=UTF-8",
										dataType : "json",
										type : 'POST',
										url : './myList',
										success : function(dataj) {
											if(dataj.length != 0){
											var _len = dataj.length;
											var post, i, newElems;
											$("#body_center").empty();
											if (_len > 0) {
												for (i = 0; i < _len; i++) {
													post = dataj[i];
													var newElemsDiv = $("<div class ='name_content id"
															+ post.idAdvertisement
															+ "'><input id ='hidden' type='hidden' value='"+post.idAdvertisement+"'></div>");
													var newElemstabl = $("<table class='tabl'> </table>");
													var newElemsTRNmaeRubric = $("<tr><td id = 'rubricEd'>"
															+ post.rubric
															+ "</td>  </tr>");
													$(newElemstabl)
															.append(
																	newElemsTRNmaeRubric);
													var newElemsTRNmaeUser = $("<tr></tr>");
													var newElemstdNmaeUser = $("<td id = 'nameUserEd' width='90%'>"
															+ post.login.nameUser
															+ " </td>");

													$(newElemsTRNmaeUser)
															.append(
																	newElemstdNmaeUser);
													var newElemstButtonDelete = $("<td> <button class='deleteAdvertism' type='button' value= "
															+ post.idAdvertisement
															+ "> удалить</button><button class='redactAdvertism' type='button' value= "
															+ post.idAdvertisement
															+ "> редактировать</button></td>");
													$(newElemsTRNmaeUser)
															.append(
																	newElemstButtonDelete);

													$(newElemstabl).append(
															newElemsTRNmaeUser);
													var dateT = new Date(
															post.modifiedDate);
													newdate = dateT.getMonth()
															+ '.'
															+ dateT.getDate()
															+ '.'
															+ dateT
																	.getFullYear();
													var newElemsTRNmaeDate = $("<tr><td>"
															+ newdate
															+ "</td>  </tr>");
													var newElemsTRNmaeTitle = $("<tr><td id = 'titleEd'>"
															+ post.title
															+ "</td>  </tr>");
													var newElemsTRNmaeText = ("<tr><td id = 'textrEd' >"
															+ post.text + "</td>  </tr>");
													$(newElemstabl).append(
															newElemsTRNmaeDate);
													$(newElemstabl)
															.append(
																	newElemsTRNmaeTitle)
													$(newElemstabl).append(
															newElemsTRNmaeText);

													$(newElemsDiv).append(
															newElemstabl);

													$("#body_center").append(
															newElemsDiv);

												}
											} else {
												var newElemsEmpty = $("<p> по вашему запросу нет данных </p>");
												$("#body_center").append(
														newElemsEmpty);
											}
											("#body_center").html(respContent);

										}else{$("#body_center").empty();
											var newElemsEmpty = $("<p> Для доступа к данным данным, пожалуйста, войдите на сайт  </p>");
											$("#body_center").append(
													newElemsEmpty);
											("#body_center").html(respContent);
										}
											},

										error : function(xhr, status, error) {
											alert(xhr.responseText + '|\n'
													+ status + '|\n' + error);
										}

									});
							event.preventDefault();
						});
	});

})(jQuery);
//clear form  editor input 
function clearform() {
	$("input:text").val('');
	$("textarea").val('');

}
 counErrorTitle=0;
 counErrorArea=0;
// send form editor to send server
function editAdvertis(){
	var select_rubric = $("#formCreateRubric").val();
	var  title = $("#formCreateTitle").val();
	var text = $("#formCreateArea").val();
    var idAdvertisement = $("#marge").val();
    $('#body_center').append('<span id="nameInfo" class="info"></span>');
	var nameInfo = $('#nameInfo');
    var ele = $('#formCreateTitle');
	var pos = ele.position();
	nameInfo.css({
        top: pos.top,
        left: pos.left+ ele.width()+10
        });
    if(title.length == 0) {
    	nameInfo.removeClass('correct').addClass('error').html('< заголовок должен быть минимум 10 символа').show();
        ele.removeClass('normal').addClass('wrong');
    	 $('.buttonChenge').prop('disabled',true);
    	 if ( counErrorTitle==1){
        	 counErrorTitle--;
           } 
        counErrorTitle++;
    }
	var data = 'idAdvertisement=' + encodeURIComponent(idAdvertisement)	+ '&rubric=' + encodeURIComponent(select_rubric)+'&title=' + encodeURIComponent(title) + '&text=' + encodeURIComponent(text);
	
	      $.ajax({
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : 'POST',
				url : './update',
				data : data,
				success : function(dataj) {
					if(dataj=="ok"){
						nameInfo.removeClass('error').addClass('correct');
						alert(" вы изминмли объявление");
						}else{
							alert("введите корректно свои данные");
						}	
				},
			 error : function(xhr, status, error) { alert(xhr.responseText +
			 '|\n' + status + '|\n' + error); }
			 
			});
	event.preventDefault();

	
}
//create new Advertisement and seve on base
function saveAdvertisment(){
	var select_rubric = $("#formCreateRubric").val();
	var  title = $("#formCreateTitle").val();
	var text = $("#formCreateArea").val();
	 $('#body_center').append('<span id="nameInfo" class="info"></span>');
		var nameInfo = $('#nameInfo');
	    var ele = $('#formCreateTitle');
		var pos = ele.position();
		nameInfo.css({
	        top: pos.top,
	        left: pos.left+ ele.width()+10
	        });
	    if(title.length == 0) {
	    	nameInfo.removeClass('correct').addClass('error').html('<заголовок должен быть минимум 10 символа').show();
	        ele.removeClass('normal').addClass('wrong');
	    	 $('.buttonChenge').prop('disabled',true);
	    	 if ( counErrorTitle==1){
            	 counErrorTitle--;
               } 
            counErrorTitle++;
	    }
	
	var data = 'rubric=' + encodeURIComponent(select_rubric)+'&title=' + encodeURIComponent(title) + '&text=' + encodeURIComponent(text);
	 if(title.length == 0) {
    	 $('.buttonChenge').prop('disabled',true);
    }
	      $.ajax({
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : 'POST',
				url : './create',
				data : data,
				success : function(dataj) {
					 nameInfo.removeClass('error').addClass('correct');
					if(dataj=="ok"){
						alert(" вы создали объявление");
					} else if (dataj=="notCorrect"){
						alert(" введите корректно данные");
					} else if(dataj=="error") {
						alert(" войдите на сайт");
					}
					
				},
			
			 error : function(xhr, status, error) { alert(xhr.responseText +
			 '|\n' + status + '|\n' + error); }
			 
			});
	event.preventDefault();

	
}
//validator careate and editor advertisment

	jVal =  {
			'title' : function() {
				$('#body_center').append('<span id="nameInfo" class="info"></span>');
				var nameInfo = $('#nameInfo');
				var ele = $('#formCreateTitle');
				var pos = ele.position();
				nameInfo.css({
			        top: pos.top,
			        left: pos.left+ ele.width()+10
			        });
				if(ele.val().length < 10) {
			        jVal.errors = true;
			                nameInfo.removeClass('correct').addClass('error').html('< заголовок должен быть минимум 10 символа').show();
			                ele.removeClass('normal').addClass('wrong');
			                $('.buttonChenge').prop('disabled',true);
			                if ( counErrorTitle==1){
			                	 counErrorTitle--;
				               } 
			                counErrorTitle++;
			} else if(ele.val().length > 30){
				 nameInfo.removeClass('correct').addClass('error').html('< Максимальное число символо - 30').show();
	                ele.removeClass('normal').addClass('wrong');
	                $('.buttonChenge').prop('disabled',true);
	                if ( counErrorTitle==1){
	            	   counErrorTitle--;
	               } 
	               counErrorTitle++;
	             
			}
			else {
				          
			                nameInfo.removeClass('error').addClass('correct').html('v').show();
			                ele.removeClass('wrong').addClass('normal');
			                if(counErrorTitle==1){
			                counErrorTitle--;
			                }
			                if( counErrorTitle == 0 && counErrorArea == 0){
			                $('.buttonChenge').prop('disabled',false);
			                 }
			}
	        },
	      'area':function(){
	    	  $('#body_center').append('<span id="nameInfoarea" class="info"></span>');
				var nameInfo = $('#nameInfoarea');
				var ele = $('#formCreateArea');
				var pos = ele.position();
				nameInfo.css({
			        top: pos.top,
			        left: pos.left+ ele.width()+10
			        });
				if(ele.val().length < 20) {
			        jVal.errors = true;
			                nameInfo.removeClass('correct').addClass('error').html('< заголовок должен быть минимум 20 символа').show();
			                ele.removeClass('normal').addClass('wrong');
			                $('.buttonChenge').prop('disabled',true);
			                if ( counErrorArea==1){
			                	counErrorArea--;
				               } 
			                counErrorArea++;
			} else if(ele.val().length > 400){
				 nameInfo.removeClass('correct').addClass('error').html('< Максимальное число символо - 400').show();
	                ele.removeClass('normal').addClass('wrong');
	                $('.buttonChenge').prop('disabled',true);
	                if ( counErrorArea==1){
	                	counErrorArea--;
		               } 
	                counErrorArea++;
			}
			else {
			         nameInfo.removeClass('error').addClass('correct').html('v').show();
			         ele.removeClass('wrong').addClass('normal');
			         ( counErrorArea==1){
		                	counErrorArea--;
			         }
		                if(  counErrorArea == 0 && counErrorTitle==0){
		                $('.buttonChenge').prop('disabled',false);
		                 }
			}
	      }
	};
	

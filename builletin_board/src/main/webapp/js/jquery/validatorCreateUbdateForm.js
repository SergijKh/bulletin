/**
 * 
 */

$(document).ready(function() {
	jQuery.validator.setDefaults({
		  debug: true,
		  success: "valid"
		});

	$("#formAdver").validate({
						
						submitHandler: function (form){
				        
				            form.submit();

						},
			rules: {
				title: {
					required: true,
					minlength: 2
				},
				tekst: {
					required: true,
					minlength: 5
				},
				
				
			},
			messages: {
				title: {
					required: "Это поле обязательно для заполнения",
	                minlength: "Логин должен быть минимум 3 символа",
	                maxlength: "Максимальное число символо - 16"
				},
				tekst: {
					required: "Это поле обязательно для заполнения",
	                minlength: "Пароль должен быть минимум 5 символа",
	                maxlength: "Пароль должен быть максимум 16 символов"
				},
				
			}
		});
});
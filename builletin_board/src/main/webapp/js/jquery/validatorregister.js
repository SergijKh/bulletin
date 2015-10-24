/**
 * 
 */

$(document).ready(function() {
	jQuery.validator.setDefaults({
		  debug: true,
		  success: "valid"
		});
		$("#commentForm").validate({
			
			submitHandler: function (form){
	        
	            form.submit();

			},
			
			rules: {
				login: {
					required: true,
					minlength: 2
				},
				password: {
					required: true,
					minlength: 5
				},
				secondPassword: {
					required: true,
					minlength: 5,
					equalTo:"#password"
				},
				email: {
					required: true,
					email: true
				},
				
				
			},
			messages: {
				login: {
					required: "Это поле обязательно для заполнения",
	                minlength: "Логин должен быть минимум 3 символа",
	                maxlength: "Максимальное число символо - 16"
				},
				password: {
					required: "Это поле обязательно для заполнения",
	                minlength: "Пароль должен быть минимум 5 символа",
	                maxlength: "Пароль должен быть максимум 16 символов"
				},
				secondPassword: {
					required: "Это поле обязательно для заполнения",
	                minlength: "Пароль должен быть минимум 5 символа",
	                maxlength: "Пароль должен быть максимум 16 символов",
	                equalTo: "Пароли не совпадают"
				},
				email: {
					required: "Это поле обязательно для заполнения",
					email: "некорректный E-mail"
				}
			}
		});
});
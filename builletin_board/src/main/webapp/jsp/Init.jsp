<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8">
<link href="<c:url value="/css/modalwindows.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/js/jquery/jquery-1.11.3.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/js/jquery/jquery.validate.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery/validatorregister.js" />"></script>
<title>  </title>
</head>
<body>
 <div id = "sign_login">
    <form  id = 'commentForm' action="../signlogin" method="POST"> 
     <input type = "hidden" name="initlogin" value= "signup"/> 
        <fieldset>  
            <legend> пожалуйста введите свои данные  </legend>  
            <table>  
                <tr>  
                    <td> логин:</td>  
                    <td><input id = 'loginForm'type="text" name="login"  /></td>  
                </tr>  
                <tr>  
                    <td>пароль:</td>  
                    <td><input  id = 'password'type="password" name="password"  /></td>  
                </tr> 
                <tr>  
                    <td> пароль еще раз:</td>  
                    <td><input id = 'passwordFormTwoo' type="password" name="secondPassword" /></td>  
                </tr>  
                <tr>  
                    <td>имя:</td>  
                    <td><input id = 'nameUs' type="text" name="nameUser"/></td>  
                </tr>  
               
                <tr>  
                    <td><input type="submit" value="регистрация" /></td>  
                </tr>  
            </table>  
        </fieldset>  
    </form>  
   
 </div>

</body>
</html>
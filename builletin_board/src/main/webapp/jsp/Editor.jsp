<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sing" uri="signup"%>
<%@ taglib prefix="search" uri="search_ads"%>
<%@ taglib prefix="listRedact" uri="list_redact"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="<c:url value="js/jquery/jquery-1.11.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="js/jquery/jquerymodalwindows.js" />"></script>
<script type="text/javascript"
	src="<c:url value="js/jquery/cheked.js" />"></script>
<script type="text/javascript"
	src="<c:url value="js/jquery/editor.js" />"></script>
<script type="text/javascript"
	src="<c:url value="js/jquery/validatorregister.js" />"></script>
<script type="text/javascript"
	src="<c:url value="js/jquery/validatorCreateUbdateForm.js" />"></script>

<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<link href="<c:url value="css/workbuilder.css" />" rel="stylesheet">
<link href="<c:url value="css/modalwindows.css" />" rel="stylesheet">



<title>Insert title here</title>
</head>
<body>
	<c:set var="nameContent" scope="session" value="Editor" />
	<div id='main'>
		<div id='header'>
			<div id='head'>
				<%@include file="Header.jsp"%>
			</div>
		</div>
		<div id='body'>
			<div id='body_left_menu'>
				<div class="top_menu">
					<ul>
						<li><a href="./listAds">список объявлений </a></li>
						<li><a href="./editor">редактор объявлений</a></li>

					</ul>
				</div>

			</div>
			<div id='body_full'>
				<div id='body_center'>
					<c:if test="${myListAdvertisment != null}">
						<listRedact:LRedact
							listAdvert="${myListAdvertisment}" />

					</c:if>
					<c:if test="${empty myListAdvertisment}">
						<p>нет объявлений</p>

					</c:if>

				</div>
				<div id='body_right_menu'>
					<div id='enterSite'>
						<c:if test="${sessionScope.login == null}">
							<div id='go'>
								<a href="#?w=350" rel="popup_name" class="poplight"> <c:out
										value="Войти" /></a>
							</div>

						</c:if>

						<c:if test="${sessionScope.login != null}">
							<c:set var="name" scope="session" value="${sessionScope.login}" />
							<div id='enter_site_button'>
								<c:out value="${name.login}" />
							</div>
						</c:if>
					</div>
					<div id='search'>

						<div class="top_menu">
							<ul>
								<li><a id='listAd'>список объявлений </a></li>
								<li><a id='creatAd'>создать объявление</a></li>

							</ul>
						</div>


					</div>
				</div>
			</div>

		</div>
	</div>
	<div id="popup_name" class="popup_block">
		<sing:singUp />
	</div>
</body>
</html>
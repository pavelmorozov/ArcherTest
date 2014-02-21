<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Register</title>
		
		<link href="resources/style/style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="resources/js/jquery-1.9.1.js"> </script>
		<script type="text/javascript" src="resources/js/script.js"></script>
	</head>
	<body>
	
	<h1>Регистрация</h1>
	<form method="POST" action="regUser">
			<c:if test="${not empty message}">
				<p class="attention">${message}</p> 		
			</c:if>
			
			<table>
				<tr>
					<td align="right">Логин:</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td align="right">Пароль:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td align="right">Повтор пароля:</td>
					<td><input type="password" name="password_repeat" /></td>
				</tr>				
				<tr>
					<td colspan="2" align="right">
						<a href="<c:url value="/login" />">Войти</a>
						<input type="submit" value="Сохранить" />
					</td>
				</tr>
			</table>
		</form>	

	
		
	</body>
</html>
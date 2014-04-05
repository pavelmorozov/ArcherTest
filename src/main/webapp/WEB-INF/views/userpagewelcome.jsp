<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ArcherTest: ${userName}</title>
</head>
<body>
	<h1>${userName}, Ваш адрес подтвержден, теперь вы пользователь!</h1><br>
	<br>
	<br>
	<a href= "<c:url value="/userpage"/> ">Перейти на страницу пользователя</a>
	<a href= "<c:url value="/logout"/> ">Выйти из системы</a>
</body>
</html>
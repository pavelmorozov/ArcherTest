<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="objectList" scope="request" 
   type="org.springframework.beans.support.PagedListHolder"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="resources/style.css" type="text/css" rel="stylesheet">		
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="resources/script.js"></script>
			
<title>Archer. Управление балансами</title>
</head>
<body>
	<h1>Управление балансами пользователей</h1><br>
	
	<a href= "<c:url value="/registryRecords"/> ">Журнал пополнений</a>
	<form method="POST" action="<c:url value="/searchUser" />">
		<table>
			<tr>
				<td>
					Email
				</td>
				<td>
					<input type="text" name="searchString" value = ""/>
				</td>
				<td>
					<input type="submit" value="Поиск"/>
				</td>
			</tr>
		</table>
	</form>
	
	<c:if test="${!empty searchString}">
		<div>
			Показаны результаты поиска по запросу: ${searchString} <a href= "<c:url value="/adminpage"/> "> Управление балансами</a>
		</div> 
	</c:if>
	
	<c:if test="${!empty objectList.pageList}">
		<table class="data">
			<tr>
				<th>Email</th>
				<th>Баланс</th>
				<th>Дата регистрации</th>
			</tr>
			<c:forEach items="${objectList.pageList}" var="user">
				<tr>
					<!-- <td><a href="fillUser/${user.email}" class="userEmail">${user.email}</a></td> -->
					<td><a href="${user.id}" class="userId">${user.email}</a></td>
					<td id="balance-${user.id}">${user.balance}</td>
					<td><fmt:formatDate value="${user.created}" pattern="dd/MM/yyyy" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
	
	<c:if test="${!objectList.firstPage}">
    	<a href="${pagePath}?pageNavy=first">&lt;&lt; First</a>
  	</c:if>
	<c:if test="${!objectList.firstPage}">
    	<a href="${pagePath}?pageNavy=prev">&lt; Prev</a>
  	</c:if>
  	
  	<c:if test="${!empty pagesList}">
  		<c:forEach items="${pagesList}" var="pageNum">
  			<c:choose>
  				<c:when test="${currentPage==pageNum}">
	  				 ${pageNum}
  				</c:when>
  				<c:otherwise>
  					<a href="${pagePath}?pageNumber=${pageNum}"> ${pageNum} </a>
  				</c:otherwise> 
  			</c:choose>
  		</c:forEach>
  	</c:if>	
  	
	<c:if test="${!objectList.lastPage}">
    	<a href="${pagePath}?pageNavy=next">Next &gt;</a>
  	</c:if>
	<c:if test="${!objectList.lastPage}">
    	<a href="${pagePath}?pageNavy=last">Last &gt;&gt;</a>
  	</c:if>
  	<br>
  	<br>
  	<br>
  	
  	<div>
  		<a href= "<c:url value="/logout"/> ">Выйти из системы</a>
  	</div>
  	
  	<div id = "fillUserAccountPopUp">
  		<div id = "fillUserAccountPopUpHead">
  			<span>Пополнение баланса</span>
			<a class = "fillUserAccountPopUpCloseRef" href="X">X</a>
		</div>
		
		<form id = "fillUserAccount" method="GET" action = "fillUserAccount">
	  		<table>
	  			<tr class="userName">
	  				<td align="right">Пользователь</td>
	  				<td><div id = "userNameFill">UserName</div></td>  				
	  			</tr>
	  			<tr class="hideAfterFill">
					<td align="right">Сумма</td>
					<td><input type="text" id = "fillAmount"/></td>
	  			</tr>
				<tr class="hideAfterFill">
					<td colspan="2" align="right">
						<input class = "fillUserAccountPopUpCloseRef" type="button" value="Отмена" />
						<input type="submit" value="Пополнить" />
					</td>
				</tr>				  			
	  		</table>
		</form>
		<div id = 'fillResult'></div>
  	</div>
</body>
</html>
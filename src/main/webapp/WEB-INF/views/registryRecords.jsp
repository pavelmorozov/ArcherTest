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
			
<title>Archer. Журнал пополнений</title>
</head>
<body>
	<h1>Журнал пополнений баланса</h1><br>

	<a href= "<c:url value="/adminpage/balance"/> ">Управление балансами</a>
	<form method="POST" action="<c:url value="/adminpage/searchRegistryRecords"/>">
		<table>
			<tr>
				<td>
					Дата с
				</td>
				<td>
					<input type="text" name="fromDate"/>
				</td>
				<td>
					по
				</td>
				<td>
					<input type="text" name="toDate"/>
				</td>				
				<td>
					<input type="submit" value="Поиск"/>
				</td>
			</tr>
		</table>
	</form>
	
	<c:if test="${(!empty fromDate)&&(!empty toDate)}">
		<div>
			Показаны результаты поиска по запросу: 
				<fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy" /> - 
				<fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy" /> <a href= "<c:url value="/adminpage/registryRecords"/> "> Журнал пополнений баланса</a>
		</div> 
	</c:if>
	
	<c:if test="${!empty objectList.pageList}">
		<table class="data">
			<tr>
				<th>Администратор</th>
				<th>Пользователь</th>
				<th>Дата пополнения</th>
				<th>Сумма пополнения</th>
			</tr>
			<c:forEach items="${objectList.pageList}" var="regRecord">
				<tr>

					<td>${regRecord.admin.email}</td>
					<td>${regRecord.user.email}</td>
					<td><fmt:formatDate value="${regRecord.regDate}" pattern="dd/MM/yyyy" /></td>
					<td>${regRecord.amount}</td>

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
</body>
</html>
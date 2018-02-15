<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Producer: ${producer.codeName}</h2>	 <%-- тут в мене відображається ім'я того виробника на яке було нажато на головній сторінці --%>
<c:forEach items="${graphics}" var="graphic">    <%-- відображаються відеокарти цього виробника --%>
	<div>${graphic.name} ${graphic.price}</div>  <%-- з якими полями з бази буде відображатись ці відеокарти --%>
</c:forEach>
<c:if test="${empty graphics}">  		<%--якщо такого виробника немає --%>
	<h3>Producer do not have graphics</h3> <%-- який текст буде при цьому --%>
</c:if>
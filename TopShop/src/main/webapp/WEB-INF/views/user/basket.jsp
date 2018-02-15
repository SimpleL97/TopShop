<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<br>
<br>
<br>
<sec:authorize access="isAuthenticated()">
<div class="container">
	<div class="col-sm-12 text-center">
		<h2>Basket</h2>
	</div>
	<c:if test="${not empty orders}">
	<div class="col-sm-12">
		<br>
	</div>
	<div class="row">
		<div class="col-md-offset-2 col-sm-offset-2 col-md-2 col-sm-2"><h3>Image</h3></div>
		<div class="col-md-2 col-sm-2"><h3>Graphic</h3></div>
		<div class="col-md-2 col-sm-2"><h3>Price</h3></div>
		<div class="col-md-2 col-sm-2"><h3>Amount</h3></div>
		<div class="col-md-1 col-sm-1"><h3>Delete</h3></div>
	</div>
	<div class="col-sm-12">
		<br>
	</div>
	<div class="row">
		<div class="col-sm-12 col-xs-12">
			<c:forEach items="${orders}" var="order">
					<div class="row">
						<div class="col-md-offset-2 col-sm-offset-2 col-md-2 col-sm-2"><img src="/images/graphic/${order.graphic.id}.jpg?version=${order.graphic.version}" width="100%"></div>
						<div class="col-md-2 col-sm-2">${order.graphic.name}</div>
						<div class="col-md-2 col-sm-2">${order.graphic.price}</div>
						
						<div class="col-md-2 col-sm-2">
							<a class="btn btn-primary"href="/basket/minus/${order.id}">-</a> ${order.amount} <a class="btn btn-primary" href="/basket/plus/${order.id}">+</a>
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-1 col-xs-1">
							<a class="btn btn-danger"
								href="/basket/delete/${order.id}">delete</a>
						</div>
					</div>
			</c:forEach>
			<div class="col-md-offset-2 col-sm-offset-2 col-md-10 col-sm-10">
				<h3>Total: ${sum}</h3>
			</div>
		</div>
		<div class="col-md-offset-2 col-sm-offset-2 col-md-1 col-sm-1">
				<a class="btn btn-success" href="/basket/accept">Accept</a>
			</div>
	</div>
	</c:if>
	<c:if test="${empty orders}">
		<div class="col-sm-12 text-center"><h3>No Graphics in Basket</h3></div>
	</c:if>
</div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<div class="container">
	<div class="col-sm-12 text-center">
		<h3>Just Authenticate to add some Graphics to Basket</h3>
	</div>
</div>
</sec:authorize>
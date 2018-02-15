<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<header>
<nav class="navbar navbar-default navbar-fixed-top head">
  <div class="container-fluid">
     <ul class="nav navbar-nav">
     	<li><div><img src="/resources/img/logo.png" alt="" class="logo"></div></li>
     	<li class="name"><h2><span class="proteus">TopShop</span></h2></li>
    </ul>
    <ul class="nav navbar-nav navbar-right buton">
   		<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
    	<li><a class="btn btn-danger" href="/admin/graphic">Admin</a></li>
    	</sec:authorize>
		<li><a class="btn btn-success" href="/basket">Basket</a></li>
		<li><form:form action="/logout" method="POST"><button type="submit" class="btn btn-danger logout">Logout</button></form:form></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		<li><a class="btn btn-success" href="/login">Sign In</a></li>
		<li><a class="btn btn-warning" href="/registration">Register</a></li>
		</sec:authorize>
    </ul>
  </div>
</nav>
</header>
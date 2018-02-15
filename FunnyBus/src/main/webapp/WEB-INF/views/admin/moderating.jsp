<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
			
				<div class="container">
				<div class="col-md-offset-1 col-md-10">
				<ul class="nav navbar-nav">
					<li><a href="/admin/new">Новини</a></li>
					<li><a href="/admin/aid">Допомога біцям АТО</a></li>
					<li><a href="/admin/act">Акції</a></li>
					<li class="active"><a href="/admin/moderating">Відгуки</a></li>
					<li><a href="/admin/bus">Автобусні</a></li>
					<li><a href="/admin/micro">Мікроавтобус</a></li>
				</ul>
				</div>
				</div>
			</div>
		</div>
	</nav>
</div>

<div class="container">
<div class="row">
	<div class="col-md-12 col-xs-12">
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Текст</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Дата</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Користувач</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Прийняти</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Видалити</h3></div>
		</div>
			<c:forEach items="${page.content}" var="review">
				<div class="row">
					<div class="col-md-4 col-xs-4">${review.text}</div>
					<div class="col-md-2 col-xs-2">${review.data}</div>
					<div class="col-md-2 col-xs-2">${review.user.firstName} ${review.user.secondName}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-primary" href="/admin/moderating/accept/${review.id}">прийняти</a></div>
  					<div class="col-md-1 col-xs-1"><a class="btn btn-danger" href="/admin/moderating/delete/${review.id}">видалити</a></div>
				</div>
			</c:forEach>
			
			
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>
</div>
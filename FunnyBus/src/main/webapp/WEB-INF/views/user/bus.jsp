<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="col-md-offset-5 col-xs-offset-5" class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/booking/bus">Автобусні рейси</a></li>
					<li><a href="/booking/micro">Рейси мікроавтосів</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-offset-2 col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-5"></div>
			<div class="col-md-5"><h4>Україна-Польща-Чехія</h4></div>
			<div class="col-md-2"></div>
				<form:form class="form-horizontal" action="/booking/bus" method="POST" modelAttribute="bookingBus">
					<div class="form-group">
						<label for="firstName" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="firstName"/></label>
					</div>
					
  					<div class="form-group">
    					<label for="firstName" class="col-sm-2 control-label">Ім'я</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="firstName" id="firstName"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="secondName" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="secondName"/></label>
					</div>
  					<div class="form-group">
    					<label for="secondName" class="col-sm-2 control-label">Прізвище</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="secondName" id="secondName"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="phone" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="phone"/></label>
					</div>
  					<div class="form-group">
    					<label for="phone" class="col-sm-2 control-label">Телефон</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="phone" id="phone"/>
    					</div>
  					</div>
  					<div class="form-group">
  						<label for="trip" class="col-sm-2 control-label">Поїздка</label>
  						<div class="col-sm-10">
      						<form:select class="form-control" path="trip" id="trip" items="${trips}" itemValue="id" itemLabel="field"/>
    					</div>
    				</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default"  onclick="wow()">Відправити</button>
    					</div>
  					</div>
  					<script>
					        function wow(){
					            alert("Дякуєм за замовлення");
					        }
					    </script>
				</form:form>
			</div>
		
	</div>
</div>
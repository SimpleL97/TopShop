<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<br>
<br>
<style>
	.filter .control-label{
		text-align: left;
	}
</style>

<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal filter" action="/" method="GET" modelAttribute="filter">
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="minPrice" class="form-control" placeholder="Min Price"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxPrice" class="form-control" placeholder="Max Price"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="minFrequency" class="form-control" placeholder="Min Frequency"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxFrequency" class="form-control" placeholder="Max Frequency"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="minData_bus" class="form-control" placeholder="Min Data Bus"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxData_bus" class="form-control" placeholder="Max Data Bus"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="minMemory_value" class="form-control" placeholder="Min Memory Value"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxMemory_value" class="form-control" placeholder="Max Memory Value"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Memories</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="memoryId" items="${memories}" itemValue="id" itemLabel="type"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Producers</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="producerId" items="${producers}" itemValue="id" itemLabel="codeName"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
			<c:forEach items="${page.content}" var="graphic">
				<div class="row">
					<div class="col-md-4 col-xs-4"><img src="/images/graphic/${graphic.id}.jpg?version=${graphic.version}" width="100%"></div>
					<div class="col-md-2 col-xs-2"><h3><a href="/graphic/${graphic.id}">${graphic.name}</a></h3></div>
					<div class="col-md-2 col-xs-2"><h3>${graphic.producer.codeName}</h3></div>
					<div class="col-md-2 col-xs-2"><h3>${graphic.price}</h3></div>
					<sec:authorize access="isAuthenticated()"><div class="col-md-1 col-xs-1"><a class="btn btn-success" href="/add/${graphic.id}"<custom:allParams/>>add</a></div></sec:authorize>
					<sec:authorize access="!isAuthenticated()"><div class="col-md-1 col-xs-1"></div></sec:authorize>
				</div>
				<br>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Amount asc" paramValue="price" />
								<custom:sort innerHtml="Amount desc" paramValue="price,desc" />
								<custom:sort innerHtml="Memory type asc" paramValue="memory.type" />
								<custom:sort innerHtml="Memory type desc" paramValue="memory.type,desc" />
								<custom:sort innerHtml="Producer name asc" paramValue="producer.codeName" />
								<custom:sort innerHtml="Producer name desc" paramValue="producer.codeName,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>
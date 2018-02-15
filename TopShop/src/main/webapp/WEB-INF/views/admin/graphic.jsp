<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

<style>
	.filter .control-label{
		text-align: left;
	}
</style>

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
				<ul class="nav navbar-nav">
					<li><a href="/admin/memory">Memory Type</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li class="active"><a href="/admin/graphic">Graphic</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal filter" action="/admin/graphic" method="GET" modelAttribute="filter">
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
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/graphic" method="POST" modelAttribute="graphic" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="price, frequency, data_bus, memory_value, producer, memory"/>
					<div class="form-group">
						<label for="name" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="name"/></label>
					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Graphic name</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="frequency" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="frequency"/></label>
					</div>
  					<div class="form-group">
    					<label for="frequency" class="col-sm-2 control-label">Frequency</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="frequency" id="frequency"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="memoryValue" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="memory_value"/></label>
					</div>
  					<div class="form-group">
    					<label for="memoryValue" class="col-sm-2 control-label">Memory value</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="memory_value" id="memoryValue"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="dataBus" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="data_bus"/></label>
					</div>
  					<div class="form-group">
    					<label for="dataBus" class="col-sm-2 control-label">Data bus</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="data_bus" id="dataBus"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="price" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
    					<label for="price" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="price" id="price"/>
    					</div>
  					</div>
  					<div class="form-group">
  						<label for="me" class="col-sm-2 control-label">Memory type</label>
  						<div class="col-sm-10">
      						<form:select class="form-control" path="memory" id="me" items="${memories}" itemValue="id" itemLabel="type"/>
    					</div>
    				</div>
    				<div class="form-group">
    					<label for="pr" class="col-sm-2 control-label">Producer</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="producer" id="pr" items="${producers}" itemValue="id" itemLabel="codeName"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input name="file" id="file" type="file">
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-xs-1"><h3>Image</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Name</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Pr.</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Type</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Fr.</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Value</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Data</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Price</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Options</h3></div>
		</div>
			<c:forEach items="${page.content}" var="graphic">
				<div class="row">
					<div class="col-md-1 col-xs-1"><img src="/images/graphic/${graphic.id}.jpg?version=${graphic.version}" width="100%"></div>
					<div class="col-md-2 col-xs-2">${graphic.name}</div>
					<div class="col-md-1 col-xs-1">${graphic.producer.codeName}</div>
					<div class="col-md-1 col-xs-1">${graphic.memory.type}</div>
					<div class="col-md-1 col-xs-1">${graphic.frequency}</div>
					<div class="col-md-1 col-xs-1">${graphic.memory_value}</div>
					<div class="col-md-1 col-xs-1">${graphic.data_bus}</div>
					<div class="col-md-1 col-xs-1">${graphic.price}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/graphic/update/${graphic.id}"<custom:allParams/>>update</a>
					<a class="btn btn-danger" href="/admin/graphic/delete/${graphic.id}"<custom:allParams/>>delete</a></div>
				</div>
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
								<custom:sort innerHtml="Ingredient name asc" paramValue="memory.type" />
								<custom:sort innerHtml="Ingredient name desc" paramValue="memory.type,desc" />
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
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>
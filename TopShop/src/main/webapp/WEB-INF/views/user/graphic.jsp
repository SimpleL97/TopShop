<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<br>
<div class="container">
	<div class="row">
		<div class="col-md-5 col-sm-5">
			<img src="/images/graphic/${graphic.id}.jpg?version=${graphic.version}" width="100%">
		</div>
		<div class="col-md-7 col-sm-7">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="row">
							<div class="col-md-6 col-sm-6"><h3>Graphic: ${graphic.name}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Producer: ${graphic.producer.codeName}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Frequency: ${graphic.frequency}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Memory Value: ${graphic.memory_value}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Data Bus: ${graphic.data_bus}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Memory Type: ${graphic.memory.type}</h3></div>
							<div class="col-md-6 col-sm-6"><h3>Price: ${graphic.price}</h3></div>
							<sec:authorize access="isAuthenticated()"><div class="col-md-6 col-xs-6"><h3><a class="btn btn-success" href="/add/${graphic.id}">ADD</a></h3></div></sec:authorize>
							<sec:authorize access="!isAuthenticated()"><div class="col-md-6 col-xs-6"><h3><span class="buy">Just SighUp To Buy</span></h3></div></sec:authorize>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
<style type="text/css">
.buy{
	color: #F57E7E;
}
</style>
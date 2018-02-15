<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
					  
					
					
  <content>
        <div class="container slideshow">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active"> <img src="/resources/img/slider/slide1.png" alt="...">
                        <div class="carousel-caption"></div>
                    </div>
                    <div class="item"> <img src="/resources/img/slider/slide2.jpg" alt="...">
                        <div class="carousel-caption"></div>
                    </div>
                    <div class="item"> <img src="/resources/img/slider/slide3.jpg" alt="...">
                        <div class="carousel-caption"></div>
                    </div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev"> <span><i class="fa fa-arrow-left"></i></span> </a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next"> <span><i class="fa fa-arrow-right"></i></span> </a>
            </div>
        </div>
        <div class="container">
        <div class="lorem">
            <center><h3>Шановні пасажири! Вітаємо вас на нашому сайті!</h3></center>
            <p>Funny Bus (Веселий Автобус) - це рейсовий автобус, який здійснює пасажирські перевезення за маршрутом: Городенка - Івано-Франківськ - Прага.</p>
            <p>На нашому сайті ви можете забронювати квиток, а оплату здійснювати вже безпосередньо під час поїздки. Для цього вам потрібно забронювати квиток і ми вам зателефонуємо.</p>
            <p>Ми завжди стараємося покращувати сервіс, щоб зробити вашу поїздку максимально комфортною і щоб у вас залишилися тільки приємні та позитивні враження. Саме тому ми створюємо для вас сервіс європейського класу. У наших автобусах під час усієї поїздки вас супроводжуватиме стюардеса, яка у всьому вам допомагатиме. У нас завжди є безкоштовні напої, а також ми часто готуємо для наших пасажирів різноманітні приємні сюрпризи і акції. Детально з усіма зручностями ви можете ознайомитися у розділі міжнародних перевезень.</p>
            <p>Не забувайте залишати нам свої відгуки, ваша думка є надзвичайно важливою для нас! Нам цікаво знати, що саме вам подобається і чого вам, можливо, не вистачає.</p>
            <p><b>Ми вас любимо. Щиро ваш і для вас, колектив Funny Bus!</b></p>
            </div>
        </div>
        </content>
				<sec:authorize access="isAuthenticated()">
					
					  
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="container">
						<div class="row">
						<div class="col-md-6">
						<div class="row">
						<div class="col-md-4">
						<p>Для адміністрування проекту:</p>
						</div>
						<div class="col-md-2">
							<a href="/admin"><button type="submit" class="btn btn-danger">Адмін</button></a>
							</div>
							</div>
							</div>
							<div class="col-md-6">
								<div class="row">
							<div class="col-md-3">
							<form:form action="/logout" method="POST">
							<p>Для виходу з адмінки:</p>
							</div>
							<div class="col-md-3">
							<button type="submit" class="btn btn-danger">Вихід</button>
							</form:form>
							</div>
							</div>
					</div>
					</div>
					</div>
						</sec:authorize>
					
					</sec:authorize>

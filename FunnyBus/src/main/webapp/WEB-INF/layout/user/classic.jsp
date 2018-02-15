<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/resources/img/logo.png">
<script src="/resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<script src="/resources/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="/resources/css/chosen.min.css">
<script src="/resources/js/chosen.jquery.min.js"></script>
 <head>
    <meta charset="UTF-8">
    <title>Funny Bus</title>
    <link href="/resources/styles/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css"> </head>
    <link rel="shortcut icon" href="/resources/css/img/logo.png">

<style type="text/css">
body{
background-color: #e5e5e5; 
}
.navbar{
background-color:   #DCDCDC;
}
.lorem{
	font-size: 17px;
}
.kontakt{
float: left;
}
.topss{
margin-top: 8px;
}
header{
	background-color:#333;
}
footer{
	background-color:#333;
}
.kontakt img{
margin-left:5px;
}
.btnss{
margin-left: 20px; 
}
.adminHeader{
margin-left: 250px;
}
.knopku-bron{
margin-left: 15px;
}
@media(min-width:481px) and (max-width:768px) { 
     .nazva {
          display: block;
        clear: both;
        margin-left: 200px;
        }
    .logo1{
        display: block;
        clear: both;
        margin-left: 200px;
       
    }
    
     .logo2{
        display: block;
        clear: both;
        margin-left: 170px;
    }
    .fotr{
        display: block;
        clear: both;
        margin-top: 50px;
        
    }
}

@media(min-width: 768px) and (max-width: 991px) { 
    .nazva {
          display: block;
        clear: both;
        margin-left: 290px;
        }
    .logo1{
        display: block;
        clear: both;
        margin-left: 260px;
    }

@media (min-width: 1000px) {
    .navbar .navbar-nav {
        display: inline-block;
        float: none;
        vertical-align: top;
    }
    .navbar .navbar-collapse {
        text-align: center;
    }
}
@media(max-width:1000px)  {
	.nav > li{
	 	float: none;
		position: relative;
		display: block;
	}
    .navbar-collapse.collapse {
        display: none !important;
    }
    .navbar-collapse {
        overflow-x: visible !important;
    }
    .navbar-collapse.in {
      overflow-y: auto !important;
    }
    .collapse.in {
      display: block !important;
    }
    .navbar-toggle {
      display: block;
  }
}
</style>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
    <tiles:insertAttribute name="header" />
	<div class="container-fluid">
		<tiles:insertAttribute name="body" />
	</div>
		<tiles:insertAttribute name="footer" />
</body>
</html>
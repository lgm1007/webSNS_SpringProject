<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to SOL</title>
<link href="/sns_pjt/resources/style_main.css" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/slideshow.css" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/slideshow.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js"></script>
    <% 
    	String sid = (String)session.getAttribute("sid");
    	String sname = (String)session.getAttribute("sname");
    %>
    
    <p>Home 페이지 입니다</p>
    <p>아래 세션값</p>
    <p><%= sid %></p>
    <p><%= sname %></p>
    </hr>
    <c:forEach items="${boardList}" var="bDtos">
    	<p>${bDtos.bd_key}</p>
    	<p>${bDtos.mem_id}</p>
    	<p>${bDtos.bd_cont}</p>
    </c:forEach>
    
</body>
</html>
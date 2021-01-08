<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
<%@page import="com.mycomp.sns_pjt.dao.IDao"%>
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
    <a href="post_page" >글작성 페이지로</a>
    </hr>
    <c:forEach items="${boardList}" var="bDtos">
    	<p>${bDtos.mem_id}</p>
    	<p>${bDtos.bd_cont}</p>
    	<%
    		IDao iDao = new IDao();
    		BDto bDto = (BDto)pageContext.findAttribute("bDtos");
    		ArrayList<IDto> iDtos = iDao.iSelect(bDto.getBd_key());
    		for (IDto iDto : iDtos) {
    			pageContext.setAttribute("filename", iDto.getFileName());
    	%>
    	<img src="<spring:url value='/img/${filename}'/>" />
    	<% } %>
    	
    </c:forEach>
    
</body>
</html>
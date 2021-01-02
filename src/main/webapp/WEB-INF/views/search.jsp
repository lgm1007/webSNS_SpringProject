<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search Page</title>
</head>
<body>
	
	<p>search 페이지 입니다.</p>
	<c:forEach items="${memberSearch}" var="mDtos">
		<p>${mDtos.id}</p>
		<p>${mDtos.pw}</p>
		<p>${mDtos.tel1}</p>
		<p>${mDtos.tel2}</p>
		<p>${mDtos.tel3}</p>
	</c:forEach>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DeleteComment</title>
</head>
<body>
	<script type="text/javascript" charset="utf-8">
		var message = '${ok}';
		alert(message);
		// 뒤로가기 후 새로고침
		location.href = document.referrer;
	</script>
</body>
</html>
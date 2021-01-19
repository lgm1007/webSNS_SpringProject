<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostEdit</title>
</head>
<body>
	<script type="text/javascript" charset="utf-8">
		var message = '${ok}';
		var Url = "${url}";
		
		alert(message);
		document.location.href = Url;
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Fail</title>
</head>
<body>
<!-- If Login Fail, Popup Alert & goto Url -->
	<script type="text/javascript" charset="utf-8">
		var warning = '${warn}';
		var Url = '${url}';
		alert(warning);
		document.location.href = Url;
	</script>
</body>
</html>
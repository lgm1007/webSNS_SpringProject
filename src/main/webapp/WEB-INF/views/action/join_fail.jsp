<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Join Fail</title>
</head>
<body>
<!-- If Join Fail, Popup Alert & goto Url -->
	<script type="text/javascript" charset="utf-8">
		var warning = '같은 아이디가 존재합니다';
		var Url = 'join_page.jsp';
		alert(warning);
		document.location.href = Url;
	</script>
</body>
</html>
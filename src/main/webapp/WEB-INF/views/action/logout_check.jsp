<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>

	<script type="text/javascript" charset="UTF-8">
		var warning = '${warn}';
		var urlTrue = '${urlTrue}';
		var urlFalse = '${urlFalse}';
		
		var confirm_result = confirm(warning);
		
		if(confirm_result == true) {
			document.location.href = urlTrue;
		} else {
			document.location.href = urlFalse;
		}
	</script>

</body>
</html>
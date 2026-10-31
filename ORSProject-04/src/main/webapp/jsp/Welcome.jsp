<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome.jsp</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<br>
	<h1 align="Center">
		<font size="10px">Welcome to ORS <%=isLogin ? user.getFirstName(): ""%>
		</font>
	</h1>

	<%@include file="Footer.jsp"%>
</body>
</html>
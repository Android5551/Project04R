<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<h1 align="center">Change Password</h1>
	<h3 style="color: green" align="center"><%=succ%></h3>
	<h3 style="color: red" align="center"><%=err%></h3>

	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
		<div align="center">
			<table>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login" value=""
						placeholder="Enter your email"></td>
				</tr>

				<tr>
					<th>Old Password</th>
					<td><input type="text" name="oldPassword" value=""
						placeholder="Enter your old password"></td>
				</tr>
				<tr>
					<th>New Password</th>
					<td><input type="text" name="newPassword" value=""
						placeholder="Enter your new password"></td>
				</tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=ChangePasswordCtl.OP_SAVE%>"></td>
			</table>
		</div>
	</form>
</body>
</html>
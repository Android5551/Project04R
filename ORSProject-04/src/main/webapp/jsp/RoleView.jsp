
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.RoleCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RoleView.jsp</title>
</head>
<body>

	<%@ include file="Header.jsp"%>
	<jsp:useBean id="bean" 
	class="in.co.rays.proj4.bean.RoleBean"
	scope="request"></jsp:useBean>
	<%
	String err = ServletUtility.getErrorMessage(request);
	String succ = ServletUtility.getSuccessMessage(request);
	
	%>
	<form action="<%=ORSView.ROLE_CTL%>" method="post">

		<div align="center">
			<input type="text" name="id" value="<%=DataUtility.getStringData(bean.getId())%>">
			<h1><%=bean != null && bean.getId() > 0 ? "Update Role":"Add Role"  %></h1>
			<h3 style="color:green"><%=succ %></h3>
			<h3 style="color:red"><%=err %></h3>
			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name"
					 value="<%=DataUtility.getStringData(bean.getName()) %>"
						placeholder="enter your role name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>
				<tr>
					<th>Description<font color="red">*</font></th>
					<td><input type="text" name="description"
					 value="<%=DataUtility.getStringData(bean.getDescription()) %>"
						placeholder="enter your description"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></td>
				</tr>


				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</div>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>
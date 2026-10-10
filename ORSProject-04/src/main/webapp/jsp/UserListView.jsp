<%@page import="in.co.rays.proj4.controller.UserListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
	List<UserBean> l = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);

	int index = (pageNo - 1) * pageSize + 1;
	Iterator<UserBean> it = l.iterator();
	%>
	<div align="center">
		<h1>User List</h1>
		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<table border="1px" width="100%">
				<tr style="background-color:skyblue">
					<th>S.No.</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Login</th>
					<th>DOB</th>
					<th>RoleId</th>
					<th>Gender</th>
				</tr>
				<%
				while (it.hasNext()) {
					UserBean b = it.next();
				%>
				<tr align="center" style="background-color:lightgrey">
					<td><%=index++%></td>
					<td><%=b.getFirstName()%>
					<td><%=b.getLastName()%>
					<td><%=b.getLogin()%>
					<td><%=b.getDob()%>
					<td><%=b.getRoleId()%>
					<td><%=b.getGender()%>
				</tr>
				<%
				}
				%>

			</table>
			<table>
			<input type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>">
			</table>
		</form>
	</div>

	<%@include file="Footer.jsp"%>

</body>
</html>
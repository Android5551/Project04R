<%@page import="in.co.rays.proj4.controller.RoleCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
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
	List<UserBean> l = (List<UserBean>) ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize +  1;
	Iterator<UserBean> it = l.iterator();
	String err = ServletUtility.getErrorMessage(request);
	String succ = ServletUtility.getSuccessMessage(request);
	
	%>
	<div align="center">
		<h1>User List</h1>
		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<h3 style="color:red"><%=err%></h3>
			<h3 style="color:green"><%=succ%></h3>
			<table>
			<tr>
			<th>First Name</th>
			<td><input type="text" name="firstName" value="" placeholder="Search by first name" >
			<input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH%>">
			</td>
			</tr>
			</table>
			<input type="text" name=pageNo value="<%=pageNo%>">
			<table border="1px" width="100%">
				<tr style="background-color: skyblue">
					<th><input type="checkbox" onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select All</th>
					<th>S.No.</th>
					<th>Id</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Login</th>
					<th>DOB</th>
					<th>RoleId</th>
					<th>Gender</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					UserBean b = it.next();
				%>
				<tr align="center" style="background-color: lightgrey">
					<td><input type="checkbox" name="ids" value="<%=b.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=b.getId()%></td>

					<td><%=b.getFirstName()%>
					<td><%=b.getLastName()%>
					<td><%=b.getLogin()%>
					<td><%=b.getDob()%>
					<td><%=b.getRoleId()%>
					<td><%=b.getGender()%>
					<td><a href="<%=ORSView.USER_CTL + "?id=" + b.getId()%>">Edit</a>
				</tr>
				<%
				}
				%>

			</table>
			<table width="100%">
				<tr>

					<td align="left"><input type="submit" 
						name="operation" value="<%=UserListCtl.OP_PREVIOUS%>"
						<%=pageNo == 1 ? "disabled" : ""%>></td>
					<td align="center"><input type="submit"
						name="operation" value="<%=UserListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit"
						name="operation" value="<%=UserListCtl.OP_NEXT%>"
						<%=l.size() < pageSize ? "disabled" : ""%>></td>
				
				</tr>

			</table>
		</form>
	</div>

	<%@include file="Footer.jsp"%>

</body>
</html>
<%@page import="in.co.rays.proj4.controller.RoleListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoleListView.jsp</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
	int nl = (int) request.getAttribute("nl");
	List<RoleBean> l = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<RoleBean> i = l.iterator();
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
		<h1 align="center">Role List</h1>
		<table align="center">
			<tr>
				<td><input type="text" name="name" value=""
					placeholder="Search by name"></td>
				<td><input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>">
			</tr>
		</table>
		<h3 style="color: green" align="center"><%=succ%></h3>
		<h3 style="color: red" align="center"><%=err%></h3>
		<input type="text" name="pageNo" value="<%=pageNo%>">
		<table border="1px" width="100%">
			<tr style="background-color: skyblue">
				<th><input type="checkbox"
					onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
					All</th>
				<th>S.No.</th>
				<th>Name</th>
				<th>Description</th>
				<th>Edit</th>
			</tr>
			<%
			while (i.hasNext()) {
				RoleBean b = i.next();
			%>
			<tr align="center" style="background-color: lightgrey">
				<td><input type="checkbox" name="ids" value="<%=b.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=b.getName()%></td>
				<td><%=b.getDescription()%></td>
				<td><a href="<%=ORSView.ROLE_CTL + "?id=" + b.getId()%>">Edit</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_PREVIOUS%>"
					<%=pageNo == 1 ? "disabled" : ""%>></td>
				<td align="center"><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_DELETE%>"></td>
				<td align="right"><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_NEXT%>"
					<%=nl == 0 ? "disabled" : ""%>></td>
				<!-- l.size() < pageSize -->
			</tr>
		</table>
	</form>
	<%@include file="Footer.jsp"%>
</body>
</html>
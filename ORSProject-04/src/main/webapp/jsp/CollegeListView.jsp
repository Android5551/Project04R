<%@page import="in.co.rays.proj4.controller.CollegeListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CollegeListView.jsp</title>
<%@include file="Header.jsp"%>
</head>
<body>

	<%
	int nl = (int) request.getAttribute("nl");
	List<CollegeBean> l = ServletUtility.getList(request);

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	Iterator<CollegeBean> i = l.iterator();
	int index = (pageNo - 1) * pageSize + 1;
	%>
	<div align="center">
		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">

			<h1>College List</h1>

			<input type="hidden" name="pageNo" value="<%=pageNo%>">
			<table>
				<tr>
					<td><input type="text" name="name" value=""
						placeholder="search by name"></td>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="1px" width="100%">
				<tr style="background-color: skyblue">
				<th>Select All <input type="checkbox"
				onclick="document.querySelectorAll('input[name=ids]').forEach(c => c.checked = this.checked)"></th>
					<th>S.No.</th>
					<th>Name</th>
					<th>PhoneNo</th>
					<th>Address</th>
					<th>State</th>
					<th>City</th>
				</tr>

				<%
				while (i.hasNext()) {
					CollegeBean b = i.next();
					%>
				<tr align="center">
				<td><input type="checkbox" name="ids" value="<%=b.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=b.getName()%></td>
					<td><%=b.getPhoneNo()%></td>
					<td><%=b.getAddress()%></td>
					<td><%=b.getState()%></td>
					<td><%=b.getCity()%></td>
					<%
					}
					%>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="left"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"
						<%=pageNo == 1 ? "disabled" : ""%>></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>" <%=nl == 0 ? "disabled" : ""%>></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<%@include file="Footer.jsp"%>
</html>
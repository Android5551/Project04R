<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>
	<%
	/* error and success message */
	String err = ServletUtility.getErrorMessage(request);
	String succ = ServletUtility.getSuccessMessage(request);

	/* List and NextList */
	List<UserBean> l = ServletUtility.getList(request);
	int nl = (int) request.getAttribute("nl");

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<UserBean> it = l.iterator();

	/* Gender */
	HashMap<String, String> genderMap = new HashMap<String, String>();
	genderMap.put("male", "male");
	genderMap.put("female", "female");
	

	%>
	<div align="center">
		<h1>User List</h1>
		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<h3 style="color: red"><%=err%></h3>
			<h3 style="color: green"><%=succ%></h3>
			<table>
				<!-- search filters -->
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="Search by first name"></td>
					
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="Search by last name"></td>
						
					<th>Gender</th>
					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), genderMap)%>
					
					
					<th>RoleId</th>
					<td><input type="text" name="roleId" value="" placeholder="enter role id"></td>
					
					<th>DOB</th>
					<!-- <td><input type="date" name="dob"></td> -->
					<td><%=HTMLUtility.getList("dob", DataUtility.getStringData(bean.getDob()), l) %>
					
					
						
					<!-- search button -->
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_SEARCH%>"></td>
				</tr>

			</table>
			<!-- checkbox to select all users and delete them. -->
			<input type="hidden" name=pageNo value="<%=pageNo%>">
			<table border="1px" width="100%">
				<tr style="background-color: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S.No.</th>
					<th>Profile</th>
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
					<td><img
						src="<%=ORSView.UPLOAD_PHOTO_CTL + "?id=" + b.getId()%>"
						width="60" height="60"
						style="object-fit: cover; border-radius: 50%; border: 1px solid #ccc;"></td>
					<td><%=b.getFirstName()%></td>
					<td><%=b.getLastName()%></td>
					<td><%=b.getLogin()%></td>
					<td><%=b.getDob()%></td>
					<td><%=b.getRoleId()%></td>
					<td><%=b.getGender()%></td>
					<td><a href="<%=ORSView.USER_CTL + "?id=" + b.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>

			</table>
			<table width="100%">
				<tr>

					<td align="left"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"
						<%=pageNo == 1 ? "disabled" : ""%>></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>" <%=nl == 0 ? "disabled" : ""%>></td>
					<%--  <%=l.size() < pageSize ? "disabled" : ""%>  --%>
				</tr>

			</table>
		</form>
	</div>

	<%@include file="Footer.jsp"%>

</body>
</html>
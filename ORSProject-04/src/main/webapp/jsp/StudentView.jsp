<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentview.jsp</title>
</head>
<body>

	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StudentBean"
		scope="request"></jsp:useBean>
	<%
	List<CollegeBean> l = ServletUtility.getList(request);
	%>
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
		<div align="center">
			<h1>Add Student</h1>
			<table>
				
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="Enter First Name"></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="Enter Last Name"></td>
				</tr>

				<tr>
					<th>Email</th>
					<td><input type="text" name="email" value=""
						placeholder="Enter Email"></td>
				</tr>
				<tr>
					<th>Mobile No</th>
					<td><input type="text" name="mobileNo" value=""
						placeholder="Enter Mobile No"></td>
				</tr>
				<tr>
					<th>Dob</th>
					<td><input type="date" name="dateOfBirth" value=""
						placeholder="Enter date of birth"></td>
				</tr>
				<!-- dynamic preload merging both c.id and c.name 
				 StudentBean's collegeId, CollegeBean's List 
				 student -> college id, collegeName
				college -> id key , name value -->
				<tr>
					<th>College</th>
					<td><%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), l)%></td>
					<td><%=ServletUtility.getErrorMessage("collegeId", request)%></td>
				</tr>
				<!-- <tr>
					<th>College Name</th>
					<td><input type="text" name="collegeName" value=""
						placeholder="Enter College Name"></td>
				</tr> -->
				<tr>
					<th></th>
					<td><input type="submit" name="opertation"
						value="<%=StudentCtl.OP_SAVE%>"></td>
				</tr>

			</table>
	</form>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>
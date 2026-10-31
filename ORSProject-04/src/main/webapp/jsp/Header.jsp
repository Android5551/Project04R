<!DOCTYPE html>
<%@page import="java.util.Locale"%>
<%@page import="in.co.rays.proj4.util.MessageSource"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	MessageSource ms = MessageSource.getInstance();
	UserBean user = (UserBean) session.getAttribute("user"); // bean
	String role = (String) session.getAttribute("role"); // rb.getName()
	boolean isLogin = user != null;
	String welcomeMsg = "Hi, ";
	String locale = ms.getLanguage();
	%>
	<table>
		<tr width="100%">
			<td width="100%">
				<%
				if (isLogin) {
				%>

				<h2><%=welcomeMsg + user.getFirstName() + " (" + role + ")"%></h2> <a
				href="<%=ORSView.USER_CTL%>">Add User</a> | <a
				href="<%=ORSView.USER_LIST_CTL%>">User List</a> | <a
				href="<%=ORSView.ROLE_CTL%>">Add Role</a> | <a
				href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> | <a
				href="<%=ORSView.STUDENT_CTL%>">Add Student</a> | <a
				href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
				href="<%=ORSView.COLLEGE_CTL%>">Add College</a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
				href="<%=ORSView.COURSE_CTL%>">Add Course</a> | <a
				href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> | <a
				href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a> | <a
				href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> | <a
				href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> | <a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> | <a
				href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> | <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> | <a
		
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> | <a
				href="<%=ORSView.LOGIN_CTL + "?operation=logout"%>">Logout</a> | <%
 } else {
 %>
				<h2>Hi, Guest</h2> <a href="<%=ORSView.LOGIN_CTL%>">Login</a> | <a
				href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a> | <%
 }
 %> <a href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
			</td>

			<td width="100%">
				<form>
					<select name="lang" onchange="this.form.submit()">
						<option value="en" <%=("en".equals(locale)) ? "selected" : ""%>>en</option>
						<option value="hi" <%=("hi".equals(locale)) ? "selected" : ""%>>hi</option>
					</select>
				</form>
			</td>
		</tr>
	</table>

	<hr>
</body>
</html>

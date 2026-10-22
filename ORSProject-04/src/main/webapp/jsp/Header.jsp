<%-- 
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
				href="<%=ORSView.ROLE_CTL%>">Add Role</a> | <a
				href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> | <a
				href="<%=ORSView.USER_CTL%>">Add User</a> | <a
				href="<%=ORSView.USER_LIST_CTL%>">User List</a> | <a
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> | <a
				href="<%=ORSView.LOGIN_CTL + "?operation=logout"%>">Logout</a> | <%
 } else {
 %>
				<h2>Hii, Guest</h2> <a href="<%=ORSView.LOGIN_CTL%>">Login</a> | <a
				href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a> | <%
 }
 %>
			
			<a href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
</td>

	<td width="100%">
			<form>
				<select name="lang" onchange="this.form.submit()">
					<option value="en" <%=("en".equals(locale)) ? "selected" :""%>>en</option>
					<option value="hi" <%=("hi".equals(locale)) ? "selected" : ""%>>hi</option>
				</select>
			</form>
			</td>
		</tr>
	</table>

	<hr>
</body>
</html> --%>
<!-- ---------------------------------- -->
<%-- <%@page import="java.util.Locale"%>
<%@page import="in.co.rays.proj4.util.MessageSource"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
MessageSource ms = MessageSource.getInstance();
UserBean user = (UserBean) session.getAttribute("user");
String role = (String) session.getAttribute("role");
boolean isLogin = user != null;
String welcomeMsg = "Hi, ";
String locale = ms.getLanguage();
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
  <div class="container-fluid">

    <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">
      <%=isLogin ? welcomeMsg + user.getFirstName() + " (" + role + ")" : "Hii, Guest"%>
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarNav" aria-controls="navbarNav"
        aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

        <li class="nav-item">
          <a class="nav-link" href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
        </li>

        <% if (isLogin) { %>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.ROLE_CTL%>">Add Role</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_CTL%>">Add User</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_LIST_CTL%>">User List</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL + "?operation=logout"%>">Logout</a></li>
        <% } else { %>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL%>">Login</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a></li>
        <% } %>

      </ul>

      <form class="d-flex" method="get">
        <select name="lang" class="form-select form-select-sm"
            onchange="this.form.submit()">
          <option value="en" <%="en".equals(locale) ? "selected" : ""%>>en</option>
          <option value="hi" <%="hi".equals(locale) ? "selected" : ""%>>hi</option>
        </select>
      </form>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
 --%>
<!-- light navbar -->
<%@page import="java.util.Locale"%>
<%@page import="in.co.rays.proj4.util.MessageSource"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
MessageSource ms = MessageSource.getInstance();
UserBean user = (UserBean) session.getAttribute("user");
String role = (String) session.getAttribute("role");
boolean isLogin = user != null;
String welcomeMsg = "Hi, ";
String locale = ms.getLanguage();
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.navbar-soft {
	background: linear-gradient(90deg, #ffffff 0%, #eaf6fb 50%, #d4ecf7 100%);
	backdrop-filter: blur(8px);
	border-bottom: 1px solid #cfe6f2;
	box-shadow: 0 2px 8px rgba(120, 170, 200, 0.15);
}

.navbar-soft .navbar-brand,
.navbar-soft .nav-link {
	color: #2c5d82;
	font-weight: 500;
}

.navbar-soft .nav-link:hover,
.navbar-soft .nav-link:focus {
	color: #0d6efd;
	background-color: rgba(255, 255, 255, 0.85);
	border-radius: 6px;
}

.navbar-soft .form-select {
	border-color: #cfe6f2;
	color: #2c5d82;
	background-color: #ffffff;
}
</style>

<nav class="navbar navbar-expand-lg navbar-soft" data-bs-theme="light">
  <div class="container-fluid">

    <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">
      <%=isLogin ? welcomeMsg + user.getFirstName() + " (" + role + ")" : "Hii, Guest"%>
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarNav" aria-controls="navbarNav"
        aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

        <li class="nav-item">
          <a class="nav-link" href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
        </li>

        <% if (isLogin) { %>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.ROLE_CTL%>">Add Role</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_CTL%>">Add User</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_LIST_CTL%>">User List</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL + "?operation=logout"%>">Logout</a></li>
        <% } else { %>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL%>">Login</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a></li>
        <% } %>

      </ul>

      <form class="d-flex" method="get">
        <select name="lang" class="form-select form-select-sm"
            onchange="this.form.submit()">
          <option value="en" <%="en".equals(locale) ? "selected" : ""%>>en</option>
          <option value="hi" <%="hi".equals(locale) ? "selected" : ""%>>hi</option>
        </select>
      </form>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
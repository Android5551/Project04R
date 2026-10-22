<%-- 
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<meta charset="UTF-8">
<title>LoginView.jsp</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>
	<div align="center">
		<h1><%=ms.get("login.title")%></h1>
		<h3 style="color:green"><%=succ %></h3>
		<h3 style="color:red"><%=err %></h3>
		

		<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		
			<table>

				<tr>
					<th><%=ms.get("login.email")%></th>
					<td><input type="text" name="login"
						placeholder="enter you login" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("login.password")%></th>
					<td><input type="password" name="password"
						placeholder="enter you password" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGNIN%>"></td>
				</tr>
				
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>">forgot your
							password ?</a></td>
				</tr>
			</table>

		</form>

	</div>

	<%@include file="Footer.jsp"%>
</body>
</html> --%>

<!-- ----------adding card------------- -->
<%-- <%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LoginView.jsp</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<div class="container d-flex justify-content-center mt-5">
		<div class="card shadow-sm" style="max-width: 400px; width: 100%">

			Optional image: put a file in your project and update the path
			<img src="<%=request.getContextPath()%>/img/login.jpg"
				class="card-img-top" alt="Login">

			<div class="card-body">
				<h4 class="card-title text-center mb-3"><%=ms.get("login.title")%></h4>

				<% if (succ != null && !succ.trim().isEmpty()) { %>
					<div class="alert alert-success py-2"><%=succ%></div>
				<% } %>
				<% if (err != null && !err.trim().isEmpty()) { %>
					<div class="alert alert-danger py-2"><%=err%></div>
				<% } %>

				<form action="<%=ORSView.LOGIN_CTL%>" method="post">

					<div class="mb-3">
						<label for="login" class="form-label"><%=ms.get("login.email")%></label>
						<input type="text" id="login" name="login" class="form-control"
							placeholder="enter your login" value="">
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label"><%=ms.get("login.password")%></label>
						<input type="password" id="password" name="password"
							class="form-control" placeholder="enter your password" value="">
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					<div class="d-grid mb-3">
						<input type="submit" name="operation" class="btn btn-primary"
							value="<%=LoginCtl.OP_SIGNIN%>">
					</div>

					<div class="text-center">
						<a href="<%=ORSView.FORGET_PASSWORD_CTL%>">forgot your password ?</a>
					</div>

				</form>
			</div>
		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html> --%>

<!-- ---------adding google icons font awesome--------- -->
<%-- <%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LoginView.jsp</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>
.input-icon {
	position: relative;
}

.input-icon .icon-left {
	position: absolute;
	left: 14px;
	top: 50%;
	transform: translateY(-50%);
	color: #6c757d;
	pointer-events: none;
}

.input-icon .icon-right {
	position: absolute;
	right: 14px;
	top: 50%;
	transform: translateY(-50%);
	color: #6c757d;
	cursor: pointer;
}

.input-icon input {
	padding-left: 40px;
	padding-right: 40px;
}
</style>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<div class="container d-flex justify-content-center mt-5">
		<div class="card shadow-sm" style="max-width: 400px; width: 100%">

			<div class="card-body">

				<div class="text-center mb-3">
					<i class="fa-solid fa-circle-user fa-4x text-primary"></i>
					<h4 class="card-title mt-2"><%=ms.get("login.title")%></h4>
				</div>

				<%
				if (succ != null && !succ.trim().isEmpty()) {
				%>
				<div class="alert alert-success py-2">
					<i class="fa-solid fa-circle-check me-1"></i><%=succ%>
				</div>
				<%
				}
				%>
				<%
				if (err != null && !err.trim().isEmpty()) {
				%>
				<div class="alert alert-danger py-2">
					<i class="fa-solid fa-triangle-exclamation me-1"></i><%=err%>
				</div>
				<%
				}
				%>

				<form action="<%=ORSView.LOGIN_CTL%>" method="post">

					<div class="mb-3">
						<label for="login" class="form-label"> <i
							class="fa-solid fa-envelope me-1"></i><%=ms.get("login.email")%>
						</label>
						<div class="input-icon">
							<i class="fa-solid fa-user icon-left"></i> <input type="text"
								id="login" name="login" class="form-control"
								placeholder="enter your login" value="">
						</div>
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label"> <i
							class="fa-solid fa-key me-1"></i><%=ms.get("login.password")%>
						</label>
						<div class="input-icon">
							<i class="fa-solid fa-lock icon-left"></i> <input type="password"
								id="password" name="password" class="form-control"
								placeholder="enter your password" value=""> <i
								class="fa-solid fa-eye icon-right" id="eyeIcon"
								onclick="togglePassword()"></i>
						</div>
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					<div class="d-grid mb-3">
						<button type="submit" name="operation" class="btn btn-primary"
							value="<%=LoginCtl.OP_SIGNIN%>">
							<i class="fa-solid fa-right-to-bracket me-1"></i><%=LoginCtl.OP_SIGNIN%>
						</button>
					</div>

					<div class="text-center">
						<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"> <i
							class="fa-solid fa-circle-question me-1"></i>forgot your password
							?
						</a>
					</div>

				</form>
			</div>
		</div>
	</div>

	<script>
		function togglePassword() {
			var pwd = document.getElementById("password");
			var icon = document.getElementById("eyeIcon");
			if (pwd.type === "password") {
				pwd.type = "text";
				icon.classList.replace("fa-eye", "fa-eye-slash");
			} else {
				pwd.type = "password";
				icon.classList.replace("fa-eye-slash", "fa-eye");
			}
		}
	</script>

	<%@include file="Footer.jsp"%>
</body>
</html>  --%>

<!-- --------using only jsp and bootstrap--- -->
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LoginView.jsp</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<div class="container d-flex justify-content-center mt-5">
		<div class="card shadow-sm" style="max-width: 400px; width: 100%">
			<div class="card-body">

				<div class="text-center mb-3">
					<i class="bi bi-person-circle text-primary" style="font-size: 4rem;"></i>
					<h4 class="card-title mt-2"><%=ms.get("login.title")%></h4>
				</div>

				<%
				if (succ != null && !succ.trim().isEmpty()) {
				%>
				<div class="alert alert-success py-2">
					<i class="bi bi-check-circle-fill me-1"></i><%=succ%>
				</div>
				<%
				}
				%>
				<%
				if (err != null && !err.trim().isEmpty()) {
				%>
				<div class="alert alert-danger py-2">
					<i class="bi bi-exclamation-triangle-fill me-1"></i><%=err%>
				</div>
				<%
				}
				%>

				<form action="<%=ORSView.LOGIN_CTL%>" method="post">

					<div class="mb-3">
						<label for="login" class="form-label"><%=ms.get("login.email")%></label>
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-person"></i></span>
							<input type="text" id="login" name="login" class="form-control"
								placeholder="enter your login" value="">
						</div>
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label"><%=ms.get("login.password")%></label>
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-lock"></i></span>
							<input type="password" id="password" name="password"
								class="form-control" placeholder="enter your password" value="">
						</div>
						<div class="text-danger small"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					<div class="d-grid mb-3">
						<button type="submit" name="operation" class="btn btn-primary"
							value="<%=LoginCtl.OP_SIGNIN%>">
							<i class="bi bi-box-arrow-in-right me-1"></i><%=LoginCtl.OP_SIGNIN%>
						</button>
					</div>

					<div class="text-center">
						<a href="<%=ORSView.FORGET_PASSWORD_CTL%>">
							<i class="bi bi-question-circle me-1"></i>forgot your password ?
						</a>
					</div>

				</form>
			</div>
		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>
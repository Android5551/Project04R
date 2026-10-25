<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CollegeListView.jsp</title>
</head>
<body>
<%
List<UserBean> nl = (List<UserBean>) request.getAttribute("nl");
List<UserBean> list = ServletUtility.getList(request);

int pageNo = ServletUtility.getPageNo(request);
int pageSize =  ServletUtility.getPageSize(request);
%>
</body>
<form action="ORSView.COLLEGE_LIST_CTL" method="POST">

</form>
</html>
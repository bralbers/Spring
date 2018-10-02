<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogOut</title>
</head>
<body>
	<h3>Are you sure you want to logout?</h3>
	<sf:form action="logoutUser" method="POST">
		<input type="submit" formmethod="post" />
	</sf:form>
	<p>
		Click <a href="http://localhost:8088/SpringLoginRegisterExercise">here</a>
		to go back
	</p>
</body>
</html>
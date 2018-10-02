<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In</title>
</head>
<body>
	<sf:form action="loginUser" method="GET" modelAttribute="newUser">
		<sf:input path="username" placeholders="Username" type="text"></sf:input>
		<sf:input path="password" placeholders="Password" type="text"></sf:input>
		<input type="submit" formmethod="post"/>
	</sf:form>
	<p>
		Click <a href="register">here</a> to register or <a
			href="http://localhost:8088/SpringLoginRegisterExercise">here</a> to go
		back
	</p>
</body>
</html>
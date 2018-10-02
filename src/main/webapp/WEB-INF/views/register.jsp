<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<sf:form action="send" method="GET" modelAttribute="newUser">
		<h4>        Username</h4><sf:input path="username" placeholder="Username" type="text"></sf:input><br>
		<h4>        Password</h4><sf:input path="password" placeholder="Password" type="password"></sf:input><br>
		<h4>Confirm Password</h4><input name="confirmPassword" placeholder="Confirm Password" type="password"></input><br>
		<h4>      First Name</h4><sf:input path="firstName" placeholder="First Name" type="text"></sf:input><br>
		<h4>       Last Name</h4><sf:input path="lastName" placeholder="Last Name" type="text"></sf:input><br>
		<h4>           Email</h4><sf:input path="email" placeholder="example@domain.com" type="email"></sf:input><br><br>
		<input type="submit" formmethod="post" />
	</sf:form>
</body>
</html>
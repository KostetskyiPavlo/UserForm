<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error {
		color: red;
	}
</style>
</head>
<body>
	
	<h1> User FORM</h1>
	
	<form:form action="/add/user" method="post" modelAttribute="userModel">
		
		<form:errors path="*" cssClass="error"/>
		<br>
		<label>First n</label>
		<form:input path="firstName"/> <br>
		<label>Last na</label>
		<form:input path="lastName"/> <br>
		<label>Email</label>
		<form:input path="email"/> <br>
		<label>Login</label>
		<form:input path="login"/> <br>
		<label>Password</label>
		<form:input path="password" type="password"/> <br>
		<label>Password confirm</label>
		<form:input path="passwordConfirm" type="password"/>  <br>

		<button type="submit">add user</button>
	
	</form:form>
	
</body>
</html>
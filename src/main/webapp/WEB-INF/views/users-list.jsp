<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="/users/search" method="get"
		modelAttribute="simpleFilterModel">
		<form:input path="search" />
		<button type="submit">Search</button>

	</form:form>


	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Login</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ usersList }" var="user">
				<tr>
					<td>${ user.id }</td>
					<td>${ user.firstName }</td>
					<td>${ user.lastName }</td>
					<td>${ user.login }</td>
					<td>${ user.email }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
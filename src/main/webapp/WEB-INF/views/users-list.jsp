<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>

</head>
<body>

    <c:url var="firstUrl"
           value="/users/pages/search?page=0&search=${searchText.search}&minSalary=${searchText.minSalary}&maxSalary=${searchText.maxSalary}&pageSize=${searchText.pageSize}"/>
    <c:url var="lastUrl"
           value="/users/pages/search?page=${usersList.totalPages - 1}&search=${searchText.search}&minSalary=${searchText.minSalary}&maxSalary=${searchText.maxSalary}&pageSize=${searchText.pageSize}"/>
    <c:url var="nextUrl"
           value="/users/pages/search?page=${currentIndex + 1}&search=${searchText.search}&minSalary=${searchText.minSalary}&maxSalary=${searchText.maxSalary}&pageSize=${searchText.pageSize}"/>
    <c:url var="prevUrl"
           value="/users/pages/search?page=${currentIndex - 1}&search=${searchText.search}&minSalary=${searchText.minSalary}&maxSalary=${searchText.maxSalary}&pageSize=${searchText.pageSize}"/>


<div class="container">

    <div class="col-md-2">
        <div class="row">&nbsp;</div>
        <div class="row">
            <form:form action="/users/pages/search" method="get" modelAttribute="searchText">
                Search text
                <form:input path="search" cssClass="form-control"/><br>
                Min salary <form:input path="minSalary" type="number" label="" class="form-control"/> <br>
                Max salary <form:input path="maxSalary" type="number" class="form-control"/> <br>
                Users on page<br>
                <form:radiobutton path="pageSize" value="5" label="5"/>&nbsp;&nbsp;
                <form:radiobutton path="pageSize" value="10" label="10"/>&nbsp;&nbsp;
                <form:radiobutton path="pageSize" value="15" label="15"/>&nbsp;&nbsp;
                <form:radiobutton path="pageSize" value="20" label="20"/>&nbsp;&nbsp;
                <form:radiobutton path="pageSize" value="50" label="50"/> <br>
                
                <button type="submit">Search</button>
            </form:form>
        </div>
    </div>
    
    <div class="col-xs-1"></div>
    <div class="col-md-9">
        <div class="row">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${ currentIndex == 0 }">
                        <li class="disabled"><a href="#">&lt;&lt;</a></li>
                        <li class="disabled"><a href="#">&lt;</a></li>
                        <li class="active"><a href="${firstUrl}">1</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${firstUrl}">&lt;&lt;</a></li>
                        <li><a href="${prevUrl}">&lt;</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${beginIndex}" end="${ endIndex }">
                    <c:url var="pageUrl"
                           value="/users/pages/search?page=${i}&search=${searchText.search}&minSalary=${searchText.minSalary}&maxSalary=${searchText.maxSalary}&pageSize=${searchText.pageSize}"/>
                    <c:choose>
                        <c:when test="${i == currentIndex }">
                            <li class="active"><a href="#">${i + 1}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageUrl}">${i + 1}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${ currentIndex + 1 == usersList.totalPages}">
                        <li class="disabled"><a href="#">&gt;</a></li>
                        <li class="disabled"><a href="#">&gt;&gt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${nextUrl}">&gt;</a></li>
                        <li><a href="${lastUrl}">&gt;&gt;</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        
        <div class="row">
            <table class="table table-bordered">
                <thead>
                	<tr>
                		<th>ID</th>
                		<th>Full name</th>
						<th>Login</th>
						<th>Email</th>
                		<th>Salary</th>
                	</tr>
                </thead>
                <tbody>
                <c:forEach items="${usersPageListByPageSize}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName} ${user.lastName}</td>
						<td>${user.login}</td>
						<td>${user.email}</td>
                        <td>${user.salary}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>

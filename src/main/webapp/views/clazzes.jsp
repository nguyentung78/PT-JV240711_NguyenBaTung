<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/12/2024
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of Classes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Class List</h1>

    <!-- Display error message if any -->
    <c:if test="${param.error != null}">
        <div class="alert alert-danger">
                ${param.error}
        </div>
    </c:if>

    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/clazzController/initCreate" class="btn btn-success">Add New Class</a>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>No</th>
            <th>Class ID</th>
            <th>Class Name</th>
            <th>Majors</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listClasses}" var="clazz" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${clazz.classId}</td>
                <td>${clazz.className}</td>
                <td>${clazz.majors}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/clazzController/initUpdate?classId=${clazz.classId}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/clazzController/delete?classId=${clazz.classId}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this class?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

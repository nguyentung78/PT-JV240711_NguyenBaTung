<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of Students</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">List of Students</h2>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/studentsController/initCreate" class="btn btn-success">Add New Student</a>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Address</th>
            <th>Gender</th>
            <th>Class</th>
            <th>Status</th>
            <th>Avatar</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listStudents}" var="student">
            <tr>
                <td>${student.studentId}</td>
                <td>${student.studentName}</td>
                <td>${student.phoneNumber}</td>
                <td>${student.email}</td>
                <td>${student.address}</td>
                <td>
                    <c:choose>
                        <c:when test="${student.sex}">Male</c:when>
                        <c:otherwise>Female</c:otherwise>
                    </c:choose>
                </td>
                <td>${student.clazz.className}</td>
                <td>
                    <c:choose>
                        <c:when test="${student.status == 1}">Active</c:when>
                        <c:otherwise>Inactive</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${not empty student.imageUrl}">
                        <img src="${student.imageUrl}" alt="Avatar" class="img-thumbnail" style="width: 50px; height: 50px;">
                    </c:if>
                    <c:if test="${empty student.imageUrl}">
                        <span>No Image</span>
                    </c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/studentsController/initUpdate?id=${student.studentId}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/studentsController/delete?id=${student.studentId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

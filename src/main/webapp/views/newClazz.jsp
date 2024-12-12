<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/12/2024
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Class</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Create New Class</h2>
    <form:form method="post" action="${pageContext.request.contextPath}/clazzController/create" modelAttribute="clazz">
        <div class="mb-3">
            <label for="className" class="form-label">Class Name</label>
            <form:input path="className" id="className" class="form-control" placeholder="Enter Class Name" />
            <form:errors path="className" cssClass="text-danger" />
        </div>
        <div class="mb-3">
            <label for="majors" class="form-label">Majors</label>
            <form:input path="majors" id="majors" class="form-control" placeholder="Enter Majors" />
            <form:errors path="majors" cssClass="text-danger" />
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Create</button>
            <a href="${pageContext.request.contextPath}/clazzController/findAll" class="btn btn-secondary">Cancel</a>
        </div>
    </form:form>
</div>
</body>
</html>


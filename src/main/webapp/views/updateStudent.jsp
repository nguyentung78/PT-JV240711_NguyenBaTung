<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/12/2024
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update Student</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">Update Student</h2>
  <form:form method="post" action="${pageContext.request.contextPath}/studentController/update" modelAttribute="student" enctype="multipart/form-data">
    <div class="mb-3">
      <form:label path="studentName" class="form-label">Name</form:label>
      <form:input path="studentName" class="form-control" />
      <form:errors path="studentName" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <form:label path="phoneNumber" class="form-label">Phone</form:label>
      <form:input path="phoneNumber" class="form-control" />
      <form:errors path="phoneNumber" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <form:label path="email" class="form-label">Email</form:label>
      <form:input path="email" class="form-control" />
      <form:errors path="email" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <form:label path="address" class="form-label">Address</form:label>
      <form:input path="address" class="form-control" />
      <form:errors path="address" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <form:label path="sex" class="form-label">Gender</form:label>
      <form:select path="sex" class="form-control">
        <option value="true" <c:if test="${student.sex}">selected</c:if>>Male</option>
        <option value="false" <c:if test="${!student.sex}">selected</c:if>>Female</option>
      </form:select>
    </div>
    <div class="mb-3">
      <form:label path="classId" class="form-label">Class</form:label>
      <form:select path="classId" class="form-control">
        <c:forEach items="${classes}" var="clazz">
          <option value="${clazz.classId}" <c:if test="${student.classId == clazz.classId}">selected</c:if>>${clazz.className}</option>
        </c:forEach>
      </form:select>
      <form:errors path="classId" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <form:label path="image" class="form-label">Image</form:label>
      <form:input path="image" type="file" class="form-control" />
    </div>
    <div class="mb-3">
      <form:label path="status" class="form-label">Status</form:label>
      <form:select path="status" class="form-control">
        <option value="1" <c:if test="${student.status == 1}">selected</c:if>>Active</option>
        <option value="0" <c:if test="${student.status == 0}">selected</c:if>>Inactive</option>
      </form:select>
    </div>
    <div class="text-center">
      <button type="submit" class="btn btn-primary">Update</button>
      <a href="${pageContext.request.contextPath}/studentController/findAll" class="btn btn-secondary">Cancel</a>
    </div>
  </form:form>
</div>
</body>
</html>


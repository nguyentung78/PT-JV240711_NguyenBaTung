<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <form:form method="post" action="${pageContext.request.contextPath}/studentsController/update" modelAttribute="student" enctype="multipart/form-data">
    <div class="mb-3">
      <form:hidden path="studentId"/>
    </div>
    <div class="mb-3">
      <form:label path="studentName" class="form-label">Name</form:label>
      <form:input path="studentName" type="text" class="form-control"/>
      <form:errors path="studentName" cssClass="text-danger"/>
    </div>
    <div class="mb-3">
      <form:label path="phoneNumber" class="form-label">Phone Number</form:label>
      <form:input path="phoneNumber" type="text" class="form-control"/>
      <form:errors path="phoneNumber" cssClass="text-danger"/>
    </div>
    <div class="mb-3">
      <form:label path="email" class="form-label">Email</form:label>
      <form:input path="email" type="email" class="form-control"/>
      <form:errors path="email" cssClass="text-danger"/>
    </div>
    <div class="mb-3">
      <form:label path="address" class="form-label">Address</form:label>
      <form:input path="address" type="text" class="form-control"/>
      <form:errors path="address" cssClass="text-danger"/>
    </div>
    <div class="mb-3">
      <form:label path="sex" class="form-label">Gender</form:label>
      <form:select path="sex" class="form-control">
        <option value="true" ${student.sex ? 'selected' : ''}>Male</option>
        <option value="false" ${!student.sex ? 'selected' : ''}>Female</option>
      </form:select>
      <form:errors path="sex" cssClass="text-danger"/>
    </div>
    <div class="mb-3">
      <form:select path="clazz.classId" class="form-control">
        <option value="" disabled>Select Class</option>
        <c:forEach items="${listClasses}" var="clazz">
          <option value="${clazz.classId}" ${student.clazz != null && student.clazz.classId == clazz.classId ? 'selected' : ''}>
              ${clazz.className}
          </option>
        </c:forEach>
      </form:select>
    </div>
    <div class="mb-3">
      <label for="avatarFile" class="form-label">Avatar</label>
      <input type="file" id="avatarFile" name="avatarFile" class="form-control"/>
    </div>
    <div class="mb-3">
      <form:label path="status" class="form-label">Status</form:label>
      <form:select path="status" class="form-control">
        <option value="1" ${student.status == 1 ? 'selected' : ''}>Active</option>
        <option value="0" ${student.status == 0 ? 'selected' : ''}>Inactive</option>
      </form:select>
      <form:errors path="status" cssClass="text-danger"/>
    </div>
    <div class="text-center">
      <button type="submit" class="btn btn-primary">Update</button>
      <a href="${pageContext.request.contextPath}/studentsController/findAll" class="btn btn-secondary">Cancel</a>
    </div>
  </form:form>
</div>
</body>
</html>

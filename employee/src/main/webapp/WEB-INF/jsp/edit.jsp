<%@ include file="common/headboard.jsp"%>
<%@ include file="common/navBar.jsp"%>

<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>ADD Employee</h3>
    </div>
    <div>
        <form action="${urlEdit}" modelAttribute="employeeForm" method="post">
        <input type="hidden" name="idEmployee" value="${employee.idEmployee}"/>
            <div class="mb-3">
                <label for="nameEmployee" class="form-label">Name</label>
                <input type="text" class="form-control" id="nameEmployee"
                name="nameEmployee" required="true" value="${employee.nameEmployee}">
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department"
                value="${employee.department}">
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="number" step="any" class="form-control" id="salary" name="salary"
                value="${employee.salary}">
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-sm me-3">ADD</button>
                <a href="${urlInit}" class="btn btn-danger btn-sm">BACK</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="common/footer.jsp"%>
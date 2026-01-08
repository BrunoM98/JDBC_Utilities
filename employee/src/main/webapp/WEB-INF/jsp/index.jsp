<%@ include file="common/headboard.jsp"%>
<%@ include file="common/navBar.jsp"%>
<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Employee System</h3>
        <div class="container">
            <table class="table table-striped table-hover table-bordered align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Department</th>
                    <th scope="col">Salary</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <th scope="row">${employee.idEmployee}</th>
                        <td>${employee.nameEmployee}</td>
                        <td>${employee.department}</td>
                        <td>
                            <fmt:setLocale value="en_US"/>
                            <fmt:formatNumber type="currency"
                                               value="${employee.salary}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/footer.jsp"%>
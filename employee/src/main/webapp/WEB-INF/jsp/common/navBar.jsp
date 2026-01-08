<!-- url de la aplicacion--!>

<c:set var="urlInit">
<c:url value="${application.contextPath}/"/>
</c:set>
<c:set var="urlADD">
<c:url value="${application.contextPath}/ADD"/>
</c:set>




<div class="container">
    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${urlInit}">Employee System</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${urlInit}">Init</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${urlADD}">ADD Employee</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
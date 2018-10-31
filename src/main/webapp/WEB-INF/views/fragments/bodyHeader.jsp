<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <nav class="col-3 navbar navbar-expand-lg navbar navbar-dark bg-dark">
        <div class="navbar-brand"><span class="oi oi-bug" title="Pet space" aria-hidden="true"></span>&nbsp;Bulls and Cows</div>
    </nav>
    <nav class="col-9 navbar navbar-expand-lg navbar-dark bg-primary">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/user"/>">
                        <span class="oi oi-home" title="home" aria-hidden="true"></span>&nbsp;
                        Home</a>
                </li>
            </ul>
        </div>
        <a class="navbar-brand float-right" href="<c:url value="/logout"/>">
            <span class="oi oi-account-logout" title="logout" aria-hidden="true"></span>&nbsp;Exit</a>
    </nav>
</div>
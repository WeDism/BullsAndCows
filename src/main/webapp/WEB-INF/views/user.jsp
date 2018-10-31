<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
</head>
<body>
<div class="container" data-context-path="${pageContext.request.contextPath}/user">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row">
        <div class="offset-4 col-4 card-body">
        </div>
        <div class="offset-4 col-4 card-body">
            <a class="btn btn-lg ${requestScope.classForATag} btn-block" href="<c:url value="/game/resume"/>" ${requestScope.style}>Resume game</a>
        </div>
        <div class="offset-4 col-4 card-body">
            <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/game/new"/>">Create new game</a>
        </div>
        <div class="offset-4 col-4 card-body">
            <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/user/history"/>">View history games</a>
        </div>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
</head>
<body>
<c:set var="user" value="${requestScope.user}"/>
<c:set var="notCompletedGame" value="${requestScope.notCompletedGame}"/>
<div class="container">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col-4">
            <div class="card text-white bg-info">
                <img src="<c:url value="/web_resources/images/user.png"/>" class="card-img-top" height="320" width="320">
                <h4 class="card-text">About of <c:out value="${user.nickname}"/></h4>
            </div>
        </div>
        <div class="col-8">
            <div class="card-header row text-center">
                <div class="col-6">Rating :</div>
                <div class="col-6"><c:out value="${user.rating}"/></div>
            </div>
            <div class="card-header row text-center">
                <div class="col-6">I have resume Game :</div>
                <div class="col-6">
                    <c:choose>
                        <c:when test="${optional.present}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="card-header row">
                <div class="col text-center">Games :</div>
            </div>
            <c:forEach items="${requestScope.gameList}" var="game">
                <div class="card-body row">
                    <div class="col-5">Start game: <c:out value="${game.gameStartTime}"/></div>
                    <div class="col-5">End game: <c:out value="${game.gameEndTime}"/></div>
                    <div class="col-2">Riddle: <c:out value="${game.riddle}"/></div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>

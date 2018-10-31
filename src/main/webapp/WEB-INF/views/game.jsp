<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <c:import url="fragments/htmlHeadTags.jsp"/>
    <script src='<c:url value="/web_resources/js/custom/game.js"/>'></script>
</head>
<body>
<c:set var="gameSteps" value="${gameSteps}"/>
<div class="container" data-context-path="${pageContext.request.contextPath}">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col">
            <div class="card-deck col-12">
                <div class="col-8 offset-2">
                    <div class="card" data-path-for-message="<c:out value="step/new"/>">
                        <div class="card-header">Game</div>
                        <div class="card-body" style="height:25rem;  overflow-y: scroll">
                            <ul class="list-unstyled">
                                <c:forEach var="step" items="${gameSteps}">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-6 offset-6">
                                                <li class="media">
                                                    <div class="media-body">
                                                        <div class="text-muted float-right">
                                                            <small>
                                                                <fmt:parseDate value="${step.left.dateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime"/>
                                                                <fmt:formatDate pattern="HH:mm:ss" var="formatTime" value="${parsedDateTime}"/>
                                                                <div class="float-right" title="${formatTime}">&nbsp;${fn:substring(step.left.dateTime,0,10)}</div>
                                                            </small>
                                                        </div>
                                                        <strong class="text-success">Your answer</strong>
                                                        <p><c:out value="${step.left.answer}"/></p>
                                                    </div>
                                                </li>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6">
                                                <li class="media">
                                                    <div class="media-body">
                                                        <strong class="text-info">Computer response</strong>
                                                        <p>${step.right}</p>
                                                    </div>
                                                </li>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </ul>
                        </div>
                        <br/>
                        <div id="controlPanel" class="card-body row">
                            <div class="col-3">
                                <label for="inputGroupSelect00">First</label><select class="custom-select" id="inputGroupSelect00">
                                <option selected disabled>X</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                            </select>
                            </div>
                            <div class="col-3">
                                <label for="inputGroupSelect01">Second</label><select class="custom-select" id="inputGroupSelect01">
                                <option selected disabled>X</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                            </select>
                            </div>
                            <div class="col-3">
                                <label for="inputGroupSelect02">Third</label><select class="custom-select" id="inputGroupSelect02">
                                <option selected disabled>X</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                            </select>
                            </div>
                            <div class="col-3">
                                <label for="inputGroupSelect03">Fourth</label><select class="custom-select" id="inputGroupSelect03">
                                <option selected disabled>X</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                            </select>
                            </div>
                        </div>
                        <div class="card-body row">
                            <div class="col">
                                <button id="sendAttempt" type="button" class="btn float-right">
                                    <span class="oi oi-envelope-open"></span>&nbsp;Send attempt
                                </button>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

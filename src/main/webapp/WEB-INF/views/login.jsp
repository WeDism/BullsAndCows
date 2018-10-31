<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <link rel="stylesheet" href="<c:url value="/web_resources/css/custom/login.css"/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <h2 class="form-sign-in-heading">
                <a href="<c:url value="/login"/>"><span class="oi oi-bug" title="Bulls and Cows" aria-hidden="true"></span>&nbsp;Bulls and Cows</a>
            </h2>
            <div class="row">
                <div class="col-4 left-col">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="v-pills-sign-in-tab" data-toggle="pill" href="#v-pills-sign-in" role="tab"
                           aria-controls="v-pills-sign-in" aria-selected="true"><span class="oi oi-account-login" title="sign-in" aria-hidden="true"></span>&nbsp;
                            Sign in</a>
                        <a class="nav-link" id="v-pills-sign-up-tab" data-toggle="pill" href="#v-pills-sign-up" role="tab"
                           aria-controls="v-pills-sign-up" aria-selected="false"><span class="oi oi-pencil" title="sign-up" aria-hidden="true"></span>&nbsp;
                            Sign up</a>
                    </div>
                </div>
                <div class="col-8 right-col">
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-sign-in" role="tabpanel" aria-labelledby="v-pills-sign-in-tab">
                            <form id="loginForm" class="form-sign-in" action="<c:url value="/login"/>" method="post">
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required autofocus>
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in">
                                <button class="btn btn-lg btn-success btn-block" type="button" value="user1" style="visibility: hidden">Sign as test user</button>
                            </form>
                        </div>
                        <div class="tab-pane fade" id="v-pills-sign-up" role="tabpanel" aria-labelledby="v-pills-sign-up-tab">
                            <form id="registerForm" class="form-sign-up" action="<c:url value="/sign_up"/>" method="post">
                                <input class="form-control" type="email" name="email" placeholder="Email" required>
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required>
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Register">
                                <button class="btn btn-lg btn-success btn-block" type="button" value="user1" style="visibility: hidden">Sign as test user</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        const $buttons = $("button.btn.btn-lg.btn-success.btn-block");
        for (let i = 0; i < $buttons.length; i++) {
            let $button = $buttons[i];
            $button.onclick = function () {
                const $registerFormTest = $("#registerForm input.form-control");
                $registerFormTest[0].value = 'wer@wer.ru';
                $registerFormTest[1].value = 'wer';
                $registerFormTest[2].value = '123123';
                const $loginFormTest = $("#loginForm input.form-control");
                $loginFormTest[0].value = 'wer';
                $loginFormTest[1].value = '123123';
            }
        }

        $.datetimepicker.setDateFormatter('moment');
        $('#datetimepicker').datetimepicker({
            format: 'YYYY-MM-DDTHH:mm'
        });
        <c:choose>
        <c:when test="${not empty requestScope.stateRegistration and requestScope.stateRegistration.booleanValue()}">
        $.notify({
            title: '<strong>Complete!</strong>',
            message: 'You are success created login'
        }, {
            type: 'success',
            delay: 15000
        });
        </c:when>
        <c:when test="${not empty requestScope.stateRegistration and not requestScope.stateRegistration.booleanValue()}">
        $.notify({
            title: '<strong>Error!</strong>',
            message: 'You aren\'t created login'
        }, {
            type: 'danger',
            delay: 0
        });
        </c:when>
        </c:choose>
        <c:forEach items="${requestScope.errors}" var="error" varStatus="status">
        $.notify({
            title: '<strong>${error.left}</strong>',
            message: '${error.right}'
        }, {
            type: 'danger',
            delay: 0
        });
        </c:forEach>
    </script>
</div>
</body>
</html>
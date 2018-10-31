const addLiInUl = function (result, message) {
    const date = moment();
    const list = $('.list-unstyled:last div.card-body:last');

    function getMessageElement() {
        return $('<div></div>').addClass('row')
            .append($('<div></div>').addClass('col-6 offset-6')
                .append($('<li></li>').addClass('media')
                    .append($('<div></div>').addClass('media-body')
                        .append($('<div></div>').addClass('text-muted float-right')
                            .append($('<small></small>')
                                .append($('<div></div>').addClass('float-right').attr('title', date.format('HH:mm:ss')).html('&nbsp;' + date.format('YYYY-MM-DD')))))
                        .append($('<strong></strong>').addClass('text-success').text('Your answer'))
                        .append($('<p></p>').text(message)))));
    }

    function getMessageResult() {
        return $('<div></div>').addClass('row')
            .append($('<div></div>').addClass('col-6')
                .append($('<li></li>').addClass('media')
                    .append($('<div></div>').addClass('media-body')
                        .append($('<strong></strong>').addClass('text-info').text('Computer response'))
                        .append($('<p></p>').text(result)))));
    }

    function getTotalMessage() {
        return $('<div></div>').addClass('card-body')
            .append(getMessageElement()).append(getMessageResult());
    }

    if (list.length === 1) list.after(getTotalMessage());
    else $('.list-unstyled:last').append(getTotalMessage());
};
const validateMessage = function (message) {
        for (let i = 0; i < message.length - 1; i++) {
            for (let j = i + 1; j < message.length; j++) {
                if (message[i] === message[j]) return false;
            }
        }
        return true;
    }
;
const validateYourChoice = function ($controlPanel) {
    for (let i = 0; i < $controlPanel.length; i++) {
        if (!$($controlPanel.get(i)).val()) return false;
    }
    return true;
};
const createMessage = function ($controlPanel) {
    let message = '';
    for (let i = 0; i < $controlPanel.length; i++) {
        message += $($controlPanel.get(i)).val();
    }
    return message;
};
const sendAttempt = function () {
    const path = $('.container:first');
    const $controlPanel = $('#controlPanel select');
    let message = createMessage($controlPanel);
    if (!validateYourChoice($controlPanel)) {
        $.notify({
            title: '<strong>Caution!</strong>',
            message: 'Choose all numbers for send answer!'
        }, {
            type: 'danger'
        });
        return;
    }
    if (!validateMessage(message)) {
        $.notify({
            title: '<strong>Caution!</strong>',
            message: 'Numbers should not be repeated!'
        }, {
            type: 'danger'
        });
        return;
    }
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.data('contextPath') + '/step/new' + '?' + $.param({
            answer: message
        }),
        type: "POST",
        success: function (result) {
            addLiInUl(result, message);
            goToLastAnswer();
            let standardMessage = 'Attempt sent';
            if ('COMPLETED' === result) {
                standardMessage = 'Congratulations! You are win!';
                $('#startNewGame').css("visibility", "visible");
            }
            $.notify({
                title: '<strong>Complete!</strong>',
                message: standardMessage
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Attempt is not sent status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })

};

const goToLastAnswer = function () {
    $('.list-unstyled div.card-body:last', window.parent.document).get(0).scrollIntoView();
};

const init = function () {
    $('#sendAttempt').on('click', sendAttempt);
    goToLastAnswer();
};

$(window).on('load', init);
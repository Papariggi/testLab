var currQuestionIndex = 0;
var script = document.createElement('script');
script.type = 'text/javascript';
script.src = 'http://code.jquery.com/jquery-1.8.3.js';
document.head.appendChild(script);

var set = [];

var counter = 150; //время в сек.
function count() {
    document.getElementById("count").innerHTML =
        parseInt(counter / 60) + " мин. " + (counter % 60) + " сек.";
    if (counter < 60) {
        document.getElementById("count").innerHTML = (counter % 60) + " сек.";
        if (counter < 0) {
            alert("Время вышло");
            finish();
        }
    };
    counter--;
    setTimeout(count, 1000);
};


function start(event) {
    event.preventDefault();
    count();

    currQuestionIndex = 0;
    $.ajax({
        type: "POST",
        url : '/test/startServlet',
        data : ""
    })

    $.ajax({
        type: "POST",
        url : '/test/getQuestionServlet',
        data : "questionIndex=" + currQuestionIndex,
        success : function(responseText) {

            $('#currentQuestion').html(responseText);
            makeChoice(currQuestionIndex);
        }
    })
    setTimeout(function () {
        $('#startTest').show();
        $('#startBtn').hide();
        $('#timer').show();
        $('#startTest1').show();
    }, 300);

};

function next() {
    if (currQuestionIndex === 4 || currQuestionIndex > 4) {
        if (confirm("Закончить выполнение теста?"))
            finish();
        else
            currQuestionIndex = 4;
    }
    else
        currQuestionIndex++;

    $.ajax({
        type: "POST",
        url : '/test/getQuestionServlet',
        data : "questionIndex=" + currQuestionIndex,
        success : function(responseText) {
            $('#questionAndOptions1').trigger('reset');
            $('#questionAndOptions2').trigger('reset');
            set.length=0;
            setTimeout(function () {
                $('#questionAndOptions1').hide();
                $('#questionAndOptions2').hide();
            }, 100);

            makeChoice(currQuestionIndex);
            $('#currentQuestion').html(responseText);
        }
    })
};

function last() {
    if (currQuestionIndex < 0) {
        currQuestionIndex = 0;
    }
    else
        currQuestionIndex--;

    $.ajax({
        type: "POST",
        url : '/test/getQuestionServlet',
        data : "questionIndex=" + currQuestionIndex,
        success : function(responseText) {
            $('#currentQuestion').html(responseText);
            set.length = 0;
            $('#questionAndOptions1').trigger('reset');
            $('#questionAndOptions2').trigger('reset');

            setTimeout(function () {
                $('#questionAndOptions1').hide();
                $('#questionAndOptions2').hide();
            }, 100);
            makeChoice(currQuestionIndex);
        }
    })
};

function makeAnswer(index) {
    if (index == 1) {
        var ans = set[($("input[name='option']:checked").val())];
    }
    else {
        var ans = $('#answerText').val();
    }

    $.ajax({
        type: "POST",
        url : '/test/addAnswerServlet',
        data : {
            answer: ans,
            questionIndex : currQuestionIndex,
        },
        success : next()
    })
    set.length = 0;
    $('#questionAndOptions1').trigger('reset');
    $('#questionAndOptions2').trigger('reset');

    setTimeout(function () {
        $('#questionAndOptions1').hide();
        $('#questionAndOptions2').hide();
    }, 100);
    return false;
};


function finish() {
    document.location.href = "http://localhost:8080/test/result";
};


function getAnswers(index) {
    $.ajax({
        type: "POST",
        url : '/test/getSetOfAnswersServlet',
        data : "questionIndex=" + index,
        success : function(responseText) {
            var receivedData = [];
            $.each(responseText.array, function(index) {
                $.each(responseText.array[index], function(key, value) {
                    var point = [];
                    point.push(key);
                    point.push(value);
                    receivedData.push(point);
                });
            });

            //костыль
            var CurrOption = 0;
            for (let i = 0; i < receivedData.length; i++) {
                if (String(receivedData[i]).includes("answer")) {
                    $("#option" + CurrOption).html(String(receivedData[i]).
                        substring(7, String(receivedData[i]).length));

                    set[CurrOption] = (String(receivedData[i]).
                        substring(7, String(receivedData[i]).length));
                    CurrOption++;
                }
            }

        }
    })
};


function currentQuestion(index) {
    $.ajax({
        type: "POST",
        url : '/test/getQuestionServlet',
        data : "questionIndex=" + index,
        success : function(responseText) {
            getAnswers(index);
            $('#currentQuestion').html(responseText);
        }
    })
};

function makeChoice(currQuestionIndex) {
    if (selfRandom(1,2) == 1)
    {
        getAnswers(currQuestionIndex);
        setTimeout(function () {
            $('#questionAndOptions1').show();
        }, 100);
    }
    else {
        setTimeout(function () {
            $('#questionAndOptions2').show();
        }, 100);
    }
};

function selfRandom(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
};


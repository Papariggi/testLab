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
            getAnswers(currQuestionIndex);

        }
    })
    setTimeout(function () {
        $('#questionAndOptions').show();
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
            getAnswers(currQuestionIndex);
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
            getAnswers(currQuestionIndex);
        }
    })
};

function makeAnswer() {
    var ans = set[($("input[name='option']:checked").val())];
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
    $('#questionAndOptions').trigger('reset');
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

};


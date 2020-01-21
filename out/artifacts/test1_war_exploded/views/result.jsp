<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Laplass
  Date: 20.01.2020
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-center-align">
    <h1>Books</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-container w3-light-blue">
        <h2>Результаты</h2>
    </div>

    <div>
        <table class="w3-table w3-striped">
            <tr>
                <th>Номер вопроса</th>
                <th>Правильный ответ</th>
                <th>Ваш ответ</th>
                <th>Результат</th>
            </tr>
            <%
                Map<String, String> userAnswers =
                        (Map<String, String>) request.getAttribute("userAnswers");
                List<String> rightAnswers = (List<String>) request.getAttribute("rightAnswers");
                String j;
                if (userAnswers != null && !userAnswers.isEmpty()) {
                    for (int i = 0; i < rightAnswers.size(); i++) {
                        out.println("<tr>");
                        out.print("<td>" + (i+1) + "</td>\n" +
                                "<td>" + rightAnswers.get(i) + "</td>\n");
                        if (userAnswers.size() > i) {
                            out.print("<td>" + userAnswers.get(Integer.toString(i)) + "</td>\n");
                            if (userAnswers.get(Integer.toString(i)).equals(rightAnswers.get(i))) {
                                out.print("<td>" + "Верно" + "</td>\n");
                            }
                            else
                                out.print("<td>" + "Неверно" + "</td>\n");
                        }
                        else
                        {
                            out.print("<td>"  + "</td>\n");
                            out.print("<td>" + "</td>\n");
                        }
                        out.println("</tr>");
                    }
                }
                else
                    out.println("There no answers");
            %>
        </table>
    </div>

    <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <button class="w3-btn w3-round-large"
                onclick="location.href='/test/index.html'">Вернуться к тесту</button>
    </div>
</body>
</html>

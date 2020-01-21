<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%
         String path = request.getContextPath();
         String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
 %>
 <!DOCTYPE html>
 <html>
 <head>
     <base href="<%=basePath%>">
     <title>Тест</title>
     <link rel="stylesheet" type="text/css" href="">
     <script type="text/javascript" src="js/app-ajax.js"></script>
     <script src="https://code.jquery.com/jquery-2.1.0.min.js"
             type="text/javascript"></script>
     <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 </head>
 <body>
     <!-- header -->
     <div class="w3-container w3-blue-grey w3-opacity w3-center">
         <h1>Тест по всемирной истории</h1>
     </div>

     <div class="w3-container w3-red w3-opacity w3-right w3-padding-large"
          id="timer" style="display: none">
         <label>Осталось времени:</label>
             <span id="count"></span>
     </div>

     <div class="w3-container w3-center">
         <input type="button" id="startBtn" class="w3-btn w3-hover-border-red w3-round-large w3-container w3-red w3-xlarge"
                 onclick="start(event)" value="Начать тест">
     </div>

     <div id = "startTest" class="w3-container w3-left" style = "display: none">
         <button id="lastBtn" class="w3-btn w3-hover-green w3-round-large w3-container w3-green w3-xlarge"
                 onclick="last()">Назад</button>
         <button id="nextBtn" class="w3-btn w3-hover-green w3-round-large w3-container w3-green w3-xlarge"
                 onclick="next()">Далее</button>
         <button id="finishBtn" class="w3-btn w3-hover-green w3-round-large w3-container w3-green w3-xlarge"
                 onclick="finish()">Закончить</button>
     </div>

     <div class="w3-panel" id="startTest1" style="display:none">
         <div class="w3-panel w3-teal w3-center w3-padding-24 w3-xxxlarge">
             <spand id="currentQuestion"/>
         </div>

         <div class="w3-panel w3-padding-16 w3-left">
             <form name = "questionAndOptions" id = "questionAndOptions" onsubmit="return makeAnswer();"
                   style="display: none">
                 <label>
                     <input type="radio" data-id="0" name="option" value="0">
                     <spand id="option0"/>
                 </label>
                 <br>
                 <label>
                     <input type="radio" data-id="1" name="option" value="1">
                     <spand id="option1"/>
                 </label>
                 <br>
                 <label>
                     <input type="radio" data-id="2" name="option" value="2">
                     <spand id="option2"/>
                 </label>
                 <br>
                 <label>
                     <input type="radio" data-id="3" name="option" value="3">
                     <spand id="option3"/>
                 </label>
                 <br>
                 <input type="submit" id = "answerBtn" class="w3-btn w3-left w3-panel w3-padding-16
                    w3-hover-white w3-round-large w3-container w3-red w3-xlarge" value="Ответить" />

                 </p>
             </form>
         </div>
     </div>
 </body>

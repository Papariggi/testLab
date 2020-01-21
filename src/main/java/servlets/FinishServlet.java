package servlets;

import entities.PullOfQuestions;
import monitor.QuestionMonitor;
import monitor.UserAnswersStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userAnswers", UserAnswersStorage.getUserAnswers());
        req.setAttribute("rightAnswers", PullOfQuestions.getInstance().
                getRightAnswers(QuestionMonitor.getInstance().getUsedQuestions()));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/result.jsp");
        requestDispatcher.forward(req, resp);
    }
}

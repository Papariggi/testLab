package servlets;

import entities.PullOfQuestions;
import monitor.QuestionMonitor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetSetOfAnswersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("questionIndex");
        QuestionMonitor monitor = QuestionMonitor.getInstance();

        List<String> answersList =
                new ArrayList<>(PullOfQuestions.getInstance().
                        getAnswersOnQuestion(List.copyOf(monitor.getUsedQuestions()).
                                                get(Integer.parseInt(index))));

        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            for (int i = 0; i < answersList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i);
                jsonObject.put("answer", answersList.get(i));
                jsonArray.put(jsonObject);
            }
            json.put("array", jsonArray);
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(json.toString());
        out.close();
    }
}

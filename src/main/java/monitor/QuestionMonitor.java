package monitor;

import entities.PullOfQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionMonitor {
    private static Set<String> usedQuestions = new HashSet<>();
    private volatile static QuestionMonitor instance;

    private QuestionMonitor() {

    }

    public static QuestionMonitor getInstance() {
        synchronized (QuestionMonitor.class) {
            if (instance == null)
                instance = new QuestionMonitor();

            return instance;
        }
    }

    public boolean isQuestionUsed(String question) {
        if (usedQuestions.contains(question))
            return true;

        return false;
    }

    public void addUsedQuestions(String question) {
        usedQuestions.add(question);
    }

    public void clearUsedQuestions() {
        usedQuestions.clear();
    }

    public Set<String> getUsedQuestions() {
        if (usedQuestions.isEmpty())
            return getFiveRandomQuestions();

        return usedQuestions;
    }

    public Set<String> getFiveRandomQuestions() {
        clearUsedQuestions();
        for (int i = 0; i < 5; i++) {
            usedQuestions.add(getRandomQuestion());
        }

        return usedQuestions;
    }

    private String getRandomQuestion() {
        int bot = 0;
        int top = PullOfQuestions.getInstance().getQuestionAnswerMap().size();

        List<String> keyList = new ArrayList<>(
                PullOfQuestions.getInstance().getQuestionAnswerMap().keySet());

        while (true) {
            int rndm = bot + (int) (Math.random() * top);

            if (!usedQuestions.contains(keyList.get(rndm)))
                return keyList.get(rndm);
        }
    }
}

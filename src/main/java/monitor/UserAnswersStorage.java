package monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAnswersStorage {
    private static Map<String, String> userAnswers = new HashMap<>();

    public static void setAnswer(String question, String answer) {
        userAnswers.put(question, answer);
    }
    public static Map<String, String> getUserAnswers() {
        return userAnswers;
    }

    public static void clear() {
        userAnswers.clear();
    }
}

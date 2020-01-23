package entities;

import java.util.*;

public class PullOfQuestions
{
    private static volatile PullOfQuestions instance;
    private static Map<String, String> questionAnswerMap = new HashMap<>(); //right answers for each quest
    private static Map<String, List<String>> setOfAnswers = new HashMap<>(); //list of all questions

    private PullOfQuestions() {
        initializeQuestions();
    }

    public static PullOfQuestions getInstance() {
        synchronized (PullOfQuestions.class) {
            if (instance == null) {
                instance = new PullOfQuestions();
            }

            return instance;
        }
    }

    public Map<String, String> getQuestionAnswerMap() {
        return questionAnswerMap;
    }

    public List<String> getRightAnswers(Set<String> questions) {
        List<String> usedAnswers = new ArrayList<>();

        for (String quest : questions) {
            usedAnswers.add(questionAnswerMap.get(quest));
        }
        return usedAnswers;
    }

    public void setQuestionAnswerMap(Map<String, String> questionAnswerMap) {
        PullOfQuestions.questionAnswerMap = questionAnswerMap;
    }

    public Map<String, List<String>> getSetOfAnswers() {
        return setOfAnswers;
    }

    public void setSetOfAnswers(Map<String, List<String>> setOfAnswers) {
        PullOfQuestions.setOfAnswers = setOfAnswers;
    }

    public List<String> getAnswersOnQuestion(String question) {
        for (Map.Entry answers : setOfAnswers.entrySet()) {
            if (answers.getKey().equals(question))
                return (List<String>) answers.getValue();
        }

        return null;
    }

    private void initializeQuestions() {
        questionAnswerMap.put("В каком году прекратила существовать Австро-Венгерская империя?",
                "1918");
        setOfAnswers.put("В каком году прекратила существовать Австро-Венгерская империя?",
                new ArrayList<>(Arrays.asList("1916", "1918", "1895", "1861")));

        questionAnswerMap.put("Первый раздел Польши произошел в ...",
                "1722");
        setOfAnswers.put("Первый раздел Польши произошел в ...",
                new ArrayList<>(Arrays.asList("1415", "1901", "1722", "1706")));

        questionAnswerMap.put("Сколько лет длилась Столетняя война?",
                "116");
        setOfAnswers.put("Сколько лет длилась Столетняя война?",
                new ArrayList<>(Arrays.asList("99", "112", "100", "116")));

        questionAnswerMap.put("В каком году была основана компания Valve?",
                "1996");
        setOfAnswers.put("В каком году была основана компания Valve?",
                new ArrayList<>(Arrays.asList("1996", "1997", "1998", "1999")));

        questionAnswerMap.put("В каком году вышла Super Mario Bros. на gameboy advance",
                "2004");
        setOfAnswers.put("В каком году вышла Super Mario Bros. на gameboy advance",
                new ArrayList<>(Arrays.asList("1983", "1985", "2004", "2007")));


        questionAnswerMap.put("В какой букмекерской конторе самые быстрые выплаты и самые высокие коэффициенты?",
                "1XBet");
        setOfAnswers.put("В какой букмекерской конторе самые быстрые выплаты и самые высокие коэффициенты?",
                new ArrayList<>(Arrays.asList("1XBet", "1XBet", "1XBet", "1XBet")));

        questionAnswerMap.put("Начало Курской битвы: ",
                "5 июля 1943");
        setOfAnswers.put("Начало Курской битвы: ",
                new ArrayList<>(Arrays.asList("5 июля 1943", "1 июня 1942", "22 июня 1941",
                        "1 августа 1944")));

        questionAnswerMap.put("В каком городе была подписана конституция США?",
                "Филадельфия");
        setOfAnswers.put("В каком городе была подписана конституция США?",
                new ArrayList<>(Arrays.asList("Бостон", "Филадельфия", "Майами", "Портленд")));

        questionAnswerMap.put("Сколько государственных дум было в дореволюционной россии?",
                "4");
        setOfAnswers.put("Сколько государственных дум было в дореволюционной россии?",
                new ArrayList<>(Arrays.asList("1", "4", "10", "0")));

        questionAnswerMap.put("Год вхождения Литвы, Латвии и Эстонии в СССР?",
                "1940");
        setOfAnswers.put("Год вхождения Литвы, Латвии и Эстонии в СССР?",
                new ArrayList<>(Arrays.asList("1920", "1937", "1917", "1940")));

        questionAnswerMap.put("В каком году в России отменили крепостное право?",
                "1861");
        setOfAnswers.put("В каком году в России отменили крепостное право?",
                new ArrayList<>(Arrays.asList("1900", "1861", "1860", "1869")));

        questionAnswerMap.put("Какой князь крестил Русь?",
                "Владимир");
        setOfAnswers.put("Какой князь крестил Русь?",
                new ArrayList<>(Arrays.asList("Ярополк", "Святослав", "Игорь", "Владимир")));

        questionAnswerMap.put("Кто считался главным языческим богом у славян?",
                "Перун");
        setOfAnswers.put("Кто считался главным языческим богом у славян?",
                new ArrayList<>(Arrays.asList("Перун", "Велес", "Яровит", "Ярило")));

        questionAnswerMap.put("В каком году произошла битва при Аустерлице?",
                "1805");
        setOfAnswers.put("В каком году произошла битва при Аустерлице?",
                new ArrayList<>(Arrays.asList("1800", "1805", "1811", "1825")));

        questionAnswerMap.put("В каком году произошла высадка союзников в Нормандии?",
                "1944");
        setOfAnswers.put("В каком году произошла высадка союзников в Нормандии?",
                new ArrayList<>(Arrays.asList("1940", "1941", "1944", "1942")));

        questionAnswerMap.put("Третья страна, которая стала ядерной державой ...",
                "Великобритания");
        setOfAnswers.put("Третья страна, которая стала ядерной державой ...",
                new ArrayList<>(Arrays.asList("СССР", "Япония", "Иран", "Великобритания")));

        questionAnswerMap.put("Сколько царей было в Древнем Риме?",
                "7");
        setOfAnswers.put("Сколько царей было в Древнем Риме?",
                new ArrayList<>(Arrays.asList("0", "7", "15", "67")));
    }
}

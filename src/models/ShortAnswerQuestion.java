package models;

import interfaces.IQuestion;
import interfaces.QuestionView;
import views.ShortAnswerView;

import java.util.Arrays;
import java.util.List;

public class ShortAnswerQuestion implements IQuestion<String, String> {

    private boolean exactAnswer;
    private String[] answers;
    private String question;

    public ShortAnswerQuestion(String question, String[] answers, boolean exactAnswer) {
        this.question = question;
        this.answers = answers;
        this.exactAnswer = exactAnswer;
    }

    @Override
    public boolean checkAnswer(String userInput) {
        List<String> answersList = Arrays.asList(answers);
        if (exactAnswer) {
            return answersList.contains(userInput.toLowerCase());
        }
        for (String word : userInput.toLowerCase().split("\\s+")) {
            if (answersList.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getCorrectAnswers() {
        return answers;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public QuestionView getView() {
        return new ShortAnswerView(this);
    }
}

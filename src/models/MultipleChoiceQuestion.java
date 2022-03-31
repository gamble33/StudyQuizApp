package models;

import interfaces.IQuestion;
import interfaces.QuestionView;
import views.MultipleChoiceView;

import java.util.Arrays;

public class MultipleChoiceQuestion implements IQuestion<String, String> {


    private final String question;
    private final String[] possibleAnswers;
    private final String[] correctAnswers;

    public MultipleChoiceQuestion(String question, String[] possibleAnswers, String[] correctAnswers) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean checkAnswer(String userInput) {
        return Arrays.asList(correctAnswers).contains(userInput);
    }

    @Override
    public String[] getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public QuestionView getView() {
        return new MultipleChoiceView(this);
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

}

package models;

import java.util.Arrays;

public class MultipleChoiceQuestion implements IQuestion<String, Integer> {


    private final String question;
    private final String[] possibleAnswers;
    private final Integer[] correctAnswers;

    public MultipleChoiceQuestion(String question, String[] possibleAnswers, Integer[] correctAnswers) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean checkAnswer(Integer userInput) {
        return Arrays.asList(correctAnswers).contains(userInput);
    }

    @Override
    public Integer[] getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

}

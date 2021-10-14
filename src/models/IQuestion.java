package models;

public interface IQuestion<T, S> {

    boolean checkAnswer(S userInput);
    S[] getCorrectAnswers();
    T getQuestion();

}

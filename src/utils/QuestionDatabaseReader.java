package utils;

import interfaces.IQuestion;
import interfaces.IQuestionRetriever;
import models.LabelDiagramQuestion;
import models.MultipleChoiceQuestion;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionDatabaseReader implements IQuestionRetriever {

    private Connection connection;

    @Override
    public List<IQuestion> getQuestions() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.193:3306/StudyQuizApp",
                    "StudyQuizAppClient@localhost",
                    "123"
            );
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT id, text, type, exactAnswer FROM question");
            List<IQuestion> questions = new ArrayList<>();
            while (res.next()) {
                switch (res.getInt("type")) {
                    case 1: {
                        questions.add(
                                extractMcq(
                                        res.getInt("id"),
                                        res.getString("text")
                                )
                        );
                        break;
                    }
                    case 2: {
                        questions.add(
                                extractLabelDiagram(
                                        res.getInt("id"),
                                        res.getString("text"),
                                        res.getBoolean("exactanswer")
                                )
                        );
                        break;
                    }
                    default:
                        throw (new Exception());
                }
            }
            connection.close();
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private LabelDiagramQuestion extractLabelDiagram(int questionId, String text, boolean exact) throws SQLException {

        PreparedStatement statement = connection
                .prepareStatement("SELECT answer FROM answer WHERE answer.questionid=? AND answer.correct=TRUE");
        statement.setInt(1, questionId);
        ResultSet res = statement.executeQuery();

        List<String> possibleAnswers = new ArrayList<String>();
        while (res.next()) {
            possibleAnswers.add(res.getString("answer"));
        }
        return new LabelDiagramQuestion(
                text,
                possibleAnswers.toArray(new String[0]),
                exact
        );
    }

    private MultipleChoiceQuestion extractMcq(int questionId, String text) throws SQLException {

        PreparedStatement statement = connection
                .prepareStatement("SELECT answer, correct FROM answer WHERE answer.questionid=?");
        statement.setInt(1, questionId);
        ResultSet res = statement.executeQuery();

        List<String> possibleAnswers = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        while (res.next()) {
            if (res.getBoolean("correct")) {
                correctAnswers.add(res.getString("answer"));
            }
            possibleAnswers.add(res.getString("answer"));
        }

        return new MultipleChoiceQuestion(
                text,
                possibleAnswers.toArray(new String[0]),
                correctAnswers.toArray(new String[0])
        );
    }
}

package utils;

import interfaces.IQuestion;
import interfaces.IQuestionRetriever;
import models.LabelDiagramQuestion;
import models.MultipleChoiceQuestion;
import models.ShortAnswerQuestion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionFileReader implements IQuestionRetriever {

    @Override
    public List<IQuestion> getQuestions() {
        try {
            return readFile();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Couldn't read questions file");
            return new ArrayList<IQuestion>();
        }
    }

    public List<IQuestion> readFile() throws Exception {
        List<IQuestion> questions = new ArrayList<IQuestion>();

        File file = new File("out/production/StudyQuizApp/resources/questions.csv");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split("~");
            String questionType = line[0];
            switch(questionType) {
                case "mcq": {
                    questions.add(extractMcq(line));
                    break;
                }
                case "labeldiagram": {
                    questions.add(extractLabelDiagram(line));
                    break;
                }
                case "shortanswer": {
                    questions.add(extractShortAnswerQuestion(line));
                    break;
                }
                default:
                    throw(new Exception());
            }
        }
        scanner.close();
        return questions;
    }

    private LabelDiagramQuestion extractLabelDiagram(String[] line){

        String imagePath = line[1];
        boolean isExact = line[2].equalsIgnoreCase("exact") ;
        List<String> possibleAnswers = new ArrayList<String>();
        for(int i=3; i<line.length; i++){
            possibleAnswers.add(line[i]);
        }
        return new LabelDiagramQuestion(
                imagePath,
                possibleAnswers.toArray(new String[0]),
                isExact
        );
    }

    private MultipleChoiceQuestion extractMcq(String[] line){
        String question = line[1];
        String[] correctAnswers = new String[]{line[2]};
        List<String> possibleAnswers = new ArrayList<String>();
        for(int i=2; i<line.length; i++){
            possibleAnswers.add(line[i]);
        }
        return new MultipleChoiceQuestion(
                question,
                possibleAnswers.toArray(new String[0]),
                correctAnswers
        );
    }

    private ShortAnswerQuestion extractShortAnswerQuestion(String[] line) {
        String question = line[1];
        boolean isExact = line[2].equalsIgnoreCase("exact");
        List<String> possibleAnswers = new ArrayList<String>();
        for(int i=3; i<line.length; i++){
            possibleAnswers.add(line[i]);
        }
        return new ShortAnswerQuestion(
                question,
                possibleAnswers.toArray(new String[0]),
                isExact
        );
    }
}

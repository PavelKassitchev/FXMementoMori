package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Controller {


    @FXML
    private Label question;


    private final static String[] questions = Questions.getQuestions();

    private static int page;

    private Stage prevStage;

    public Label getQuestion() {
        return question;
    }


    public void toIntro(ActionEvent actionEvent) throws Exception {
        Button button = (Button)actionEvent.getSource();
        prevStage = (Stage)button.getScene().getWindow();
        prevStage.close();
        Introduction introduction = new Introduction();
        introduction.start(new Stage());

    }



    public void toQuestions(ActionEvent actionEvent) throws Exception {
        Button button = (Button)actionEvent.getSource();
        prevStage = (Stage)button.getScene().getWindow();
        prevStage.close();
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.start(new Stage());
        page++;


    }

    public void goNext(ActionEvent actionEvent) throws Exception {
        if (page == questions.length - 1) {
            page = 0;
            Button button = (Button)actionEvent.getSource();
            prevStage = (Stage)button.getScene().getWindow();
            prevStage.close();
            new Main().start(new Stage());
        }
        else {
            question.setText(questions[page++]);
        }
    }


}

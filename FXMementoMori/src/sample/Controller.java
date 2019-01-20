package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class Controller {


    @FXML
    private Label question;


    private final static String[] questions = Questions.getQuestions();

    private static int page;

    private Stage prevStage;
    private Main main;
    private Questionnaire questionnaire;


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
        questionnaire = new Questionnaire();
        questionnaire.start(new Stage());
        page++;

    }

    public void goNext(ActionEvent actionEvent) {
        question.setText(questions[page++]);
    }


}

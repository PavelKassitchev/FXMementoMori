package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Label question;

    private final static String[] questions = Questions.getQuestions();

    private int page;

    private Stage prevStage;
    private Main main;
    private Questionnaire questionnaire;


    public void toIntro(ActionEvent actionEvent) throws Exception {
        prevStage.close();
        Introduction introduction = new Introduction();
        introduction.start(new Stage());
    }

    public void setPrevStage (Stage prevStage) {
        this.prevStage = prevStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void toQuestions(ActionEvent actionEvent) throws Exception {
        prevStage.close();
        questionnaire = new Questionnaire();
        questionnaire.start(new Stage());

    }

    public void goNext(ActionEvent actionEvent) {
        question.setText(questions[page++]);
    }
}

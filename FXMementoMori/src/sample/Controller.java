package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Controller {

    @FXML private Label greeting, count, question, pager;
    @FXML private Button next;
    @FXML private ToggleGroup radioGroup, gender;
    @FXML private TextField name, birthdate;
    @FXML private RadioButton yes, no, male, female;

    private static UserHandler userHandler;

    private static User user, tempUser;

    private static boolean isOldUser;

    private final static String[] questions = Questions.getQuestions();

    private static int page;

    private int pages = questions.length;

    private Stage prevStage;


    public void toIntro(ActionEvent actionEvent) throws Exception {
        if (isOldUser) {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            buttons.setAlignment(Pos.CENTER);
            Button yesDialog = new Button("Да");
            Button noDialog = new Button("Нет");
            buttons.getChildren().addAll(yesDialog, noDialog);
            yesDialog.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    closeStage(actionEvent);
                    Introduction introduction = new Introduction();
                    try {
                        introduction.start(new Stage());
                        tempUser = userHandler.obtainUser();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            noDialog.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    closeStage(actionEvent);
                    Introduction introduction = new Introduction();
                    try {
                        introduction.start(new Stage());
                        tempUser = new FXUser();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            box.getChildren().addAll(new Label("Показывать предыдущие ответы?"), buttons);
            box.setPrefWidth(300);
            Scene scene = new Scene(box);
            dialog.setScene(scene);
            dialog.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setAlwaysOnTop(true);
            dialog.requestFocus();

            dialog.show();
        }

        else {
            closeStage(actionEvent);
        Introduction introduction = new Introduction();
        introduction.start(new Stage());
        tempUser = new FXUser();
        }

    }


    public void toQuestions(ActionEvent actionEvent) throws Exception {
        closeStage(actionEvent);
        new Questionnaire().start(new Stage());
        String answer;
        RadioButton radioButton = (RadioButton)gender.getSelectedToggle();
        if (radioButton == null) {
            answer = "";
        }
        else {
            answer = radioButton.getText();
        }
        switch(answer) {
            case "Мужской":
                tempUser.setGender(User.MALE);
                break;
            case "Женский":
                tempUser.setGender(User.FEMALE);
                break;
            default:
                tempUser.setGender(0);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date;

        try {
            date = formatter.parse(birthdate.getText());

        } catch (ParseException e) {
            date = new Date();
        }
        tempUser.setBirthDate(date);

        String username = name.getText();
        if (username.isEmpty()) {
            username = "Пользователь";
        }
        tempUser.setName(username);
    }

    public void goNext(ActionEvent actionEvent) throws Exception {

        RadioButton radioButton = (RadioButton)radioGroup.getSelectedToggle();
        String answer;
        if (radioButton == null) {
            answer = "";
        }
        else {
            answer = radioButton.getText();
        }
        switch(answer) {

            case "Да":
                tempUser.setReply(page, 1);
                break;
            case "Нет":
                tempUser.setReply(page, -1);
                break;
            default:
                tempUser.setReply(page, 0);
        }
        if (page == pages - 1) {
            page = 0;
            isOldUser = true;
            userHandler.saveUser(tempUser);
            closeStage(actionEvent);
            tempUser = null;
            new Main().start(new Stage());

        }
        else {
            if (page == pages - 2) {
                next.setText("ГОТОВО");
            }
            question.setText(questions[++page]);
            pager.setText((page + 1) + "/" + pages);
            setCheck();

        }
    }

    public void setQuestion() {
        question.setText(questions[page]);
        pager.setText((page + 1) + "/" + pages);
    }


    public void setFacade() throws Exception {
        userHandler = new FXUserHandler();
        user = userHandler.obtainUser();
        String name = user.getName();
        greeting.setText("Здравствуйте, " + name + "!");
        if (user.getGender() != 0) {
            Date lastDate = userHandler.getLastDate(user);
            DateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            String counter = formatter.format(lastDate).toUpperCase();
            count.setText(counter);
        }
        else {
            count.setText("СЧЕТЧИК НЕ УСТАНОВЛЕН");
        }
    }

    public void goPrev(ActionEvent actionEvent) throws Exception {
        if (page != 0) {
            if (page == pages - 1) {
                next.setText("Далее");
            }
            question.setText(questions[--page]);
            setCheck();
            pager.setText((page + 1) + "/" + pages);

        }
        else {
            closeStage(actionEvent);
            new Introduction().start(new Stage());
        }
    }

    public void clean(ActionEvent actionEvent) throws Exception {
        userHandler.cleanUser();
        closeStage(actionEvent);
        new Main().start(new Stage());
        isOldUser = false;
    }

    public void toMain(ActionEvent actionEvent) throws Exception {
        closeStage(actionEvent);
        tempUser = null;
        new Main().start(new Stage());
    }

    private void closeStage(ActionEvent actionEvent) {
        Button button = (Button)actionEvent.getSource();
        prevStage = (Stage)button.getScene().getWindow();
        prevStage.close();
    }

    private void setCheck() {
        switch(tempUser.getReply(page)) {
            case 0:
                radioGroup.selectToggle(null);
                break;
            case -1:
                radioGroup.selectToggle(no);
                break;
            case 1:
                radioGroup.selectToggle(yes);
                break;
        }
    }

    public void setIntro() {
        if (tempUser != null) {
            if (!tempUser.getName().equals("Пользователь")) {
                name.setText(tempUser.getName());
            }
            Date date = tempUser.getBirthDate();
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String birth = formatter.format(date);
            if (!birth.equals(formatter.format(new Date()))) {
                birthdate.setText(birth);
            }
            switch (tempUser.getGender()) {
                case User.MALE:
                    gender.selectToggle(male);
                    break;
                case User.FEMALE:
                    gender.selectToggle(female);
                    break;
            }
        }
    }
}

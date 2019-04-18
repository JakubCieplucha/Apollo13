package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;


/**
 * Controls the Menu stage.
 */

public class First {

    private Stage stage;
    public static Controller controller;
    public static Instructions instructions;

    /**
     * Gets the current stage
     * @return stage the current stage
     */
    public Stage getStage() {
        return stage;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button scoreboardBtn;

    public void initialize(){
        try {
           Image image = new Image(getClass().getResourceAsStream("ksiezyc.jpg"),
                    800, 600, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            anchorPane.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void instructionsBtnClickedBtnClicked(ActionEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Instructions.fxml"));
        Parent root = loader.load();
        instructions=(Instructions) loader.getController();
        stage.setTitle("Instructions");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }


    @FXML
    void startBtnClicked(ActionEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controller = (Controller) loader.getController();
        stage.setTitle("Apollo 13");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @FXML
    void scoreBtnClicked(ActionEvent event) {
try {
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ranking.fxml"));
    Parent root = loader.load();
    stage.setTitle("Scoreboard");
    stage.setScene(new Scene(root, 800, 600));
    stage.show();
} catch(Exception e){
    try {
        PrintWriter fileWriter=new PrintWriter(new File("error.txt"));
        e.printStackTrace(fileWriter);
        fileWriter.close();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
}
    }



}



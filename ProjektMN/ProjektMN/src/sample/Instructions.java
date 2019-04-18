package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controls the Instruction Stage.
 */
public class Instructions {

    private Stage stage;
    public static Controller controller;

    /**
     * Gets the current stage.
     * @return stage the current stage.
     */
    public Stage getStage() {
        return stage;
    }


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startBtn;


    @FXML
    void BegginGame(ActionEvent event) throws IOException {

        Main.first.startBtnClicked(event);
    }

}

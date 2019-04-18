package sample;

import LogicSection.PlayerComparator;
import LogicSection.Scoreboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.Collections;

/**
 * Controls the Ranking stage.
 */
public class Ranking {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label player1txt;

    @FXML
    private Label player2txt;

    @FXML
    private Label player3txt;

    @FXML
    private Label player4txt;

    @FXML
    private Label player5txt;

    @FXML
    private Label numberOfWins;

    @FXML
    private Label player1ScoreTxt;

    @FXML
    private Label player2ScoreTxt;

    @FXML
    private Label player3ScoreTxt;

    @FXML
    private Label player4ScoreTxt;

    @FXML
    private Label player5ScoreTxt;

    @FXML
    private Button menuBtn;

    private Stage stage;

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public void initialize() {

        try {

            Image image = new Image(getClass().getResourceAsStream("scoreboard.jpg"),
                    800, 600, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            anchorPane.setBackground(new Background(backgroundImage));

        } catch (Exception e) {
            System.out.println("Nie odnaleziono pliku");

        }

        PlayerComparator playerComparator = new PlayerComparator();

        Scoreboard scoreboard = new Scoreboard();

        scoreboard.readPlayers();

        Collections.sort(scoreboard.getListOfPlayers(), playerComparator);

        try {

            int number = scoreboard.getListOfPlayers().size();
            numberOfWins.setText(number + " times!");

            player1txt.setText(scoreboard.getListOfPlayers().get(0).getName());
            player1ScoreTxt.setText(df2.format(scoreboard.getListOfPlayers().get(0).getScore()));

            player2txt.setText(scoreboard.getListOfPlayers().get(1).getName());
            player2ScoreTxt.setText(df2.format(scoreboard.getListOfPlayers().get(1).getScore()));

            player3txt.setText(scoreboard.getListOfPlayers().get(2).getName());
            player3ScoreTxt.setText(df2.format(scoreboard.getListOfPlayers().get(2).getScore()));

            player4txt.setText(scoreboard.getListOfPlayers().get(3).getName());
            player4ScoreTxt.setText(df2.format(scoreboard.getListOfPlayers().get(3).getScore()));

            player5txt.setText(scoreboard.getListOfPlayers().get(4).getName());
            player5ScoreTxt.setText(df2.format(scoreboard.getListOfPlayers().get(4).getScore()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not even 5 players have beaten the game ");
        }

    }


    @FXML
    void backToMenu(ActionEvent event) throws Exception {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main main = new Main();
        main.start(stage);
    }

}

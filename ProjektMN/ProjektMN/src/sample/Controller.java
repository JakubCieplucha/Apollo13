package sample;

import LogicSection.DataAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * Controls the game stage.
 */

public class Controller {


    private DataAdmin dataAdmin;
    private boolean done = false;
    private Stage stage;
    private String playerName;
    private boolean hasBegun=false;



    private AudioClip note= new AudioClip(getClass().getResource("song.mp3").toString());
    private AudioClip scream= new AudioClip(getClass().getResource("scream.mp3").toString());
    private AudioClip pullup= new AudioClip(getClass().getResource("pullup.mp3").toString());
    private AudioClip explosion= new AudioClip(getClass().getResource("explosion.mp3").toString());
    private AudioClip fanfary= new AudioClip(getClass().getResource("fanfary.mp3").toString());

    /**
     * Plays the background music.
     */
    public void playFanfary(){
        this.fanfary.play();
    }

    /**
     * Stops the background music.
     */
    public void stopNote() {
        this.note.stop();
    }

    /**
     * Plays the scream sound if a Player looses.
     */
    public void playScream() {
        this.scream.play();
    }

    /**
     *  Stops the scream sound if a Player looses.
     */
    public void stopScream() {
        this.scream.stop();
    }

    /**
     * Plays the explosion sound if a Player looses.
     */
    public void playExplosion() {
        explosion.play();
    }

    /**
     * Stops the explosion sound if a Player looses.
     */
    public void stopExplosion(){
        explosion.stop();
    }

    /**
     * Plays an alert when the Player reaches 10 000 m.
     */
    public void playPullup(){
        this.pullup.play();
    }
    /**
     * Stops the alert when the Player reaches 10 000 m.
     */
    public void stopPullUp(){
        pullup.stop();
    }
    /**
     * Gets the current stage/
     */

    public Stage getStage() {
        return stage;
    }
    /**
     * Returns the state of the boolean done.
     */
    public boolean isDone() {
        return done;
    }

    @FXML
    private TextField fuelConsumptionField;

    public TextField getFuelConsumptionField() {
        return fuelConsumptionField;
    }

    @FXML
    private Pane pane;

    @FXML
    private TextField fuelTankField;

    public TextField getFuelTankField() {
        return fuelTankField;
    }

    @FXML
    private TextField heightField;

    public TextField getHeightField() {
        return heightField;
    }

    public Pane getPane() {
        return pane;
    }

    @FXML
    private AnchorPane anchorPane;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @FXML
    private TextField velocityField;

    public TextField getVelocityField() {
        return velocityField;
    }

    @FXML
    private TextField timeField;

    @FXML
    private Button quitButton;

    @FXML
    private Button returnToMenuBtn;

    @FXML
    private ScatterChart<Number,Number> chart1;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button beginBtn;

    @FXML
    private TextField playerNameTxt;

    @FXML
    void beginClicked(ActionEvent event) {

        hasBegun=true;

        playerName=playerNameTxt.getText();
        playerNameTxt.setVisible(false);
        beginBtn.setVisible(false);

        // rozpocznij symulacje
        note.play();
        dataAdmin = new DataAdmin(quitButton, fuelConsumptionField, fuelTankField, heightField, velocityField, timeField, pane, anchorPane,returnToMenuBtn,chart1,xAxis,yAxis,playerName);
        dataAdmin.start();
    }


    public void initialize() {
        try {
            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("rakieta.png"),
                    100, 100, false, false);
            imageView.setImage(image);
            pane.getChildren().addAll(imageView);
            Image image2 = new Image(getClass().getResourceAsStream("ksiezyc.jpg"),
                    800, 600, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            anchorPane.setBackground(new Background(backgroundImage));

        } catch (Exception e) {
            System.out.println("Nie odnaleziono pliku");
        }

    }

    @FXML
    void quitBtnClicked(ActionEvent event) {

        if(hasBegun==false){
            Main.first.getStage().close();
        }

        if(hasBegun){
        if(quitButton.isArmed()) {
            try {
                musicStop();
                dataAdmin.stop();
                Main.first.getStage().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }}
    }

    @FXML
    void returnAction(ActionEvent event) throws Exception {

        musicStop();
        dataAdmin.stop();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main main=new Main();
        main.start(stage);
    }
    private void musicStop(){
        note.stop();
        scream.stop();
        fanfary.stop();
        explosion.stop();
        pullup.stop();
    }

}



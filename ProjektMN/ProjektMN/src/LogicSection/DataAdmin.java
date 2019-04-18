package LogicSection;

import Interfaces.CalculateAcceleration;
import javafx.application.Platform;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.First;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 * manages the application
 */
public class DataAdmin implements Runnable {

    private Integrator integrator;
    private CalculateAcceleration rocket;
    private Analizator analizator;
    private double[] timeVector;
    private double[] wartosci;
    private double u;
    private int counter;
    private Button button;
    private Thread worker;
    private volatile boolean isRunning;
    private double score;
    private Button returnButton;
    private boolean done = true;
    private String playerName;
    private Player player;
    private Scoreboard scoreboard;

    private TextField fuelConsumption;
    private TextField fuelLeft;
    private TextField height;
    private TextField velocity;
    private TextField time;
    private Pane pane;
    private AnchorPane anchorPane;
    private ScatterChart<Number, Number> chart1;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series<Number, Number> series1;

    /**
     *
     * @param button button that controlles keyboard keys pressings
     * @param fuelConsumption field in which the value of fuel consumption is displayed
     * @param fuelLeft field in which the value of fuel left is displayed
     * @param height field in which the value of height is displayed
     * @param velocity field in which the value of velocity is displayed
     * @param time field in which the value of time is displayed
     * @param pane field in which the value of fuel consumption is displayed
     * @param anchorPane pane that holds Rocket image
     * @param returnButton button that allows user to return to menu
     * @param chart1 chart that displays the data
     * @param xAxis chart X axis
     * @param yAxis chart Y axis
     * @param playerName player name
     */
    public DataAdmin(Button button, TextField fuelConsumption, TextField fuelLeft, TextField height, TextField velocity, TextField time, Pane pane, AnchorPane anchorPane, Button returnButton,
                     ScatterChart<Number, Number> chart1, NumberAxis xAxis, NumberAxis yAxis, String playerName) {
        this.integrator = new Integrator(0.01);
        this.analizator = new Analizator();
        this.rocket = new Rocket(1.63, 636);
        this.timeVector = new double[]{0, 1};
        this.wartosci = new double[]{50000, -150, 2730.14};/////
        this.u = 0;
        this.button = button;
        this.counter = 0;
        this.fuelConsumption = fuelConsumption;
        this.fuelLeft = fuelLeft;
        this.height = height;
        this.velocity = velocity;
        this.time = time;
        this.pane = pane;
        this.anchorPane = anchorPane;
        this.returnButton = returnButton;
        this.chart1 = chart1;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.series1 = new XYChart.Series<>();
        this.playerName = playerName;
    }


    //Klasa obliczajaca wynik w wypadku wygranej

    /**
     * calculates score if player manages to win
     * @return player's score
     */
    public double calculateScore() {
        double score = 0;
        double vel = 0;

        if ((analizator.getvValues().get(analizator.getvValues().size() - 1)) == 0) {

            vel = 3;
        } else {
            vel = analizator.getvValues().get(analizator.getvValues().size() - 1);
        }

        score = analizator.getFuelValues().get(analizator.getFuelValues().size() - 1) * 0.2 + (double) (10 / counter) * 200 + Math.pow(vel, 2) * 50;
        return score;
    }

    /**
     * The main method, making Integrator and Analizator run in separate thread and managing the whole game
     */
    @Override
    public void run() {
        isRunning = true;
        done = true;
        while (isRunning) {
            u=u(button);
            integrator.integrate(rocket, analizator, timeVector, wartosci, u);
            wartosci[0] = analizator.gethValues().get(analizator.gethValues().size() - 1);
            wartosci[1] = analizator.getvValues().get(analizator.getvValues().size() - 1);
            wartosci[2] = analizator.getmValues().get(analizator.getmValues().size() - 1);
            display();
            if (analizator.gethValues().get(analizator.gethValues().size() - 1) <= 10000 && done == true) {
                Platform.runLater(() -> {
                    First.controller.stopNote();
                    First.controller.playPullup();
                    done = false;
                });
            }
            try {
                Thread.sleep(1000);
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (analizator.gethValues().get(analizator.gethValues().size() - 1) <= 0) {
                if (analizator.getvValues().get(analizator.getvValues().size() - 1) >= -2 &&
                        analizator.getvValues().get(analizator.getvValues().size() - 1) <= 2) {
                    System.out.println("Wygrana");
                    Platform.runLater(() -> {
                        pane.setVisible(false);
                        score = Math.round(calculateScore()); // obliczanie wyniku w wypadku wygranej

                        //Adding player to the scoreboard

                        try {
                            player = new Player(playerName, score);
                            scoreboard = new Scoreboard();
                            scoreboard.readPlayers();
                            scoreboard.addPlayers(player);
                            scoreboard.savePlayers();
                        }catch(Exception e){
                            try {
                                PrintWriter printWriter = new PrintWriter(new File("error.txt"));
                                e.printStackTrace(printWriter);
                                printWriter.close();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }

                        Label label = new Label();
                        label.setLayoutY(280);
                        label.setLayoutX(50);
                        label.setFont(new Font(30));
                        label.setTextFill(Color.GOLD);
                        First.controller.stopPullUp();
                        First.controller.playFanfary();
                        label.setText("Mission Complete! " + "Your score : " + score);
                        anchorPane.getChildren().add(label);
                        returnButton.setVisible(true);
                    });
                    try {
                        Image image1 = new Image(getClass().getResourceAsStream("wygrana.png"),
                                800, 600, false, false);
                        BackgroundImage backgroundImage2 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                        anchorPane.setBackground(new Background(backgroundImage2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        isRunning = false;
                        stop();
                    } catch (Exception e) {
                        System.out.println("Exception in run() method, DataAdmin Class");
                    }

                } else {
                    System.out.println("przegrana");
                    Platform.runLater(() -> {
                        pane.setVisible(false);
                        Label label = new Label();
                        label.setLayoutY(280);
                        label.setLayoutX(50);
                        label.setFont(new Font(30));
                        label.setTextFill(Color.RED);
                        label.setText("You've failed, everybody died");
                        First.controller.stopPullUp();
                        First.controller.playScream();
                        try {
                            Thread.sleep(1000);/////////
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        First.controller.playExplosion();
                        anchorPane.getChildren().add(label);
                        returnButton.setVisible(true);
                    });

                    try {
                        Image image1 = new Image(getClass().getResourceAsStream("porazka.jpeg"),
                                800, 600, false, false);
                        BackgroundImage backgroundImage2 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                        anchorPane.setBackground(new Background(backgroundImage2));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("File not found, DataAdmin class");
                    }
                }
                try {
                    isRunning = false;
                    stop();
                } catch (Exception e) {
                    System.out.println("Exception in run() method, DataAdmin Class");
                }
            }

        }
    }

    /**
     * Calculates fuel consumption
     * @param button event listener
     * @return fuel consumption value
     */
    private double u(Button button) {
        button.setOnKeyPressed(keyEvent -> {
            if (analizator.getFuelValues().get(analizator.getFuelValues().size() - 1) != 0) {
                switch (keyEvent.getCode()) {
                    case UP:
                        if (u < 0 && u >= -16.5) {
                            u=u+1.5;
                        }
                        break;
                    case DOWN:
                        if (u > -16.5 && u <= 0) {
                            u=u-1.5;
                        }
                        break;
                }
            } else {
                u = 0;
            }
        });
        return u;
    }

    /**
     * displays current values of every data on screen
     */
    public void display() {
        Platform.runLater(() -> {
            fuelConsumption.setText(Double.toString(u));
            double value = Math.round(analizator.gethValues().get(analizator.gethValues().size() - 1) * 100);
            height.setText(Double.toString(value / 100));
            value = Math.round((wartosci[2] - 1000) * 100);
            fuelLeft.setText(Double.toString(value / 100));
            value = Math.round(analizator.getvValues().get(analizator.getvValues().size() - 1) * 100);
            velocity.setText(Double.toString(value / 100));
            time.setText(Integer.toString(counter));
            pane.setLayoutY(400 - analizator.gethValues().get(analizator.gethValues().size() - 1) / 50000 * 400);
            drawChart();
        });
    }

    /**
     * updates chart data series
     */
    public void drawChart() {
        xAxis.setLabel("h(t)");
        yAxis.setLabel("v(t)");
        xAxis.setTickUnit(1);
        yAxis.setTickUnit(1);//podzialka
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);//zakres automatyczny
        series1.setName("Phase space ");
        if (analizator.gethValues().size() > 0) {
            series1.getData().add(new XYChart.Data<Number, Number>(analizator.gethValues().get(analizator.gethValues().size() - 1), analizator.getvValues().get(analizator.getvValues().size() - 1)));
            chart1.getData().clear();
            chart1.getData().add(series1);
        }
    }

    /**
     * stops the method thread
     */
    public void stop() {
        isRunning = false;
    }


    /**
     * interrupts the method thread
     */
    public void interrupt() {
        isRunning = false;
        worker.interrupt();
    }

    /**
     * starts the thread
     */
    public void start() {
        worker = new Thread(this, " New thread ");
        worker.start();
    }

}

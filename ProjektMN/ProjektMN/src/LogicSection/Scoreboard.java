package LogicSection;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Class used to read and write a list of Players who have managed to win the game from and to a file.
 */

public class Scoreboard  {

    /**
     * A list of Players who have managed to win the game.
     */
    private ArrayList<Player> listOfPlayers = new ArrayList<>();

    /**
     * Gets the list of Players who have managed to win the game.
     * @return listOfPlayers a list of Players who have managed to win the game.
     */
    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }


    /**
     * Adds a player to the list of winners.
     * @param player a user who has managed to win the game.
     */
    protected void addPlayers(Player player){

        listOfPlayers.add(player);

    }


    /**
     * Saves the list of Players to a file.
     */
    public void savePlayers()  {


        try {

            File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());

            File dir = new File(root.getAbsolutePath() + "\\..\\..\\..\\src\\sample");

            dir.mkdir();

            File file=new File(dir,"Players.txt");

            FileWriter fw= new FileWriter(file);
            BufferedWriter bw= new BufferedWriter(fw);

            for (Player player: listOfPlayers) {

                bw.write(player.getName()+System.lineSeparator() );
                bw.write(player.getScore()+System.lineSeparator());

            }

            bw.flush();
            bw.close();
            fw.close();



        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }


    }


    /**
     * Reads the list of Players from a file.
     */
    public void readPlayers() {


        try {

            File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());

            File dir = new File(root.getAbsolutePath() + "\\..\\..\\..\\src\\sample");

            File file=new File(dir,"Players.txt");

            FileReader fr= new FileReader(file);
            BufferedReader br =new BufferedReader(fr);

            String line;


            while ((line = br.readLine()) != null){
                Player player=new Player(line,Double.parseDouble(br.readLine()));
                addPlayers(player);
            }
     br.close();

            }catch (Exception e){
                    e.printStackTrace();
                }


    }



}

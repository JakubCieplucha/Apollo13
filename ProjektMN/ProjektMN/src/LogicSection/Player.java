package LogicSection;

/**
 * Used to crate a players profile.
 */

public class Player   {

    /**
     *  The players name.
     */
    protected String name;

    /**
     * The players score appointed if a player manages to win the game.
     */

    protected double score;


    /**
     *
     * @param name The players name.
     * @param score  The players score appointed if a player manages to win the game.
     */

    public Player(String name, double score) {
        this.name = name;
        this.score = score;
    }

    /**
     *  Gets the Players name.
     * @return name the Players name.
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the Players score.
     * @return score The players score appointed if a player manages to win the game.
     */
    public double getScore() {
        return score;
    }



    /**
     * Returns the string representation of the object.
     * @return the name and score of the Player.
     */
    @Override
    public String toString() {
        return getName() +" " +  getScore();
    }



}// end of class

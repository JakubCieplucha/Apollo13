package Interfaces;

/**
 * Interface used to update a class that a change of state has been made to the rocket.
 */

public interface Updater {

    /**
     *
     * @param t  the time since the game has been started.
     * @param h the current altitude.
     * @param v the current velocity.
     * @param m the current mass.
     * @param fuel the amount of fuel left.
     */

    void update(double t, double h, double v, double m,double fuel);
}

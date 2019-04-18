package LogicSection;

import Interfaces.CalculateAcceleration;

/**
 * Used to create an object that represents the rocket used in the game
 */

public class Rocket implements CalculateAcceleration {
    /**
     * the gravity acceleration
     */
    private double g;

    /**
     * the exhaust's velocity
     */
    private double k;

    /**
     *
     * @param g gravity acceleration
     * @param k exhaust's velocity
     */
    public Rocket(double g, double k) {
        this.g = g;
        this.k = k;
    }

    /**
     * returns calculated current acceleration
     * @param u the current rate at which the rockets fuel is being burnt.
     * @param m the rockets current mass.
     * @return acceleration
     */
    @Override
    public double a(double u, double m) {
        return -g - k * u / m;
    }
}

package Interfaces;

/**
 * Interface used to calculate the acceleration.
 */

public interface CalculateAcceleration {

    /**
     *
     * @param u the current rate at which the rockets fuel is being burnt.
     * @param m the rockets current mass.
     * @return a the rockets current acceleration.
     */

    double a(double u, double m);
}

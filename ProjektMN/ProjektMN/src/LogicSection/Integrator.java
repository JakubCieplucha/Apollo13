package LogicSection;

import Interfaces.CalculateAcceleration;

/**
 * Used to calculate current height, velocity and mass of the rocket and fuel left and to update the Analizator's data
 */
public class Integrator {

    /**
     * the interval of time by which the values are increased
     */
    private double step;


    /**
     *
     * @param step the interval of time by which the values are increased
     */
    public Integrator(double step) {
        this.step = step;
    }

    /**
     * Calculates current height, velocity and mass of the rocket and fuel left and updates the Analizator's data
     * @param rocket rocket object
     * @param analizator analizator object, data storage
     * @param timeVector vector of interval of time in which the data are calculated
     * @param initialConditions vector of initial conditions of v, h and om
     * @param u current fuel consumption
     */
    public void integrate(CalculateAcceleration rocket, Analizator analizator, double[] timeVector, double[] initialConditions, double u) {
        int nSteps = (int) ((timeVector[1] - timeVector[0]) / step);

        double t = timeVector[0];
        double h = initialConditions[0];
        double v = initialConditions[1];
        double m = initialConditions[2];
        double a = rocket.a(u, m);
        double fuel = m-1000;
        if(fuel<u){
            u=-0.5;
        }if(fuel<0.5){
            u=0;
            fuel=0;
            m=1000;
        }
        analizator.update(t, h, v, m, fuel);
        for (int i = 0; i < nSteps; i++) {

            //równania
            double vHalf = v + a * step / 2;
            double mHalf = m + u * step / 2;
            double uHalf = u; //u jest stałe przez całą sekundę. uHalf wprowadzam dla formalności.
            double aHalf = rocket.a(uHalf, mHalf);
            double hNew = h + vHalf * step ;
            double vNew = v + aHalf * step;
            double mNew = m + uHalf * step;

            t += step;
            h = hNew;
            v = vNew;
            m = mNew;
        }
        fuel=m-1000;
        analizator.update(t, h, v, m, fuel);


    }


}

package LogicSection;

import Interfaces.Updater;

import java.util.ArrayList;

/**
 *Data storage for DataAdmin
 */
public class Analizator implements Updater {

    /**
     * list of time values
     */
    private ArrayList<Double> tValues;
    /**
     * list of height values
     */
    private ArrayList<Double> hValues;
    /**
     * list of velocity values
     */
    private ArrayList<Double> vValues;
    /**
     * list of mass values
     */
    private ArrayList<Double> mValues;
    /**
     * list of fuel left values
     */
    private ArrayList<Double> fuelValues;

    /**
     * contructor creates all of the lists
     */
    public Analizator() {
        tValues = new ArrayList<>();
        hValues = new ArrayList<>();
        vValues = new ArrayList<>();
        mValues = new ArrayList<>();
        fuelValues=new ArrayList<>();
    }

    /**
     *
     * @return time list
     */
    public ArrayList<Double> gettValues() {
        return tValues;
    }

    /**
     *
     * @return height list
     */
    public ArrayList<Double> gethValues() {
        return hValues;
    }

    /**
     *
     * @return velocity list
     */
    public ArrayList<Double> getvValues() {
        return vValues;
    }

    /**
     *
     * @return mass list
     */
    public ArrayList<Double> getmValues() {
        return mValues;
    }

    /**
     *
     * @return fuel left list
     */
    public ArrayList<Double> getFuelValues() {
        return fuelValues;
    }


    /**
     * Adds current data to their lists
     * @param t  the time since the game has been started.
     * @param h the current altitude.
     * @param v the current velocity.
     * @param m the current mass.
     * @param fuel the amount of fuel left.
     */
    @Override
    public void update(double t, double h, double v, double m, double fuel) {
        tValues.add(t);
        hValues.add(h);
        vValues.add(v);
        mValues.add(m);
        fuelValues.add(fuel);
    }
}

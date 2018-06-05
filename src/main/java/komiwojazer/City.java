package komiwojazer;

public class City {

    private double x;
    private double y;
    private String name;
    private int index;

    public City () {}


    public City(double x, double y, String name, int index) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.index = index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public double distanceTo(City city) {
        double xDist = Math.abs(getX() - city.getX());
        double yDist = Math.abs(getY() - city.getY());
        return Math.sqrt((xDist * xDist) + (yDist * yDist));
    }

    @Override
    public String toString() {
        return name;
    }
}

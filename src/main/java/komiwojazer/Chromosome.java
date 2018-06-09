package komiwojazer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing chromosomes - in our case - single tour through all cities.
 */

public class Chromosome implements Comparable{

    private ArrayList<City> cities;
    private double distance;
    private double fitness;

    //generate random chromosome
    public Chromosome() {
        cities = new ArrayList<>();
        cities = generateRandom();
        distance = getDistance();
        fitness = getFitness();
    }

    //generate chromosome from given list of citites
    public Chromosome(ArrayList<City> cities) {
        this.cities = cities;
        distance = getDistance();
        fitness = getFitness();
    }

    //generate list with randomly placed cities
    private ArrayList<City> generateRandom() {
        for (int i = 0; i < CitiesManager.getCitiesNumber(); i++) {
            cities.add(CitiesManager.getCityAt(i));
        }
        Collections.shuffle(cities);
        return cities;
    }


    public ArrayList<City> getCities() {
        return cities;
    }

    //get total distance of tour that this chromosome represents
    public double getDistance() {

        if (distance != 0.0) {
            return distance;
        }

        for (int i = 1; i < cities.size(); i++) {
            distance += cities.get(i - 1).distanceTo(cities.get(i));
        }
        //dystans between last and first
        distance += cities.get(cities.size() - 1).distanceTo(cities.get(0));

        return distance;
    }

    //get value of fitness function which is defined as 1/distance
    public double getFitness() {
        fitness = 1. / getDistance();
        return fitness;
    }

    public boolean contains(City city) {
        return cities.contains(city);
    }

    @Override
    public int compareTo(Object o) {
        Chromosome chromosome = (Chromosome) o;
        double diff = chromosome.getFitness() - getFitness();
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        }
        return 0;
    }

    public int getGene(int index) {
        return cities.get(index).getIndex();
    }

    public City getCityAt(int index) {
        return cities.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CitiesManager.getCitiesNumber(); i++) {
            sb.append(cities.get(i).getIndex()).append(" ");
        }
        return sb.toString();
    }
}

package komiwojazer;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneticAlghoritm {

    // Uses a bit mask to perform a uniform order uniformCrossover
    // returns array of two chromosomes - children
    public Chromosome[] uniformCrossover(Chromosome parent1, Chromosome parent2) {
        int[] bitmask = generateBitmask(CitiesManager.getCitiesNumber());

        ArrayList<City> child1 = new ArrayList<>();
        ArrayList<City> child2 = new ArrayList<>();

        //fill lists with null to avoid IndexOutOfBondException
        while (child1.size() < CitiesManager.getCitiesNumber()) {
            child1.add(null);
            child2.add(null);
        }

        //when at some position bitmask value is 1 then pick city from parent at this position
        // and set at the same position in child
        for (int i = 0; i < bitmask.length; i++) {
            if (bitmask[i] == 1) {
                child1.set(i, parent1.getCityAt(i));
                child2.set(i, parent2.getCityAt(i));
            }
        }
        //fill empty spaces in children
        for (int i = 0; i < bitmask.length; i++) {
            //if child doesn't contain given city, then look for first empty (null) gene and place city there
            if (!child1.contains(parent2.getCityAt(i))) {

                for (int j = 0; j < bitmask.length; j++) {

                    if (child1.get(j) == null) {
                        child1.set(j, parent2.getCityAt(i));
                        break;
                    }
                }
            }
            //the same for the other child
            if (!child2.contains(parent1.getCityAt(i))) {

                for (int j = 0; j < bitmask.length; j++) {

                    if (child2.get(j) == null) {
                        child2.set(j, parent1.getCityAt(i));
                        break;
                    }
                }
            }
        }
        Chromosome ch1 = new Chromosome(child1);
        Chromosome ch2 = new Chromosome(child2);
        Chromosome[] children = {ch1, ch2};
        return children;
    }

    //Mutation swaps two randomly picked cities in given chromosome
    public Chromosome mutate(Chromosome chromosome) {
        Random random = new Random();
        ArrayList<City> cities = chromosome.getCities();
        int rand1 = random.nextInt(cities.size());
        int rand2 = random.nextInt(cities.size());
        City city1 = cities.get(rand1);
        City city2 = cities.get(rand2);
        cities.set(rand1, city2);
        cities.set(rand2, city1);

        return new Chromosome(cities);
    }

    //genetates bitmask for crossover
    private int[] generateBitmask(int size) {

        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (random.nextInt(2) == 0) ? 0 : 1;
        }
        return array;
    }

    //choose new population from given population
    public Population chooseNewPopulation(Population oldPop, Population newPop) {
        //construct big population from given two and pick best individuals
        Population bigPopulation = new Population(oldPop, newPop);
        PriorityQueue<Chromosome> population = new PriorityQueue<>();
        for (int i = 0; i < oldPop.getPopulationSize(); i++) {
            population.add(bigPopulation.pollBestIndividual());
        }
        return new Population(population);
    }

    //crossover and mutate population
    public Population evolvePopulation(Population population) {
        //deep copy is made in order to save the old population
        Population pop = population.deepCopy();
        Population newPop = new Population();
        Chromosome[] children;
        Random random = new Random();
        //while there are still chromosomes in population do crossover
        while (pop.getPopulationSize() > 1) {
            //take 2 best individuals from population and crossover them
            children = uniformCrossover(pop.pollBestIndividual(), pop.pollBestIndividual());
            int rand = random.nextInt(2);
            //mutate radomly picked children
            children[rand] = mutate(children[rand]);
            newPop.addChildren(children);
        }
        return newPop;
    }
}

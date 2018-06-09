package komiwojazer;

import java.util.PriorityQueue;

public class Population {

    private PriorityQueue<Chromosome> population;

    //holds Chromosomes sorted by their fitness function
    public Population() {
        population = new PriorityQueue<>();
    }

    //constructs new population from two given
    public Population(Population pop1, Population pop2) {
        population = new PriorityQueue<>();
        population.addAll(pop1.population);
        population.addAll(pop2.population);
    }

    //generate random population
    public Population(int size) {
        population = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            population.add(new Chromosome());
        }
    }
    //creates population from given chromosomes
    public Population(PriorityQueue<Chromosome> population) {
        this.population = population;
    }

    //returns and remove the best individual from population
    public Chromosome pollBestIndividual() {
        return population.poll();
    }

    //returns the best individual from population
    public Chromosome peekBestIndividual() {
        return population.peek();
    }

    //adds chromosomes from given array of two children
    public void addChildren(Chromosome[] children) {
        population.add(children[0]);
        population.add(children[1]);
    }

    //copies population
    public Population deepCopy() {
        Population pop = new Population();
        for (Chromosome ch : population) {
            pop.saveChromosome(ch);
        }
        return pop;
    }

    //adds Chromosome to population
    private void saveChromosome(Chromosome ch) {
        population.add(ch);
    }

    public int getPopulationSize() {
        return population.size();
    }
}

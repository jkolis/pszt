package komiwojazer;

import java.util.PriorityQueue;

public class Population {

    private PriorityQueue<Chromosome> population;

    public Population() {
        population = new PriorityQueue<>();
    }

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

    public Population(PriorityQueue<Chromosome> population) {
        this.population = population;
    }

    public Chromosome pollBestIndividual() {
        return population.poll();
    }

    public Chromosome peekBestIndividual() {
        return population.peek();
    }

    public void addChildren(Chromosome[] children) {
        population.add(children[0]);
        population.add(children[1]);
    }

    public Population deepCopy() {
        Population pop = new Population();
        for (Chromosome ch : population) {
            pop.saveChromosome(ch);
        }
        return pop;
    }

    private void saveChromosome(Chromosome ch) {
        population.add(ch);
    }

    public int getPopulationSize() {
        return population.size();
    }
}

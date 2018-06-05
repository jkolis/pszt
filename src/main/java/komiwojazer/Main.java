package komiwojazer;

public class Main {

    private static final int POPULATION_SIZE = 1000;
    private static final int GENERATIONS_NUMBER = 500;

    public static void main(String[] args) {
        CitiesManager.readData();
        Population pop = new Population(POPULATION_SIZE);

        FenotypeVisualiser fenotypeVisualiser = new FenotypeVisualiser();
        fenotypeVisualiser.initialize();
        fenotypeVisualiser.createGraph(pop.peekBestIndividual());

        System.out.println(pop.peekBestIndividual().getDistance());
        System.out.println();

        GeneticAlghoritm geneticAlghoritm = new GeneticAlghoritm();

        Population nextPopulation = geneticAlghoritm.evolvePopulation(pop);
        Population newPopulation = geneticAlghoritm.chooseNewPopulation(pop, nextPopulation);

        for (int i = 0; i < GENERATIONS_NUMBER; i++) {

            nextPopulation = geneticAlghoritm.evolvePopulation(newPopulation);

            newPopulation = geneticAlghoritm.chooseNewPopulation(newPopulation, nextPopulation);
            System.out.println(newPopulation.peekBestIndividual().getDistance());
        }
        fenotypeVisualiser = new FenotypeVisualiser();
        fenotypeVisualiser.initialize();

        fenotypeVisualiser.createGraph(newPopulation.peekBestIndividual());

    }
}

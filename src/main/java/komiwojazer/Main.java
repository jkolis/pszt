package komiwojazer;

public class Main {

    private static final int POPULATION_SIZE = 1000;
    private static final int GENERATIONS_NUMBER = 500;

    public static void main(String[] args) {
        CitiesManager.readData();

        //initial population
        Population pop = new Population(POPULATION_SIZE);

        FenotypeVisualiser fenotypeVisualiser = new FenotypeVisualiser();
        fenotypeVisualiser.initialize();
        fenotypeVisualiser.createGraph(pop.peekBestIndividual());

        System.out.println(pop.peekBestIndividual().getDistance());
        System.out.println();

        GeneticAlghoritm geneticAlghoritm = new GeneticAlghoritm();

        //create next population by doing crossover and mutation
        Population nextPopulation = geneticAlghoritm.evolvePopulation(pop);
        //choose best individuals from both old and next generation and set them as new population
        Population newPopulation = geneticAlghoritm.chooseNewPopulation(pop, nextPopulation);

        // repeat for certain amount of generations
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

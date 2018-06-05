package komiwojazer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CitiesManager {

    private static List<City> cities;

    public static List<City> readData() {
        cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;

            int i = 1;

            while ((line = br.readLine()) != null) {
                String[] cities = line.split("\\t");
                String cityName = cities[1];
                double latitude = Double.valueOf(cities[3]);
                double longitude = Double.valueOf(cities[4]);
                City city = new City(latitude, longitude, cityName, i);
                addCity(city);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }

    public static int getCitiesNumber() {
        return cities.size();
    }

    private static void addCity(City city) {
        cities.add(city);
    }

    public static City getCityAt(int index) {
        return cities.get(index);
    }
}

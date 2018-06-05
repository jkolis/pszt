package komiwojazer;

import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import komiwojazer.City;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FenotypeVisualiser {
    private static final int COEF1 = 130;
    private static final int COEF2 = 130;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int LAT_COEF = 50;
    private static final int LONG_COEF = 11;
    private UndirectedGraph graph;
    private StaticLayout layout;
    private JFrame frame;

    public FenotypeVisualiser() {
        graph = new UndirectedSparseGraph();
        layout = new StaticLayout(graph);
    }

    public void createGraph(Chromosome chromosome) {
        City city;
        int citiesNr = CitiesManager.getCitiesNumber();

        for (int i = 0; i < citiesNr -1 ; i++) {
            city = chromosome.getCityAt(i);
            graph.addVertex(city);
            layout.setLocation(city, COEF1 * (city.getY() - LONG_COEF), HEIGHT - COEF2 * (city.getX() - LAT_COEF));
            graph.addEdge(i, city, chromosome.getCityAt(i + 1));
        }
        city = chromosome.getCityAt(citiesNr - 1);
        graph.addVertex(city);
        layout.setLocation(city, COEF1 * (city.getY() - LONG_COEF), HEIGHT - COEF2 * (city.getX() - LAT_COEF));
        graph.addEdge(citiesNr, city, chromosome.getCityAt(0));

        frame.repaint();

    }


    public void initialize() {
        VisualizationViewer<Integer, String> viewer = new VisualizationViewer<Integer, String>(layout);
        viewer.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        viewer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        viewer.getRenderContext().setEdgeShapeTransformer(EdgeShape.line(graph));
        PluggableGraphMouse graphMouse = new PluggableGraphMouse();
        graphMouse.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON1_MASK));
        graphMouse.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));

        viewer.setGraphMouse(graphMouse);

        frame = new JFrame();
        frame.getContentPane().add(viewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();

    }

}

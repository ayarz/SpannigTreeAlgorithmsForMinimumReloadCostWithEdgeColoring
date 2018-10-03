package Gui;

import finalproject.ChangeoverCost;
import finalproject.EdgeColoring;
import finalproject.Graphs;
import finalproject.SpanningTree;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart_AWT extends ApplicationFrame {

    public LineChart_AWT() {
        super("Line Chart Example with JFreechart");

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel() {
        String chartTitle = "Karşılaştırma Sonuçları";
        String categoryAxisLabel = "Düğüm Sayısı";
        String valueAxisLabel = "Devralma Maliyeti";

        CategoryDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        return new ChartPanel(chart);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Gui gui1 = new Gui();
        Gui gui2 = new Gui();
        Gui gui3 = new Gui();
        int totalCost1 = 0;
        int totalCost2 = 0;
        int totalCost3 = 0;
        int nodeNumber = 5;
        int sampleNumber=40;
      
        while (nodeNumber <= 50) {
            for (int i = 0; i < sampleNumber; i++) {

                gui1.g = new Graphs("A");
                gui2.g = new Graphs("A");
                gui3.g = new Graphs("A");
                
                gui1.createGraph(nodeNumber);
                gui2.createGraph(nodeNumber);
                gui3.createGraph(nodeNumber);
                
                SpanningTree st1 = new SpanningTree(gui1.g);
                SpanningTree st2 = new SpanningTree(gui2.g);
                SpanningTree st3 = new SpanningTree(gui3.g);
               
                Graphs rst1 = st1.randomSpanningTree(1);
                Graphs rst2 = st2.randomSpanningTree(1);
                Graphs rst3 = st3.mst();
              
                EdgeColoring ecc1 = new EdgeColoring(rst1);
                EdgeColoring ecc2 = new EdgeColoring(rst2);
                EdgeColoring ecc3 = new EdgeColoring(rst3);
               
                ecc1.randomEdgeColoring();
                ecc2.minEdgeColoring();
                ecc3.minEdgeColoring();
              
                ChangeoverCost c1 = new ChangeoverCost(rst1);
                ChangeoverCost c2 = new ChangeoverCost(rst2);
                ChangeoverCost c3 = new ChangeoverCost(rst3);
               
                c1.calculateCost();
                c2.calculateCost();
                c3.calculateCost();
          
                totalCost1 += Integer.parseInt(c1.print());
                totalCost2 += Integer.parseInt(c2.print());
                totalCost3 += Integer.parseInt(c3.print());
               
            }
            totalCost1 = (int) (totalCost1 / (sampleNumber));
            totalCost2 = (int) (totalCost2 / (sampleNumber));
            totalCost3 = (int) (totalCost3 / sampleNumber);
            
            dataset.addValue(totalCost1, "Random ST && Random EC", "" + nodeNumber);
        
            dataset.addValue( totalCost2, "Random ST && Min EC", "" +nodeNumber);
     
            dataset.addValue(totalCost3, "Min ST &&Min EC", "" +nodeNumber );
          
            totalCost1 = 0;
            totalCost2 = 0;
            totalCost3 = 0;
            nodeNumber += 5;
       
        }

        return dataset;
    }
}

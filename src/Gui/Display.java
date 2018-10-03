package Gui;

import finalproject.Graphs;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Display {

    public static void display(Graphs g) {
        Graph graph = new SingleGraph("fun or dull");
        String s = "graph {padding : 50px;}"
                + "node {size: 20px; fill-mode: image-scaled; fill-color: blue;"
                + " text-color: red; text-size: 20; }"
                + "edge {size: 6px;}";

        graph.addAttribute("stylesheet", s);
        graph.addAttribute("ui.stylesheet", "graph { fill-color: rgb(150,150,150); }");
        for (int i = 0; i < g.nodeNumber; i++) {
            Node n = graph.addNode(g.nodeList.get(i).label);
            n.addAttribute("ui.label", n.getId());

        }

        String r = g.root.label;
        Node root = graph.getNode(r);
        root.addAttribute("ui.label", "ROOT: " + r);
        
        for (int i = 0; i < g.edgeList.size(); i++) {
            String d1 = g.edgeList.get(i).d1.label;
            String d2 = g.edgeList.get(i).d2.label;
            Edge e = graph.addEdge(d1 + d2, d1, d2);
            e.addAttribute("ui.style", "fill-color:" + g.edgeList.get(i).color.colorname + ";");

        }

        graph.display();
    }
}

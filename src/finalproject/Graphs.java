package finalproject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Graphs {

    public ArrayList<Nodes> nodeList;
    public Nodes root;
    public int nodeNumber;

    public Colors[] colorList = {new Colors(Color.BLACK, "black"), new Colors(Color.BLUE, "blue"), new Colors(Color.CYAN, "cyan"),
        new Colors(Color.GRAY, "gray"), new Colors(Color.GREEN, "green"), new Colors(Color.MAGENTA, "magenta"), new Colors(Color.ORANGE, "orange"),
        new Colors(Color.PINK, "pink"), new Colors(Color.RED, "red"), new Colors(Color.YELLOW, "yellow")};

    public LinkedList<Edges> edgeList;

    public Graphs(String rootname) {
        root = new Nodes(rootname);
        nodeList = new ArrayList<Nodes>();
        nodeNumber = 0;

        edgeList = new LinkedList<Edges>();
    }

    public void addNode(String label) {
        nodeList.add(new Nodes(label));
        nodeNumber++;
    }

    public void addEdge(int startIndex, int finalIndex, Colors color) {
        Nodes d1 = nodeList.get(startIndex);
        Nodes d2 = nodeList.get(finalIndex);

        Edges d = new Edges(d1, d2, color);
        d1.addEdge(d);
        d2.addEdge(d);
        edgeList.add(d);

    }

    public String print(String list) {
        String text = "";
        if (list.equals("nodes")) {
            for (int i = 0; i < nodeNumber; i++) {
                System.out.print(nodeList.get(i).label + ",");
                text += nodeList.get(i).label + ",";
            }
        } else if (list.equals("edges")) {
            for (int i = 0; i < edgeList.size(); i++) {
                text += edgeList.get(i).print();

            }
        }
        System.out.println();
        text += "\n";

        return text;

    }

    public void print() {

        for (int i = 0; i < nodeNumber; i++) {
            nodeList.get(i).print();

        }
    }

    public void dfs(int startIndex) {
        Stack<Nodes> stack = new Stack<Nodes>();
        Nodes firstNode = nodeList.get(startIndex);
        firstNode.makeVisited();
        System.out.println(firstNode.label);
        stack.push(firstNode);

        while (!stack.empty()) {
            Nodes upNode = stack.peek();
            Nodes neighbor = upNode.unvisitedNeighbor();
            if (neighbor == null) {
                stack.pop();
            } else {
                neighbor.makeVisited();
                System.out.println(neighbor.label);
                stack.push(neighbor);
            }
        }

    }

    public void addto_Graph(String node1, String node2) {
        boolean b1 = false;
        boolean b2 = false;
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < nodeNumber; i++) {
            if (node1.equalsIgnoreCase(nodeList.get(i).label)) {
                b1 = true;
                s1 = i;
                break;
            }
        }

        for (int j = 0; j < nodeNumber; j++) {
            if (node2.equalsIgnoreCase(nodeList.get(j).label)) {
                b2 = true;
                s2 = j;
                break;
            }
        }
        if (b1 == false && b2 == false) {
            addNode(node1);
            s1 = nodeNumber - 1;
            addNode(node2);
            s2 = nodeNumber - 1;
            addEdge(s1, s2, new Colors(Color.WHITE, "WHITE"));
        } else if (b1 == true && b2 == true) {
            boolean hasEdge = false;
            for (int i = 0; i < edgeList.size(); i++) {
                if (edgeList.get(i).isThisEdge(node1, node2)) {
                    hasEdge = true;
                    break;
                }
            }
            if (!hasEdge) {
                if (nodeList.get(s1).edgeSize < nodeList.get(s1).maxEdge && nodeList.get(s2).edgeSize < nodeList.get(s2).maxEdge) {
                    addEdge(s1, s2, new Colors(Color.WHITE, "WHITE"));
                }
            }
        } else if (b2 == true) {
            if (nodeList.get(s2).edgeSize < nodeList.get(s2).maxEdge) {
                addNode(node1);
                s1 = nodeNumber - 1;
                addEdge(s1, s2, new Colors(Color.WHITE, "WHITE"));
            }

        } else if (nodeList.get(s1).edgeSize < nodeList.get(s1).maxEdge) {
            addNode(node2);
            s2 = nodeNumber - 1;
            addEdge(s1, s2, new Colors(Color.WHITE, "WHITE"));
        }
    }

}

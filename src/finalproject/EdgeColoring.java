package finalproject;

import java.util.ArrayList;
import java.util.Stack;
import static finalproject.ReadColorCost.colorCostList;

public class EdgeColoring {

    Graphs g;

    public EdgeColoring(Graphs g) {
        this.g = g;
    }

    public void randomEdgeColoring() {
        int LIMITLESS = Integer.MAX_VALUE;
        Edges e;
        Nodes n;
        int r;
        int s = 0;
        int[] R = new int[10];
        for (int i = 0; i < R.length; i++) {
            R[i] = LIMITLESS;
        }

        for (int i = 0; i < g.nodeList.size() - 1; i++) {
            n = g.nodeList.get(i);

            for (int j = 0; j < n.edgeList.size(); j++) {
                e = n.edgeList.get(j);
                if (e.visited) {
                    Colors color = e.color;
                    for (int l = 0; l < g.colorList.length; l++) {
                        if (color.equals(g.colorList[l])) {
                            R[l] = l;

                            break;
                        }
                    }
                }
            }

            for (int j = 0; j < n.edgeList.size(); j++) {

                e = n.edgeList.get(j);
                if (!e.visited) {
                    r = (int) (Math.random() * 10);
                    while (R[r] != LIMITLESS) {

                        r = (int) (Math.random() * 10);
                    }
                    R[r] = r;

                    e.setColor(g.colorList[r]);

                    e.makeVisited();

                }
            }

            for (int j = 0; j < R.length; j++) {
                R[j] = LIMITLESS;
            }
        }
        for (int i = 0; i < g.edgeList.size(); i++) {
            g.edgeList.get(i).visited = false;
        }
    }

    public Stack<Stack<Edges>> findPaths() {
        Stack<Edges> edges = new Stack<Edges>();
        Stack<Stack<Edges>> edgesList = new Stack<Stack<Edges>>();

        boolean finish = false;
        int rootIndex = 0;
        for (int i = 0; i < g.nodeNumber; i++) {
            if (g.nodeList.get(i).label.equals(g.root.label)) {
                rootIndex = i;
                break;
            }
        }
        Nodes firstNode = g.nodeList.get(rootIndex);
        Stack<Nodes> stack = new Stack<Nodes>();

        firstNode.makeVisited();
        stack.push(firstNode);

        while (!stack.empty()) {
            Nodes upNode = stack.peek();
            Edges edge = upNode.unvisitedEdge();
            if (edge == null) {
                if (!finish) {
                    Stack<Edges> ed = edges;
                    edgesList.push(ed);
                    edges = new Stack();
                    for (int i = 0; i < ed.size(); i++) {
                        edges.push(ed.get(i));
                    }
                }
                if (edges.size() != 0) {
                    edges.pop();
                }
                stack.pop();
                finish = true;

            } else {
                finish = false;
                edge.getOtherNode(upNode).makeVisited();
                stack.push(edge.getOtherNode(upNode));
                edges.push(edge);
            }
        }

        for (int i = 0; i < g.nodeNumber; i++) {
            g.nodeList.get(i).makeUnVisited();
        }

        return edgesList;
    }

    public int indexofmaxPathSize(Stack<Stack<Edges>> edgesList) {

        int max = 0;
        int index = 0;
        for (int i = 0; i < edgesList.size(); i++) {
            if (edgesList.get(i).size() > max) {
                max = edgesList.get(i).size();
                index = i;
            }

        }
        return index;
    }

    public void minEdgeColoring() {
        Stack<Stack<Edges>> edgesList = findPaths();
        int index = indexofmaxPathSize(edgesList);

        while (!edgesList.empty()) {

            for (int i = 0; i < edgesList.get(index).size(); i++) {
                Edges e = edgesList.get(index).get(i);
                if (!e.visited) {

                    if (!e.d1.visitedEdge() && !e.d2.visitedEdge()) {

                        e.setColor(colorCostList.get(0).c1);
                        e.makeVisited();
                    } else {
                        Nodes n = e.d1.visitedEdge() ? e.d1 : e.d2;
                        ArrayList<Colors> edgeColors = new ArrayList<Colors>();
                        for (int j = 0; j < n.edgeList.size(); j++) {
                            if (n.edgeList.get(j).visited) {
                                edgeColors.add(n.edgeList.get(j).color);
                            }
                        }
                        if (i > 0) {
                            Edges ee = edgesList.get(index).get(i - 1);
                            Colors c = ee.color;
                            edgeColors.remove(c);

                            for (int j = 0; j < colorCostList.size(); j++) {

                                if (colorCostList.get(j).contain(c)) {
                                    if (colorCostList.get(j).notContains(edgeColors)) {
                                        e.setColor(colorCostList.get(j).getOtherColor(c));
                                        e.makeVisited();

                                        break;
                                    }
                                }

                            }
                        }

                        if (i == 0) {

                            for (int j = 0; j < colorCostList.size(); j++) {
                                for (int k = 0; k < edgeColors.size(); k++) {
                                    if (colorCostList.get(j).contain(edgeColors.get(k))) {
                                        Colors c = edgeColors.get(k);
                                        edgeColors.remove(edgeColors.get(k));
                                        if (colorCostList.get(j).notContains(edgeColors)) {
                                            e.setColor(colorCostList.get(j).getOtherColor(c));
                                            e.makeVisited();
                                            break;
                                        } else {
                                            edgeColors.add(c);
                                        }
                                    }

                                }

                            }
                        }
                    }
                }
            }
            edgesList.remove(index);
            index = indexofmaxPathSize(edgesList);

        }
        for (int i = 0; i < g.edgeList.size(); i++) {
            g.edgeList.get(i).visited = false;
        }

    }

}

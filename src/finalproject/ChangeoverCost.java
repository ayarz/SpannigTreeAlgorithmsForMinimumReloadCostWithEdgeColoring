package finalproject;

import java.util.Stack;
import static finalproject.ReadColorCost.colorCostList;

public class ChangeoverCost {

    Graphs g;
    int totalCost;

    public ChangeoverCost(Graphs g) {
        this.g = g;
        totalCost = 0;
    }

    public int calculateCost() {
        int startIndex = 0;
        for (int i = 0; i < g.nodeNumber; i++) {
            if (g.nodeList.get(i).label.equals(g.root.label)) {
                startIndex = i;
                break;
            }
        }

        Stack<Nodes> stack = new Stack<Nodes>();
        Stack<Edges> stackEdge = new Stack<Edges>();
        Nodes firstNode = g.nodeList.get(startIndex);
        firstNode.makeVisited();

        stack.push(firstNode);

        while (!stack.empty()) {
            Nodes upNode = stack.peek();
            Nodes neighbor = upNode.unvisitedNeighbor();

            if (neighbor == null) {
                stack.pop();
                if (!stackEdge.empty()) {
                    Edges edge1 = stackEdge.pop();
                    if (!stackEdge.empty()) {
                        Edges edge2 = stackEdge.peek();

                        for (int i = 0; i < colorCostList.size(); i++) {

                            if (colorCostList.get(i).isThisColorCost(edge1.color, edge2.color)) {
                                totalCost += colorCostList.get(i).cost;

                                break;
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < g.edgeList.size(); i++) {
                    if (g.edgeList.get(i).isThisEdge(upNode, neighbor)) {
                        Edges edge = g.edgeList.get(i);
                        stackEdge.push(edge);
                        break;
                    }
                }

                neighbor.makeVisited();

                stack.push(neighbor);
            }
        }
        for (int i = 0; i < g.nodeNumber; i++) {
            g.nodeList.get(i).makeUnVisited();
        }

        return totalCost;
    }

    public String print() {
        String text = "" + totalCost;
        // System.out.println("totalcost: " + totalCost);
        // System.out.println(" ");
        return text;
    }

}
